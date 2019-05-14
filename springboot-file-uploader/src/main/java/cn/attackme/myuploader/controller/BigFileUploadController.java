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
 * 大文件上传
 */
@RestController
@RequestMapping("/BigFile")
@CrossOrigin
@Api(tags = "大文件上传相关接口", description = "提供大文件上传相关的 Rest API")
public class BigFileUploadController {
    @Autowired
    private FileService fileService;


    /**
     *
     * @param name      文件名
     * @param md5       MD5
     * @param size
     * @param chunks    文件分块数
     * @param chunk     文件分块序号
     * @param file      文件
     * @throws IOException
     */
    @ApiOperation("大文件上传接口")
    @PostMapping("/")
    public void upload(String name,
                       String md5,
                       Long size,
                       Integer chunks,
                       Integer chunk,
                       MultipartFile file) throws IOException {
        if (chunks != null && chunks != 0) {
            fileService.uploadWithBlock(name, md5,size,chunks,chunk,file);
        } else {
            fileService.upload(name, md5,file);
        }
    }
}
