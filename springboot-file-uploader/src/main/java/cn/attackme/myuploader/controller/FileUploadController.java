package cn.attackme.myuploader.controller;

import cn.attackme.myuploader.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 文件上传
 */
@RestController
@RequestMapping("/File")
@CrossOrigin
@Api(tags = "文件上传相关接口", description = "提供文件上传相关的 Rest API")
public class FileUploadController {
    @Autowired
    private FileService fileService;

    @ApiOperation("文件上传接口")
    @PostMapping("/")
    public void upload(String name,
                       String md5,
                       MultipartFile file) throws IOException {
        fileService.upload(name, md5,file);
    }
}
