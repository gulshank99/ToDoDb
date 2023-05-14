package com.gsa.todo.todomanager.cantrollers;

import com.gsa.todo.todomanager.models.Todo;
import com.gsa.todo.todomanager.services.TodoService;
import com.gsa.todo.todomanager.services.impl.TodoServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/todos")
public class TodoController {

    Logger logger=LoggerFactory.getLogger(TodoController.class);

    @Autowired
    private TodoService todoService;    // Changed TodoServiceImpl to his parent TodoService
    Random random=new Random();

    //Create Todo
    @PostMapping
    public ResponseEntity<Todo> createTodoHandler(@RequestBody Todo todo){

        // Created Mannual exception
       //  String str=null;
      //   logger.info("{}",str.length());
     //    Integer.parseInt("kdwfni2");
        int id =random.nextInt(99999);
         todo.setId(id);

        Date currentDate = new Date();               // Create Date with Sysytem Default
        logger.info("Current Date : {}",currentDate);
        todo.setAddedDate(currentDate);

        // create todo
        logger.info("Create Todo: ");

        // call service of  create todo
        Todo todo1=todoService.creatTodo(todo);

        return new ResponseEntity<>(todo1,HttpStatus.CREATED) ;
    }

    // get all Todo
    @GetMapping
    public ResponseEntity<List<Todo>> getAllTodoHandler(){
       List<Todo> allTodos = todoService.getAllTodos();
       return new ResponseEntity<>(allTodos,HttpStatus.OK);
    }

          //GEt single todo
    @GetMapping("/{todoId}")
    public ResponseEntity<Todo> getSingleTodoHandler(@PathVariable int todoId) throws ParseException {
      Todo todo=  todoService.getTodo(todoId);
      return ResponseEntity.ok(todo);
    }


    //Update Todo
    @PutMapping("/{todoId}")
    public ResponseEntity<Todo> updateTodoHandler(@RequestBody Todo todoWithNewDetails,@PathVariable int todoId){
       Todo todo = todoService.updateTodo(todoId,todoWithNewDetails);
       return ResponseEntity.ok(todo);
    }


    // Delete
    @DeleteMapping("/{todoId}")
    public ResponseEntity<String>  deleteTodoHandler(@PathVariable int todoId){
         todoService.deleteTodo(todoId);
        return ResponseEntity.ok("Succesfully Deeted");
    }

     // To HAndle exception In  class

//    @ExceptionHandler(NullPointerException.class)
//    public String nullPointerException(NullPointerException ex){
//        System.out.println(ex.getMessage());
//        System.out.println("Null Pointer Exception Generated");
//        return "Null Pointer Exception" +ex.getMessage();
//
//    }

//        @ExceptionHandler(value = {NullPointerException.class, NumberFormatException.class})
//    public String nullPointerException(Exception ex){
//        System.out.println(ex.getMessage());
//        System.out.println("Null Pointer Exception Generated");
// //       return "Null Pointer Exception" +ex.getMessage();
//
//    }
}
