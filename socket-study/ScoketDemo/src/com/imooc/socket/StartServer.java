package src.com.imooc.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Description: 启动服务器
 * @author: Gao Hang Hang
 * @date 2019/02/09 14:52
 */
public class StartServer {
    public static void main(String[] args) {
        try {
            // 创建服务器Socket, 绑定指定端口
            ServerSocket serverSocket = new ServerSocket(8800);
            Socket socket = null;
            System.out.println("服务器即将启动，等待客户端连接*****");
            while (true) { //服务器循环监听客户端的连接请求
                // 开始监听，等待客户端连接
                socket = serverSocket.accept();
                // 多线程通信
                ServerThread thread = new ServerThread(socket);
                thread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
