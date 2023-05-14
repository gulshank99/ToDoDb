package com.gsa.todo.todomanager.dao;

import com.gsa.todo.todomanager.models.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo,Integer> {
    
}
