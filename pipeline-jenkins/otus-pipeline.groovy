import java.text.SimpleDateFormat


node {
    properties([
            buildDiscarder(
                    logRotator(
                            artifactDaysToKeepStr: '',
                            artifactNumToKeepStr: '',
                            daysToKeepStr: '14',
                            numToKeepStr: '20')
            ),
            parameters([
                    booleanParam(
                            name: 'CUSTOMFATJARRUN',
                            defaultValue: true,
                            description: 'Запустить - Task customFatJar'),
                    gitParameter(
                            name: 'BRANCH',
                            description: 'По умолчанию: main',
                            branch: '',
                            branchFilter: '.*',
                            defaultValue: 'main',
                            quickFilterEnabled: false,
                            selectedValue: 'NONE',
                            sortMode: 'NONE',
                            tagFilter: '*',
                            type: 'GitParameterDefinition',
                            useRepository: 'otus_groovy_homework')
            ])])

    stage('Deleting workspace') {
        echo '-----------------DELETING WORKSPACE-----------------'
        deleteDir()
    }

    stage('Checkout') {
        echo '-----------------GIT CHECKOUT-----------------'
        checkout changelog: false, poll: false, scm: scmGit(
                branches: [[name: "${params.BRANCH}"]],
                extensions: [cloneOption(depth: 1, noTags: true, reference: '', shallow: true)],
                userRemoteConfigs: [[
                                            credentialsId: 'git-promokk',
                                            url: 'git@github.com:promokk/otus_groovy_homework.git'
                                    ]])
    }

    stage('Build projects') {
        echo '-----------------BUILD PROJECTS-----------------'
        def moduleMap = [:]
        def moduleArr = sh (
                script: 'cd ./otus-groovy; chmod +x ./gradlew; ./gradlew tasks --all | grep ":assemble" | cut -d: -f1 | sort -u',
                returnStdout: true
        ).split("\n")

        for (m in moduleArr) {
            def module = m
            moduleMap[module] = {
                printSysTime(params.BRANCH, module)
                sh "cd ./otus-groovy; chmod +x ./gradlew; ./gradlew build -p ${module}"
            }
        }
        parallel moduleMap
    }

    stage('Task customFatJar') {
        echo '-----------------TASK CUSTOMFATJAR-----------------'
        if (params.CUSTOMFATJARRUN) {
            sh 'cd ./otus-groovy; chmod +x ./gradlew; ./gradlew customFatJar'
            archiveArtifacts artifacts: 'otus-groovy/build/libs/*.jar', followSymlinks: false, onlyIfSuccessful: true
        } else {
            echo "Task customFatJar - ${params.CUSTOMFATJARRUN}"
        }
    }
}


// function
def printSysTime(def branch, def module) {
    def sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss")
    def date = new Date()
    echo "INFO: ${sdf.format(date)} -- ${branch} -- ${module}"
}
