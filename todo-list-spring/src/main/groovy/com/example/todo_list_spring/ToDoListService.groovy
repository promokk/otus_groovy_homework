package com.example.todo_list_spring

import org.springframework.data.repository.CrudRepository

import java.time.LocalDateTime

class ToDoListService {

    // Метод проверяет верно ли указана дата
    def checkDateCorrect(Object obj) {
        Boolean checkError = obj.dateTimeStart >= obj.dateTimeEnd
        if (checkError) {
            println("Ошибка: начало указано позже чем окончание!")
            return true
        }
        return false
    }

    // Метод проверяет свободен ли интервал времени для Задачи
    def checkTimeBusy(CrudRepository repository, Object task) {
        Boolean checkFind = repository.findAll().findAll() {
            it.dateTimeStart <= task.dateTimeEnd && it.dateTimeEnd >= task.dateTimeStart && it.id != task.id
        }.isEmpty()
        if (checkFind) {
            return false
        }
        println("Ошибка: временной интервал занят другой Задачей!")
        return true
    }

    // Метод проверяет свободен ли интервал времени для Действия
    def checkTimeBusy(Object task, Object action) {
        if (task.dateTimeStart <= action.dateTimeStart && task.dateTimeEnd >= action.dateTimeEnd) {
            Boolean checkFind = task.actions.findAll() {
                it.dateTimeStart <= action.dateTimeEnd && it.dateTimeEnd >= action.dateTimeStart && it.id != action.id
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
}
