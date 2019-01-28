package com.acts.rpc;

/**
 * @Description: 实现
 * @author: Gao Hang Hang
 * @date 2019/01/27 12:45
 */
public class HelloServiceImpl implements HelloService {

    @Override
    public String hello(String name) {
        return "Hello " + name;
    }
}
