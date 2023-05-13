package com.gsa.todo.todomanager.dao;

import com.gsa.todo.todomanager.helper.Helper;
import com.gsa.todo.todomanager.models.Todo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

//@Component
@Repository
public class TodoDao {

   // @Autowired
    private JdbcTemplate template;

    Logger logger = LoggerFactory.getLogger(TodoDao.class);

    public TodoDao(@Autowired JdbcTemplate template) {
        this.template = template;
        // Create table if it does not exist
        String createTable="create table IF NOT EXISTS todos(id int primary key,title varchar(100) not null,content varchar(4500),status varchar(100) not null,addedDate datetime,toDoDate datetime )";
        int update =template.update(createTable);
        logger.info("Table Created:{}",update);
    }

    public JdbcTemplate getTemplate() {
        return template;
    }


    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }

    // Save todo in db
    public Todo saveTodo(Todo todo){
        String insertQuery="insert into todos(id,title,content,status,addedDate,toDoDate) values(?,?,?,?,?,?)";
        // we will provide values dynamically here ? is placeholder Here values comes dynamic
       int rows= template.update(insertQuery,todo.getId(),todo.getTitle(),todo.getContent(),todo.getStatus(),todo.getAddedDate(),todo.getToDoDate());

       logger.info("JDBC OPERATION :{}",rows);

       return todo;
    }

    //Get single todo from Db

    public Todo getTodo(int id) throws ParseException {
        String query ="select * from todos WHERE id=?";
     //  Map<String,Object> todoData = template.queryForMap(query,id);
        Todo todo =(Todo) template.queryForObject(query, new TodoRowMapper(),id);

 //       logger.info("Todo:{}",todoData);
//      Todo todo = new Todo();
//      todo.setId((int)(todoData.get("id")));
//      todo.setTitle(((String)todoData.get("title")));
//      todo.setContent(((String)todoData.get("content")));
//      todo.setStatus(((String)todoData.get("status")));
//      todo.setAddedDate(Helper.parseDate((LocalDateTime) todoData.get("addedDate")));
//        todo.setToDoDate(Helper.parseDate((LocalDateTime) todoData.get("addedDate")));

   return todo;
    }

    //Get all todo from db

    public List<Todo> getAllTodos(){
        String query="select * from todos";
       // List<Map<String,Object>> maps=template.queryForList(query);

       List<Todo> todo = template.query(query,new TodoRowMapper());


//      List<Todo> todos=  maps.stream().map((map)->{
//
//            Todo todo = new Todo();
//
//            todo.setId((int)(map.get("id")));
//            todo.setTitle(((String)map.get("title")));
//            todo.setContent(((String)map.get("content")));
//            todo.setStatus(((String)map.get("status")));
//            try {
//                todo.setAddedDate(Helper.parseDate((LocalDateTime) map.get("addedDate")));
//            } catch (ParseException e) {
//                throw new RuntimeException(e);
//            }
//            try {
//                todo.setToDoDate(Helper.parseDate((LocalDateTime) map.get("addedDate")));
//            } catch (ParseException e) {
//                throw new RuntimeException(e);
//            }
//
//            return todo;
//
//        }).collect(Collectors.toList());

        return todo;
    }

    //Update todo from database
     public  Todo updateTodo(int id,Todo newTodo){
        String query = "update todos set title=?,content=?,status=?,addedDate=?,todoDate=? WHERE id=?";
       int update= template.update(query,newTodo.getTitle(),newTodo.getContent(),newTodo.getStatus(),newTodo.getAddedDate(),newTodo.getToDoDate(),id);
        logger.info("Updated:{}",update);
        newTodo.setId(id);
        return newTodo;
     }


    //Delete todo from Database

    public void deleteTodos(int id ){
        String query = "delete from todos WHERE id=?";
        int update = template.update(query,id);
        logger.info("Deleted {}",update);
    }



    public void deleteMultipleTodos(int ids[] ){
        String query = "delete from todos WHERE id=?";
        // using For or ForEach
        // template.batchUpdate(query,id);
       int[] ints= template.batchUpdate(query, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                int id = ids[i];
                ps.setInt(1,id);
            }

            @Override
            public int getBatchSize() {
                return ids.length;
            }
        });

        for(int i :ints){
            logger.info("Deleted : {}",i);
        }
    }
}
