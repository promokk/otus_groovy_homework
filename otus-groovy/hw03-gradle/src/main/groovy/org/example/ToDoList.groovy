package org.example

import groovy.time.TimeDuration

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import groovy.time.TimeCategory

class ToDoList {
    private InMemoryTask inMemoryTask
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm")

    ToDoList(InMemoryTask inMemoryTask) {
        this.inMemoryTask = inMemoryTask
    }

    //Метод добавляет Задачу
    def add(String title, String description, LocalDateTime dateTimeStart, LocalDateTime dateTimeEnd) {
        inMemoryTask.add(title, description, dateTimeStart, dateTimeEnd)
    }

    //Метод добавляет Действие
    def add(String title, String description, LocalDateTime dateTimeStart, LocalDateTime dateTimeEnd, int idTask) {
        inMemoryTask.add(title, description, dateTimeStart, dateTimeEnd, idTask)
    }

    // Метод изменяет Задачу
    def set(int idTask, Map params = [title: null, description: null, dateTimeStart: null, dateTimeEnd: null]) {
        inMemoryTask.set(idTask, params)
    }

    // Метод изменяет Действие
    def set(int idTask, int idAction, Map params = [title: null, description: null, dateTimeStart: null, dateTimeEnd: null]) {
        inMemoryTask.set(idTask, idAction, params)
    }

    // Метод отображает Задачи по выбранной дате
    def findTaskByDate(dateTime) {
        Map findTask = this.inMemoryTask.tasks.groupBy {
            it.value.dateTimeStart.toLocalDate() <= dateTime && it.value.dateTimeEnd.toLocalDate() >= dateTime
        }
        if (!findTask[true]) {
            return println("${dateTime} - задачи отсутствуют")
        }
        println("Задачи на ${dateTime}:\nКол-во задач: ${findTask[true].size()}")
        infoToDoList(findTask[true])
    }

    // Метод выводит инфо по Задачам или Действиям, принимает Map
    def infoToDoList(Map object) {
        String str = ""
        object.each { key, value ->
            str += """|-- ${key} --
                |Название: ${value.title}
                |Описание: ${value.description}
                |Начало: ${value.dateTimeStart.format(formatter)}
                |Конец: ${value.dateTimeEnd.format(formatter)}\n""".stripMargin()
        }
        println(str)
    }

    // Метод выводит инфо по Задачам или Действиям
    def infoToDoList(idTask = null) {
        String str = ""
        Map object = [:]
        if (idTask) {
            str = "Действия:"
            object = this.inMemoryTask.tasks[idTask].actions
        } else {
            str = "Задачи:"
            object = this.inMemoryTask.tasks
        }
        object.each { key, value ->
            str += """
                |-- ${key} --
                |Название: ${value.title}
                |Описание: ${value.description}
                |Начало: ${value.dateTimeStart.format(formatter)}
                |Конец: ${value.dateTimeEnd.format(formatter)}""".stripMargin()
        }
        println(str)
    }

    // Проверка на ближайшие события
    def checkEvent() {
        def str = "Ближайшие события:\n"
        LocalDateTime timeNow = LocalDateTime.now()
        this.inMemoryTask.tasks.each { key, value ->
            value.actions.each { k, v ->
                if (v.dateTimeStart.format("HH:mm") == timeNow.format("HH:mm")) {
                    str += "Задача '${value.title}' --> Событие '${v.title}' началось!!!\n"
                }
                if (v.dateTimeStart >= timeNow.minusMinutes(15) && v.dateTimeStart > timeNow) {
                    TimeDuration stillTime = TimeCategory.minus(v.dateTimeStart.toDate(), timeNow.toDate())
                    str += "Задача '${value.title}' --> " +
                            "Событие '${v.title}' начнется через ${stillTime.minutes} минут ${stillTime.seconds} секунд.\n"
                }
            }
            if (str == "Ближайшие события:\n") {
                str = "В ближайшее время нет событий."
            }
        }
        println(str)
    }

}
