package com.groovy.todo.repositories

import com.groovy.todo.entities.TodoEntity
import org.springframework.data.repository.CrudRepository

interface TodoRepository extends CrudRepository<TodoEntity, Long> {}