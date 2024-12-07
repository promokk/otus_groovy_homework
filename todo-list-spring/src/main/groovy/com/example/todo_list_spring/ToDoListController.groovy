package com.example.todo_list_spring

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class ToDoListController {
    private final TaskRepository taskRepository
    private final ActionRepository actionRepository
    private final ToDoListService toDoListService = new ToDoListService()

    ToDoListController(ActionRepository actionRepository, TaskRepository taskRepository) {
        this.taskRepository = taskRepository
        this.actionRepository = actionRepository
    }

    @GetMapping("/tasks")
    Iterable<Task> getTasks() {
        return taskRepository.findAll()
    }

    @GetMapping("/tasks/{id}")
    Optional<Task> getTaskById(@PathVariable("id") String id) {
        return taskRepository.findById(id)
    }

    @GetMapping("/actions")
    Iterable<Action> getActions() {
        return actionRepository.findAll()
    }

    @GetMapping("/actions/{id}")
    Optional<Action> getActionById(@PathVariable("id") String id) {
        return actionRepository.findById(id)
    }

    @PostMapping("/tasks/add")
    ResponseEntity postTask(@RequestBody Task task) {
        if (toDoListService.checkDateCorrect(task)) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST)
        }
        if (toDoListService.checkTimeBusy(taskRepository, task)) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST)
        }
        return new ResponseEntity(taskRepository.save(task), HttpStatus.CREATED)
    }

    @PostMapping("/tasks/{id}/addAction")
    ResponseEntity postAction(@PathVariable("id") String id, @RequestBody Action action) {
        Task task
        if (taskRepository.existsById(id)) {
            task = taskRepository.findById(id).get()
        } else {
            return new ResponseEntity(new CustomMessage("Задача отсутствует"), HttpStatus.NOT_FOUND)
        }
        if (toDoListService.checkDateCorrect(action)) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST)
        }
        if (toDoListService.checkTimeBusy(task, action)) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST)
        }

        action.setTask(task)
        return new ResponseEntity(actionRepository.save(action), HttpStatus.CREATED)
    }

    @PutMapping("/tasks/{id}/edit")
    ResponseEntity putTask(@PathVariable("id") String id, @RequestBody Task task) {
        if (toDoListService.checkDateCorrect(task)) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST)
        }
        if (toDoListService.checkTimeBusy(taskRepository, task)) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST)
        }
        return (taskRepository.existsById(id)) ?
                new ResponseEntity(taskRepository.save(task), HttpStatus.OK) :
                new ResponseEntity(taskRepository.save(task), HttpStatus.CREATED)
    }

    @PutMapping("/tasks/{idTask}/actionEdit/{idAction}")
    ResponseEntity putAction(
            @PathVariable("idTask") String idTask,
            @PathVariable("idAction") String idAction,
            @RequestBody Action action)
    {
        Task task
        if (taskRepository.existsById(idTask)) {
            task = taskRepository.findById(idTask).get()
        } else {
            return new ResponseEntity(new CustomMessage("Задача отсутствует"), HttpStatus.NOT_FOUND)
        }
        if (!task.actions.contains(actionRepository.findById(idAction).orElse(null))) {
            return new ResponseEntity(new CustomMessage("Действие отсутствует"), HttpStatus.NOT_FOUND)
        }
        if (toDoListService.checkDateCorrect(action)) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST)
        }
        if (toDoListService.checkTimeBusy(task, action)) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST)
        }

        Action actionOld = actionRepository.findById(idAction).get()
        actionOld.setTitle(action.title)
        actionOld.setDescription(action.description)
        actionOld.setDateTimeStart(action.dateTimeStart)
        actionOld.setDateTimeEnd(action.dateTimeEnd)
        return new ResponseEntity(actionRepository.save(actionOld), HttpStatus.OK)
    }

    @DeleteMapping("/tasks/{id}")
    void deleteTask(@PathVariable("id") String id) {
        taskRepository.deleteById(id)
    }

    @DeleteMapping("/actions/{id}")
    void deleteAction(@PathVariable("id") String id) {
        actionRepository.deleteById(id)
    }
}
