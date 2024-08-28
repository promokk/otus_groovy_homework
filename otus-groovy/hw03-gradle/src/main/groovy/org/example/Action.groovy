package org.example

import java.time.LocalDateTime


class Action {
    String title
    String description
    LocalDateTime dateTimeStart
    LocalDateTime dateTimeEnd

    Action(String title, String description, LocalDateTime dateTimeStart, LocalDateTime dateTimeEnd) {
        this.title = title
        this.description = description
        this.dateTimeStart = dateTimeStart
        this.dateTimeEnd = dateTimeEnd
    }


}
