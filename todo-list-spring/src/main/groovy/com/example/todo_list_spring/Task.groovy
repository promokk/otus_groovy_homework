package com.example.todo_list_spring

import com.fasterxml.jackson.annotation.*
import jakarta.persistence.*

import java.time.LocalDateTime

@Entity
@JsonPropertyOrder(["id", "title", "description", "dateTimeStart", "dateTimeEnd", "actions"])
class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id
    String title
    String description
    LocalDateTime dateTimeStart
    LocalDateTime dateTimeEnd
    @OneToMany(mappedBy = "task", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<Action> actions

    Task(){}

    @JsonCreator()
    Task(@JsonProperty("title") String title,
         @JsonProperty("description") String description,
         @JsonProperty("dateTimeStart") LocalDateTime dateTimeStart,
         @JsonProperty("dateTimeEnd") LocalDateTime dateTimeEnd,
         @JsonProperty("actions") List<Action> actions) {
        this.title = title
        this.description = description
        this.dateTimeStart = dateTimeStart
        this.dateTimeEnd = dateTimeEnd
        this.actions = actions
    }
}
