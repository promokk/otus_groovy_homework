package org.example


class ApplicationDslRun {
    static void config(@DelegatesTo(value = ApplicationDsl) Closure closure) {
        def applicationDsl = new ApplicationDsl()
        closure = closure.rehydrate(applicationDsl, this, this)
        closure.resolveStrategy = Closure.DELEGATE_ONLY
        closure.call()

        applicationDsl.run()
    }
}

class ApplicationDsl {
    private String name
    private String description
    private Http http
    private Https https
    private Mappings mappings

    def mappings(@DelegatesTo(value=Mappings) Closure closure) {
        mappings = new Mappings()
        closure = closure.rehydrate(mappings, this, this)
        closure.resolveStrategy = Closure.DELEGATE_ONLY
        closure.call()
    }

    def http(@DelegatesTo(value = Http) Closure closure) {
        http = new Http()
        closure = closure.rehydrate(http, this, this)
        closure.resolveStrategy = Closure.DELEGATE_ONLY
        closure.call()
    }

    def https(@DelegatesTo(value = Https) Closure closure) {
        https = new Https()
        closure = closure.rehydrate(https, this, this)
        closure.resolveStrategy = Closure.DELEGATE_ONLY
        closure.call()
    }

//    def include(String pathConfig) {
//        def a = new ConfigSlurper().parse(new File(pathConfig).toURI().toURL())
//        println(a)
//    }

    def run() {
        mappings.run()
        println "Info: ${name}, ${description}, ${http.port} - ${http.secure}, ${https.port} - ${https.secure}"
    }
}

class Mapping {
    String url
    boolean active
}

class Http {
    String port
    boolean secure
}

class Https {
    String port
    boolean secure
}

class Mappings {
    List<Mapping> mappingArr = []
    private Mapping mapping

    def map(@DelegatesTo(value = Mapping) Closure closure) {
        mapping = new Mapping()
        closure = closure.rehydrate(mapping, this, this)
        closure.resolveStrategy = Closure.DELEGATE_ONLY
        closure.call()

        mappingArr << mapping
    }

    def run() {
        mappingArr.each {it ->
            println "${it.url} - ${it.active}"
        }
    }
}
