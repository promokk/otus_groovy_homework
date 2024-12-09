package com.example.todo_list_spring

import com.fasterxml.jackson.annotation.*
import jakarta.persistence.*

import java.time.LocalDateTime

@Entity
@JsonPropertyOrder(["id", "title", "description", "dateTimeStart", "dateTimeEnd"])
class Action {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id
    String title
    String description
    LocalDateTime dateTimeStart
    LocalDateTime dateTimeEnd
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="task_id", nullable = false)
    @JsonIgnore
    Task task

    Action(){}

    @JsonCreator()
    Action(@JsonProperty("title") String title,
         @JsonProperty("description") String description,
         @JsonProperty("dateTimeStart") LocalDateTime dateTimeStart,
         @JsonProperty("dateTimeEnd") LocalDateTime dateTimeEnd) {
        this.title = title
        this.description = description
        this.dateTimeStart = dateTimeStart
        this.dateTimeEnd = dateTimeEnd
    }
}
