package org.example

import groovy.time.TimeDuration

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import groovy.time.TimeCategory

class ToDoList {
    private Map tasks = [:]
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm")

    // Метод проверяет верно ли указана дата
    private def checkDateCorrect(LocalDateTime dateTimeStart, LocalDateTime dateTimeEnd) {
        Boolean checkError = dateTimeStart >= dateTimeEnd
        if (checkError) {
            println("Ошибка: начало указано позже чем окончание!")
            return true
        }
        return false
    }

    // Метод проверяет свободен ли интервал времени для Задачи
    private def checkTimeBusy(LocalDateTime dateTimeStart, LocalDateTime dateTimeEnd, idTask = null) {
        Boolean checkFind = this.tasks.findAll {
            it.value.dateTimeStart <= dateTimeEnd && it.value.dateTimeEnd >= dateTimeStart && it.key != idTask
        }.isEmpty()
        if (checkFind) {
            return false
        }
        println("Ошибка: временной интервал занят другой Задачей!")
        return true
    }

    // Метод проверяет свободен ли интервал времени для Действия
    private def checkTimeBusy(LocalDateTime dateTimeStart, LocalDateTime dateTimeEnd, int idTask, idAction) {
        if (this.tasks[idTask].dateTimeStart <= dateTimeStart && this.tasks[idTask].dateTimeEnd >= dateTimeEnd) {
            Boolean checkFind = this.tasks[idTask].actions.findAll {
                it.value.dateTimeStart <= dateTimeEnd && it.value.dateTimeEnd >= dateTimeStart && it.key != idAction
            }.isEmpty()
            if (checkFind) {
                return false
            }
            println("Ошибка: временной интервал занят другим Действием!")
            return true
        }
        println("Ошибка: временной интервал выходит за рамки Задачи!")
        return true
    }

    //Метод добавляет Задачу
    def add(String title, String description, LocalDateTime dateTimeStart, LocalDateTime dateTimeEnd) {
        if (checkDateCorrect(dateTimeStart, dateTimeEnd)) {
            return false
        }
        if (checkTimeBusy(dateTimeStart, dateTimeEnd)) {
            return false
        }
        int numberingActions = this.tasks.isEmpty() ? 1 : this.tasks.size() + 1
        this.tasks[numberingActions] = new Task(title, description, dateTimeStart, dateTimeEnd)
    }

    //Метод добавляет Действие
    def add(String title, String description, LocalDateTime dateTimeStart, LocalDateTime dateTimeEnd, int idTask) {
        if (checkDateCorrect(dateTimeStart, dateTimeEnd)) {
            return false
        }
        if (checkTimeBusy(dateTimeStart, dateTimeEnd, idTask, null)) {
            return false
        }
        int numberingActions = this.tasks[idTask].actions.isEmpty() ? 1 : this.tasks[idTask].actions.size() + 1
        this.tasks[idTask].actions[numberingActions] = new Action(title, description, dateTimeStart, dateTimeEnd)
    }

    // Метод изменяет Задачу
    def set(int idTask, Map params = [title: null, description: null, dateTimeStart: null, dateTimeEnd: null]) {
        params.dateTimeStart = params.dateTimeStart ?: this.tasks[idTask].dateTimeStart
        params.dateTimeEnd = params.dateTimeEnd ?: this.tasks[idTask].dateTimeEnd
        if (checkDateCorrect(params.dateTimeStart, params.dateTimeEnd)) {
            return false
        }
        if (checkTimeBusy(params.dateTimeStart, params.dateTimeEnd, idTask)) {
            return false
        }
        params.each { key, value ->
            this.tasks[idTask]."$key" = value ?: this.tasks[idTask]."$key"
        }
    }

    // Метод изменяет Действие
    def set(int idTask, int idAction, Map params = [title: null, description: null, dateTimeStart: null, dateTimeEnd: null]) {
        params.dateTimeStart = params.dateTimeStart ?: this.tasks[idTask].actions[idAction].dateTimeStart
        params.dateTimeEnd = params.dateTimeEnd ?: this.tasks[idTask].actions[idAction].dateTimeEnd
        if (checkDateCorrect(params.dateTimeStart, params.dateTimeEnd)) {
            return false
        }
        if (checkTimeBusy(params.dateTimeStart, params.dateTimeEnd, idTask, idAction)) {
            return false
        }
        params.each { key, value ->
            this.tasks[idTask].actions[idAction]."$key" = value ?: this.tasks[idTask].actions[idAction]."$key"
        }
    }

    // Метод отображает Задачи по выбранной дате
    def findTaskByDate(dateTime) {
        Map findTask = this.tasks.groupBy {
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
            object = this.tasks[idTask].actions
        } else {
            str = "Задачи:"
            object = this.tasks
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
        this.tasks.each { key, value ->
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
