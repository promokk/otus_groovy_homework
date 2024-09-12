package org.example

import groovy.json.JsonSlurper
import groovy.xml.MarkupBuilder

class BuilderSuper {
    JsonSlurper jsonSlurper = new JsonSlurper()
    String baseDir = 'D:/git/otus-groovy-homework/otus-groovy/fileDirectory'

    void downloadFile(String nameFile, String url) {
        String sourceJson = new URL(url).text
        def file = new File(this.baseDir, nameFile)
        // очищаем файл
        file.text = ''
        file << sourceJson
    }

    def builderHtml(String jsonFile) {
        StringWriter writer = new StringWriter()
        MarkupBuilder builder = new MarkupBuilder(writer)
        new File(this.baseDir, jsonFile).withInputStream { stream ->
            def resultJson = this.jsonSlurper.parseText(stream.text)
            builder.html {
                div {
                    div(id: "employee") {
                        p("${resultJson.name}")
                        p("${resultJson.age}")
                        p("${resultJson.secretIdentity}")
                        ul(id: "powers") {
                            resultJson.powers.each {
                                li("${it}")
                            }
                        }
                    }
                }
            }
        }
        writer
    }

    void builderXmlFile(String jsonFile, String nameFile) {
        StringWriter writer = new StringWriter()
        MarkupBuilder builder = new MarkupBuilder(writer)
        new File(this.baseDir, jsonFile).withInputStream { stream ->
            def resultJson = this.jsonSlurper.parseText(stream.text)
            builder.xml {
                employee {
                    name("${resultJson.name}")
                    age("${resultJson.age}")
                    secretIdentity("${resultJson.secretIdentity}")
                    powers {
                        resultJson.powers.each {
                            power("${it}")
                        }
                    }
                }
            }
            def file = new File(this.baseDir, nameFile)
            file.text = ''
            file << writer
        }
    }
}
