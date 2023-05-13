package com.gsa.todo.todomanager.dao;

import com.gsa.todo.todomanager.helper.Helper;
import com.gsa.todo.todomanager.models.Todo;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDateTime;

public class TodoRowMapper implements RowMapper {
    @Override
    public Todo mapRow(ResultSet rs, int rowNum) throws SQLException {

        Todo todo = new Todo();

        todo.setId((rs.getInt("id")));
        todo.setTitle((rs.getString("title")));
        todo.setContent((rs.getString("content")));
        todo.setStatus((rs.getString("status")));
        try {
            todo.setAddedDate(Helper.parseDate((LocalDateTime) rs.getObject("addedDate")));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        try {
            todo.setToDoDate(Helper.parseDate((LocalDateTime) rs.getObject("addedDate")));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }


        return todo;
    }
}
