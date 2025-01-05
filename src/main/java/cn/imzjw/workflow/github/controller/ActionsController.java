package cn.imzjw.workflow.github.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author https://blog.imzjw.cn
 * @date 2021/6/29 15:10
 */
@RestController
public class ActionsController {

    @Value("${upload.dir}")
    private String uploadDir;
    
    @GetMapping("actions")
    public ResponseEntity<String> actions(@RequestParam("file") MultipartFile file) {
        try {
            Path path = Paths.get(uploadDir + file.getOriginalFilename());
            Files.write(path, file.getBytes());
            return ResponseEntity.ok("File uploaded successfully: " + file.getOriginalFilename());
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("File upload failed");
        }
    }
}
