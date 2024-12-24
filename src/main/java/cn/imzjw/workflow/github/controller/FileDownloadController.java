package cn.imzjw.workflow.github.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(FileDownloadController.class);

    @GetMapping("/download/")
    public ResponseEntity<FileSystemResource> downloadFile() {

        try {
            // 可能抛出异常的代码块
            File file = new File(FILE_DIRECTORY);
            logger.info("77777777777777777777!");
            if (!file.exists()) {
                logger.info("666666666666666666666!");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }


            FileSystemResource resource = new FileSystemResource(file);
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + file.getName());

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(resource);
        } catch (Exception e) {
            // 处理异常的代码块
            logger.info(e.getLocalizedMessage() + e + "xxxxxxxxxxxxxxxxxxxxxxxxx");
            return null;
        }

    }
}