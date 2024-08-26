package org.example

import java.time.LocalDateTime


class Task {
    String title
    String description
    LocalDateTime dateTimeStart
    LocalDateTime dateTimeEnd
    Map actions = [:]

    Task(String title, String description, LocalDateTime dateTimeStart, LocalDateTime dateTimeEnd) {
        this.title = title
        this.description = description
        this.dateTimeStart = dateTimeStart
        this.dateTimeEnd = dateTimeEnd
    }
}
