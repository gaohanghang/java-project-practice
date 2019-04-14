package com.gaohanghang.springbootgephi.controller;


import com.gaohanghang.springbootgephi.entity.Result;
import com.gaohanghang.springbootgephi.entity.StatusCode;
import com.gaohanghang.springbootgephi.service.RelationshipService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.io.IOException;

/**
 * @Description:
 * @author: Gao Hang Hang
 * @date 2019/01/28 15:42
 */
@RestController
@RequestMapping(value = "/relationship")
@EnableSwagger2 // 启动swagger注解
@Api(description = "关于relationship的操作", tags = "relationship")
public class RelationShipController {

    @Autowired
    private RelationshipService relationshipService;

    @ApiOperation(value = "excel数据导入到数据中")
    @PostMapping("/excel/upload")
    @ResponseBody
    public Result excelUpload(@RequestParam("file") MultipartFile file) throws IOException {
        relationshipService.excelImport(file);
        return new Result(true, StatusCode.OK, "excel导入成功");
    }

    @ApiOperation("测试统一异常处理")
    @GetMapping("/exception")
    @ResponseBody
    public Result pageDownload() throws IOException {
        throw new IllegalArgumentException();
    }

    @ApiOperation("测试")
    @GetMapping("/test")
    @ResponseBody
    public Result test() throws IOException {
        return new Result(true, StatusCode.OK, "test");
    }

    @GetMapping
    public String getJson() throws IOException {
        return relationshipService.getJson();
    }


}
