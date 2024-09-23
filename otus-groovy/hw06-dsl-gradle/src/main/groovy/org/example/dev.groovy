package org.example

import static org.example.ApplicationDslRun.config

http {
    port = 8080
    secure = false
}

https {
    port = 4443
    secure = true
}

