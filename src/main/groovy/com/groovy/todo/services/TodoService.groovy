package com.groovy.todo.services

import com.groovy.todo.entities.TodoEntity
import com.groovy.todo.repositories.TodoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import javax.persistence.EntityNotFoundException

@Service
class TodoService {
    @Autowired
    TodoRepository todoRepository

    List findAll(){
        todoRepository.findAll().asList()
    }

    TodoEntity findById(long id){
        todoRepository.findById(id).orElseThrow({
            new EntityNotFoundException('Entity not found to given id')
        })
    }

    TodoEntity save(TodoEntity todoEntity) {
        todoRepository.save(todoEntity)
    }

    TodoEntity update(long id, TodoEntity todoEntity){
        TodoEntity persisted = findById(id)

        persisted.with {
            name = todoEntity.name
            description = todoEntity.description
            priority = todoEntity.priority
            author = todoEntity.author
        }

        todoRepository.save(persisted)
    }

    TodoEntity deleteById(long id){
        def todo = findById(id)
        todoRepository.delete(todo)
        todo
    }
}