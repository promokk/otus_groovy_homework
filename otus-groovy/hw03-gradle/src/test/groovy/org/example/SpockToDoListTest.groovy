package org.example

import spock.lang.Specification

import java.time.LocalDateTime

class SpockToDoListTest extends Specification {
    InMemoryTask inMemoryTask
    ToDoList toDoList

    void setup() {
        inMemoryTask = new InMemoryTask()
        toDoList = new ToDoList(inMemoryTask)
    }

    void cleanup() {
        inMemoryTask = null
        toDoList = null
    }

    def "TestAdd"() {
        given:
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

        when:
        def test01 = inMemoryTask.tasks.size()
        def test02 = inMemoryTask.tasks[1].actions.size()

        then:
        test01 == 2
        test02 == 3

    }

    def "TestSet"() {
        given:
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

        when:
        def test01 = inMemoryTask.tasks[1].title
        def test02 = inMemoryTask.tasks[1].actions[1].title

        then:
        test01 == 'Новая Задача 1'
        test02 == 'Новое Действие 1-1'

    }

    def "TestCheckDateCorrect"() {
        given:
        toDoList.add(
                "Задача 1",
                "Это задача",
                LocalDateTime.of(2024, 8, 26, 01, 20),
                LocalDateTime.of(2024, 8, 25, 01, 20)
        )

        when:
        def test01 = inMemoryTask.tasks.size()

        then:
        test01 == 0
    }

    def "TestCheckTimeBusy"() {
        given:
        toDoList.add(
                "Задача 1",
                "Это задача",
                LocalDateTime.of(2024, 8, 26, 01, 20),
                LocalDateTime.of(2024, 8, 27, 01, 20)
        )
        toDoList.add(
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
        toDoList.add(
                "Действие 1-2",
                "Это действие",
                LocalDateTime.of(2024, 8, 26, 15, 30),
                LocalDateTime.of(2024, 8, 26, 17, 00),
                1
        )
        toDoList.add(
                "Действие 1-2",
                "Это действие",
                LocalDateTime.of(2024, 8, 27, 1, 00),
                LocalDateTime.of(2024, 8, 27, 4, 00),
                1
        )

        when:
        def test01 = inMemoryTask.tasks.size()
        def test02 = inMemoryTask.tasks[1].actions.size()

        then:
        test01 == 1
        test02 == 1
    }
}
