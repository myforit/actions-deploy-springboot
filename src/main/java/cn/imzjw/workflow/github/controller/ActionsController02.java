package cn.imzjw.workflow.github.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author https://blog.imzjw.cn
 * @date 2021/6/29 15:10
 */
@RestController
public class ActionsController01 {

    @GetMapping("actionsDockerCompose12")
    public String actions() {
        return "采用DockerCompose方式部署项目，更加灵活的任务编排！！！";
    }
}
