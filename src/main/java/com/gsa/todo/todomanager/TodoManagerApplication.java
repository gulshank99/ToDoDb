package com.gsa.todo.todomanager;

import com.gsa.todo.todomanager.dao.TodoDao;
import com.gsa.todo.todomanager.models.Todo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Date;
import java.util.List;

@SpringBootApplication
public class TodoManagerApplication implements CommandLineRunner {

	Logger logger= LoggerFactory.getLogger(TodoManagerApplication.class);
	@Autowired
	private TodoDao todoDao;
	public static void main(String[] args) {


		SpringApplication.run(TodoManagerApplication.class, args);

		System.out.println("Getting Started");

	}

	@Override
	public void run(String... args) throws Exception {
//		System.out.println("Started");
//		JdbcTemplate template =todoDao.getTemplate();
//		logger.info("Template Object : {}",template);
//
		//Todo todo = new Todo();

//		todo.setId(126);
//		todo.setTitle("Test purpose");
//		todo.setContent("Java ");
//		todo.setStatus("INPROCESS");
//		todo.setAddedDate(new Date());
//		todo.setToDoDate(new Date());
//
//		todoDao.saveTodo(todo);
//
           // Update
	   //Todo todo =	 todoDao.getTodo(123);
	//	logger.info("Todo:{}",todo);

//		todo.setTitle("jgugu");
//		todo.setContent("nneb");
//		todo.setStatus("Pending");
//		todo.setAddedDate(new Date());
//		todo.setToDoDate(new Date());
//
//		todoDao.updateTodo(126,todo);

	//	List<Todo> allTodos = todoDao.getAllTodos();
	//	logger.info("All TODos :{}",allTodos);


		// Delete
		//todoDao.deleteTodos(126);

		// Delete multiple
		//todoDao.deleteMultipleTodos(new int[]{126,125});


	}
}
