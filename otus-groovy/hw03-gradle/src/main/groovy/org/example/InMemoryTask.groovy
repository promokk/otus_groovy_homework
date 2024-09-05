package org.example

import java.time.LocalDateTime

class InMemoryTask {
    Map tasks = [:]

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
    void add(String title, String description, LocalDateTime dateTimeStart, LocalDateTime dateTimeEnd) {
        if (checkDateCorrect(dateTimeStart, dateTimeEnd)) {
            return
        }
        if (checkTimeBusy(dateTimeStart, dateTimeEnd)) {
            return
        }
        int numberingActions = this.tasks.isEmpty() ? 1 : this.tasks.size() + 1
        this.tasks[numberingActions] = new Task(title, description, dateTimeStart, dateTimeEnd)
    }

    //Метод добавляет Действие
    void add(String title, String description, LocalDateTime dateTimeStart, LocalDateTime dateTimeEnd, int idTask) {
        if (checkDateCorrect(dateTimeStart, dateTimeEnd)) {
            return
        }
        if (checkTimeBusy(dateTimeStart, dateTimeEnd, idTask, null)) {
            return
        }
        int numberingActions = this.tasks[idTask].actions.isEmpty() ? 1 : this.tasks[idTask].actions.size() + 1
        this.tasks[idTask].actions[numberingActions] = new Action(title, description, dateTimeStart, dateTimeEnd)
    }

    // Метод изменяет Задачу
    void set(int idTask, Map params = [title: null, description: null, dateTimeStart: null, dateTimeEnd: null]) {
        params.dateTimeStart = params.dateTimeStart ?: this.tasks[idTask].dateTimeStart
        params.dateTimeEnd = params.dateTimeEnd ?: this.tasks[idTask].dateTimeEnd
        if (checkDateCorrect(params.dateTimeStart, params.dateTimeEnd)) {
            return
        }
        if (checkTimeBusy(params.dateTimeStart, params.dateTimeEnd, idTask)) {
            return
        }
        params.each { key, value ->
            this.tasks[idTask]."$key" = value ?: this.tasks[idTask]."$key"
        }
    }

    // Метод изменяет Действие
    void set(int idTask, int idAction, Map params = [title: null, description: null, dateTimeStart: null, dateTimeEnd: null]) {
        params.dateTimeStart = params.dateTimeStart ?: this.tasks[idTask].actions[idAction].dateTimeStart
        params.dateTimeEnd = params.dateTimeEnd ?: this.tasks[idTask].actions[idAction].dateTimeEnd
        if (checkDateCorrect(params.dateTimeStart, params.dateTimeEnd)) {
            return
        }
        if (checkTimeBusy(params.dateTimeStart, params.dateTimeEnd, idTask, idAction)) {
            return
        }
        params.each { key, value ->
            this.tasks[idTask].actions[idAction]."$key" = value ?: this.tasks[idTask].actions[idAction]."$key"
        }
    }
}
