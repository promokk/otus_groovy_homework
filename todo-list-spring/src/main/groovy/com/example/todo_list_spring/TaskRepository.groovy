package com.example.todo_list_spring

import org.springframework.data.repository.CrudRepository

interface TaskRepository  extends CrudRepository<Task, String> {}
