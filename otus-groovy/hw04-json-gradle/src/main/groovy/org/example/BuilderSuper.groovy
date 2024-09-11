package org.example

import groovy.json.JsonSlurper
import groovy.xml.MarkupBuilder

class BuilderSuper {
    JsonSlurper jsonSlurper = new JsonSlurper()

    def builderHtml(String jsonString) {
        StringWriter writer = new StringWriter()
        MarkupBuilder builder = new MarkupBuilder(writer)
        def resultJson = this.jsonSlurper.parseText(jsonString)
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
        writer
    }

    def builderXml(String jsonString) {
        StringWriter writer = new StringWriter()
        MarkupBuilder builder = new MarkupBuilder(writer)
        def resultJson = this.jsonSlurper.parseText(jsonString)
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
        writer
    }
}
