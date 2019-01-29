package com.gaohanghang.easyexceldemo.controller;

import com.gaohanghang.easyexceldemo.service.UserExcelImportService;
import entity.Result;
import entity.StatusCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.io.IOException;

/**
 * @Description:
 * @author: Gao Hang Hang
 * @date 2019/01/28 15:42
 */
@Controller
@RequestMapping(value = "/user")
@EnableSwagger2 // 启动swagger注解
@Api(description = "关于user的操作", tags = "user")
public class UserController {

    @Autowired
    private UserExcelImportService userExcelImportService;

    @ApiOperation(value = "excel数据导入到数据中")
    @PostMapping("/excel/upload")
    @ResponseBody
    public Result excelUpload(@RequestParam("file") MultipartFile file) throws IOException {
        userExcelImportService.excelImport(file);
        return new Result(true, StatusCode.OK, "excel导入成功");
    }

    @ApiOperation(value = "导出全部数据到excel")
    @GetMapping("/excel/download")
    @ResponseBody
    public Result excelDownload() throws IOException {
        userExcelImportService.download();
        return new Result(true, StatusCode.OK, "excel下载成功");
    }

    @ApiOperation("分页导出数据到excel")
    @GetMapping("/excel/download/{page}/{size}")
    @ResponseBody
    public Result pageDownload(@PathVariable int page, @PathVariable int size) throws IOException {
        if (size <= 0) {
            return new Result(false, StatusCode.ERROR, "size输入不正确");
        }
        userExcelImportService.pageDownload(page, size);
        return new Result(true, StatusCode.OK, "excel下载成功");
    }

    @ApiOperation("测试统一异常处理")
    @GetMapping("/exception")
    @ResponseBody
    public Result pageDownload() throws IOException {
        throw new IllegalArgumentException();
    }
}
