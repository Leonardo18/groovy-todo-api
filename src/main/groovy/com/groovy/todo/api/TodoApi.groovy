package com.groovy.todo.api

import com.groovy.todo.api.dto.TodoDto
import com.groovy.todo.api.dto.TodoResponseDto
import com.groovy.todo.entities.TodoEntity
import com.groovy.todo.services.TodoService
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*

import javax.validation.Valid

@RestController
@RequestMapping('todo')
@Transactional
class TodoApi {
    @Autowired
    TodoService todoService

    @GetMapping('')
    List findAll(){
        todoService.findAll() as List<TodoResponseDto>
    }

    @GetMapping('{id}')
    TodoResponseDto findbyId(@PathVariable long id){
        convertTodoEntityToResponseDto(todoService.findById(id))
    }

    @PostMapping('')
    TodoResponseDto create(@Valid @RequestBody TodoDto todoDto){
        convertTodoEntityToResponseDto(todoService.save(convertTodoDtoToEntity(todoDto)))
    }

    @PutMapping('{id}')
    TodoResponseDto update(@PathVariable long id, @Valid @RequestBody TodoDto todoDto){
        convertTodoEntityToResponseDto(todoService.update(id, convertTodoDtoToEntity(todoDto)))
    }

    @DeleteMapping('{id}')
    TodoResponseDto delete(@PathVariable long id){
        convertTodoEntityToResponseDto(todoService.deleteById(id))
    }

    static def convertTodoDtoToEntity(TodoDto todoDto){
        ModelMapper modelMapper = new ModelMapper()
        TodoEntity todoEntity = modelMapper.map(todoDto, TodoEntity.class)
    }

    static def convertTodoEntityToResponseDto(TodoEntity todoEntity){
        ModelMapper modelMapper = new ModelMapper()
        TodoResponseDto todoResponseDto = modelMapper.map(todoEntity, TodoResponseDto.class)
    }
}
