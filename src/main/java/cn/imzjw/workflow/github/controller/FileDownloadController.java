package cn.imzjw.workflow.github.controller;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


import java.io.File;


@RestController
public class FileDownloadController {


    private static final String FILE_DIRECTORY = "/home/ap/huaqing/file/a.txt";
//    private static final String FILE_DIRECTORY = "D:\\fileshare\\c.txt";


    @GetMapping("/download/")
    public ResponseEntity<FileSystemResource> downloadFile() {
        File file = new File(FILE_DIRECTORY);

        if (!file.exists()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }


        FileSystemResource resource = new FileSystemResource(file);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + file.getName());

        return ResponseEntity.ok()
                .headers(headers)
                .body(resource);
    }
}