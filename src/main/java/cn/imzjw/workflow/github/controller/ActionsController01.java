package cn.imzjw.workflow.github.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author https://blog.imzjw.cn
 * @date 2021/6/29 15:10
 */
@RestController
public class ActionsController01 {

    @GetMapping("actionsNew")
    public String actions() {
        return "GitHub Actions Docker Pipe Line" +
                "这种镜像的方式整体打包部署相对速度快" +
                "简洁，很好移植，也很轻量";
    }
}
