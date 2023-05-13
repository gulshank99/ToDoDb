package com.gsa.todo.todomanager.cantrollers;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Arrays;

@RestController
@RequestMapping("/file")
public class FileController {


    Logger logger= LoggerFactory.getLogger(TodoController.class);

    @PostMapping("/single")
    public String uploadingSingle(@RequestParam("image") MultipartFile file){
        logger.info("Name : {}",file.getName());
        logger.info("ContentType:{}",file.getContentType());
        logger.info("File Name:{}",file.getOriginalFilename());
        logger.info("File Size:{}",file.getSize());
        // file.getInputStream()

        return "File Test";
    }

    @PostMapping("/multiple")
    public String uploadingMultiple(@RequestParam("files") MultipartFile[] files){
        Arrays.stream(files).forEach(file -> {
            logger.info("Name : {}",file.getName());
            logger.info("ContentType:{}",file.getContentType());
            logger.info("File Name:{}",file.getOriginalFilename());
            logger.info("File Size:{}",file.getSize());
            // file.getInputStream()
        });

        return "Multiple File Test";
    }

    @GetMapping("/serve-image")
    public void serveImageHandler(HttpServletResponse response){
         try {

             InputStream fileInputStream = new FileInputStream("images/download.png");
             response.setContentType(MediaType.IMAGE_PNG_VALUE);
             StreamUtils.copy(fileInputStream,response.getOutputStream());
         }catch (Exception e){
             e.printStackTrace();
         }

    }


}
