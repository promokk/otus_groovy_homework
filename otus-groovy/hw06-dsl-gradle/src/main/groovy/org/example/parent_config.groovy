package org.example

import static org.example.ApplicationDslRun.config

config {
    name = "MyTest"
    description = "Apache Tomcat"

    http {
        port = 8080
        secure = false
    }

    https {
        port = 4443
        secure = true
    }

    mappings {
        map {
            url = "/"
            active = true
        }
        map {
            url = "/login"
            active = false
        }
    }
}