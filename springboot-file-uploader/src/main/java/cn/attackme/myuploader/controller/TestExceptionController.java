package cn.attackme.myuploader.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static cn.attackme.myuploader.utils.LogUtils.logToFile;

/**
 * 测试日志功能
 */
@RestController
@RequestMapping("/Ex")
@Api(tags = "测试日志功能接口", description = "提供测试日志功能的 Rest API")
public class TestExceptionController {
    /**
     * 测试日志切面
     * @return
     */
    @ApiOperation("测试日志切面的接口")
    @GetMapping("/aspect")
    public int aspect() {
        int i = 1 / 0;
        return i;
    }

    /**
     * 测试日志util
     */
    @ApiOperation("测试日志util的接口")
    @GetMapping("/util")
    public void util() {
        try {
            System.out.println(1/0);
        } catch (Exception e) {
            logToFile(e);
        }
    }
}
