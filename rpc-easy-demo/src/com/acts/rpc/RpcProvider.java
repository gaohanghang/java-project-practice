package com.acts.rpc;

/**
 * @Description: 提供者
 * @author: Gao Hang Hang
 * @date 2019/01/27 12:46
 */
public class RpcProvider {
    public static void main(String[] args) throws Exception {
        HelloService service = new HelloServiceImpl();
        RpcFramework.export(service, 1234);
    }
}
