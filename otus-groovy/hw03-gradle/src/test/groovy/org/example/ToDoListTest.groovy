package org.example

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

import java.time.LocalDateTime


class ToDoListTest {
    ToDoList toDoList = new ToDoList()

    @Test
    void testAdd() {
        toDoList.add(
                "Задача 1",
                "Это задача",
                LocalDateTime.of(2024, 8, 26, 01, 20),
                LocalDateTime.of(2024, 8, 28, 01, 20)
        )
        toDoList.add(
                "Задача 2",
                "Это задача",
                LocalDateTime.of(2024, 8, 29, 10, 00),
                LocalDateTime.of(2024, 8, 29, 12, 00)
        )
        toDoList.add(
                "Действие 1-1",
                "Это действие",
                LocalDateTime.of(2024, 8, 26, 22, 23),
                LocalDateTime.of(2024, 8, 26, 23, 00),
                1
        )
        toDoList.add(
                "Действие 1-2",
                "Это действие",
                LocalDateTime.of(2024, 8, 26, 12, 30),
                LocalDateTime.of(2024, 8, 26, 14, 00),
                1
        )
        toDoList.add(
                "Действие 1-3",
                "Это действие",
                LocalDateTime.of(2024, 8, 26, 10, 00),
                LocalDateTime.of(2024, 8, 26, 11, 00),
                1
        )

        String result01 = toDoList.tasks.size()
        String result02 = toDoList.tasks[1].actions.size()

        Assertions.assertEquals('2', result01.toString())
        Assertions.assertEquals('3', result02.toString())
    }

    @Test
    void testSet() {
        toDoList.add(
                "Задача 1",
                "Это задача",
                LocalDateTime.of(2024, 8, 26, 01, 20),
                LocalDateTime.of(2024, 8, 28, 01, 20)
        )
        toDoList.add(
                "Действие 1-1",
                "Это действие",
                LocalDateTime.of(2024, 8, 26, 22, 23),
                LocalDateTime.of(2024, 8, 26, 23, 00),
                1
        )

        toDoList.set(1, [title: "Новая Задача 1"])
        toDoList.set(1, 1, [title: "Новое Действие 1-1"])

        String result01 = toDoList.tasks[1].title
        String result02 = toDoList.tasks[1].actions[1].title

        Assertions.assertEquals('Новая Задача 1', result01)
        Assertions.assertEquals('Новое Действие 1-1', result02)
    }

    @Test
    void testCheckDateCorrect() {
        def result01 = toDoList.add(
                "Задача 1",
                "Это задача",
                LocalDateTime.of(2024, 8, 26, 01, 20),
                LocalDateTime.of(2024, 8, 25, 01, 20)
        )

        Assertions.assertEquals('false', result01.toString())
        Assertions.assertEquals('0', toDoList.tasks.size().toString())
    }

    @Test
    void testCheckTimeBusy() {
        toDoList.add(
                "Задача 1",
                "Это задача",
                LocalDateTime.of(2024, 8, 26, 01, 20),
                LocalDateTime.of(2024, 8, 27, 01, 20)
        )
        def result01 = toDoList.add(
                "Задача 2",
                "Это задача",
                LocalDateTime.of(2024, 8, 26, 10, 00),
                LocalDateTime.of(2024, 8, 26, 12, 00)
        )
        toDoList.add(
                "Действие 1-1",
                "Это действие",
                LocalDateTime.of(2024, 8, 26, 14, 00),
                LocalDateTime.of(2024, 8, 26, 16, 00),
                1
        )
        def result02 = toDoList.add(
                "Действие 1-2",
                "Это действие",
                LocalDateTime.of(2024, 8, 26, 15, 30),
                LocalDateTime.of(2024, 8, 26, 17, 00),
                1
        )
        def result03 = toDoList.add(
                "Действие 1-2",
                "Это действие",
                LocalDateTime.of(2024, 8, 27, 1, 00),
                LocalDateTime.of(2024, 8, 27, 4, 00),
                1
        )

        Assertions.assertEquals('false', result01.toString())
        Assertions.assertEquals('false', result02.toString())
        Assertions.assertEquals('false', result03.toString())
        Assertions.assertEquals('1', toDoList.tasks.size().toString())
        Assertions.assertEquals('1', toDoList.tasks[1].actions.size().toString())
    }
}
