package com.gsa.todo.todomanager.services.impl;

import com.gsa.todo.todomanager.dao.TodoDao;
import com.gsa.todo.todomanager.models.Todo;
import com.gsa.todo.todomanager.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;
@Service
@Primary
public class DaoTodoServiceImpl implements TodoService {
    @Autowired
    private TodoDao todoDao;
    @Override
    public Todo creatTodo(Todo todo) {
        return todoDao.saveTodo(todo);
    }

    @Override
    public List<Todo> getAllTodos() {
        return todoDao.getAllTodos();
    }

    @Override
    public Todo getTodo(int todoId) throws ParseException {
        return todoDao.getTodo(todoId);
    }

    @Override
    public Todo updateTodo(int todoId, Todo todo) {
        return todoDao.updateTodo(todoId,todo);
    }

    @Override
    public void deleteTodo(int todoId) {
      todoDao.deleteTodos(todoId);
    }
}
