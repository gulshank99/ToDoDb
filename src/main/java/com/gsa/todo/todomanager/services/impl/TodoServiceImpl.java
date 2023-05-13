package com.gsa.todo.todomanager.services.impl;


import com.gsa.todo.todomanager.cantrollers.TodoController;
import com.gsa.todo.todomanager.exception.ResourceNotFoundException;
import com.gsa.todo.todomanager.models.Todo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TodoServiceImpl implements com.gsa.todo.todomanager.services.TodoService {

    Logger logger= LoggerFactory.getLogger(TodoController.class);


    // Create todo method
    List<Todo> todos=new ArrayList<>();

    public Todo creatTodo(Todo todo){
        //create... fake database
        //If we have to take this in database we will change this
        todos.add(todo);
        logger.info("Todo {}",this.todos);

        return todo;

    }


    public List<Todo> getAllTodos() {
        return todos;

    }

    public Todo getTodo(int todoId) {
//      Todo todo = todos.stream().filter(t ->todoId ==t.getId()).findAny().get();
//    //  Todo todo = todos.stream()
        //                 .filter(t ->todoId ==t.getId())
        //                 .findAny()
        //                 .orElseThrow( ()-> new ResourceNotFoundException("Todo not found with given Id",HttpStatus.NOT_FOUND));

        // Without doing Lambda Expression
        Todo todo = null;
        for (Todo t : todos) {
            if (t.getId() == todoId) {
                todo = t;
                break;
            }
        }

        if (todo == null) {
            throw new ResourceNotFoundException("Todo not found with given Id", HttpStatus.NOT_FOUND);
        }


        logger.info("TODO: {}",todo);
      return todo;
    }

    public Todo updateTodo(int todoId,Todo todo){
        List<Todo> newUpdateList =todos.stream().map(t -> {
            if(t.getId()==todoId){
                //Perform Update
            t.setTitle(todo.getTitle());
            t.setContent(todo.getContent());
            t.setStatus(todo.getStatus());

                return t;
            }
            else return t;
                }).collect(Collectors.toList());

              todos=newUpdateList;          // garbage collector ko digest kr lega purana list ko
              todo.setId(todoId);
        return todo;
    }

    public void deleteTodo(int todoId) {
        List<Todo> newList= todos.stream().filter(t ->t.getId() != todoId ).collect(Collectors.toList());
        todos=newList;
    }
}
