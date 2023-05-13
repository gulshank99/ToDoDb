package com.gsa.todo.todomanager.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Date;


public class Helper {

    public static Date parseDate(LocalDateTime localDateTime) throws ParseException {
      //  SimpleDateFormat simpleDateFormat=new SimpleDateFormat();
      //  Instant instant = localDateTime.toInstant(ZoneOffset.UTC);
        Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
        Date date = Date.from(instant);
        return date;
    }
}
