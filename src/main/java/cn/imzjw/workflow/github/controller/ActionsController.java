package cn.imzjw.workflow.github.controller;

import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;


/**
 * @author https://blog.imzjw.cn
 * @date 2021/6/29 15:10
 */
@RestController
public class ActionsController {

    @Value("${upload.dir}")
    private String uploadDir;
    
    @PostMapping("actions")
    public ResponseEntity<String> actions(@RequestParam("file") MultipartFile file) {
        try {
            Path path = Paths.get(uploadDir + file.getOriginalFilename());
            Files.write(path, file.getBytes());
            return ResponseEntity.ok("File uploaded successfully: " + file.getOriginalFilename());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("File upload failed" + e);
        }
       
    }
    
    @RequestMapping("/upload")
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file) throws Exception {
        if (file.isEmpty()) {

            throw new Exception();
        }
        String filePath = System.getProperty("user.dir");


        File upload = new File(filePath + File.separator + "view" + File.separator);
        if (!upload.exists()) {
            upload.mkdirs();
        }
        File dest = new File(filePath + File.separator +  "view" +  File.separator + "index.html");
        try {
            file.transferTo(dest);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("File upload failed"+e);
        }
 return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("OK");
    }

    @GetMapping("/files/download/{filename}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable String filename) {
        try {
            Path path = Paths.get(System.getProperty("user.dir") + File.separator +  "view" +  File.separator + "index.html");
            byte[] data = Files.readAllBytes(path);
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename);
            return new ResponseEntity<>(data, headers, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.toString().getBytes());
        }
    }
}
