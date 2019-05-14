package cn.attackme.myuploader.controller;

import cn.attackme.myuploader.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 秒传
 */
@RestController
@RequestMapping("/QuickUpload")
@CrossOrigin
@Api(tags = "文件秒传相关接口", description = "提供文件秒传相关的 Rest API")
public class QuickUploadController {
    @Autowired
    private FileService fileService;

    @ApiOperation("文件秒传接口")
    @GetMapping("/")
    public boolean upload(String md5) {
        return fileService.checkMd5(md5);
    }
}
