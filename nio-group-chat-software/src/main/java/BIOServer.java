import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Description:
 * @author: Gao Hang Hang
 * @create: 2019/04/28 23:19
 */
public class BIOServer {

    ServerSocket server;

    public BIOServer(int port) {
        try {
            server = new ServerSocket(port);
            System.out.println("IO服务已经启动，监控的端口是：" + port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new BIOServer(8081).start();
    }

    public void start() {
        while (true) {
            try {
                Socket client = server.accept();
                InputStream is = client.getInputStream();
                byte[] buff = new byte[1024];
                int len = is.read(buff);
                if (len > 0) {
                    String msg = new String(buff,0,len);
                    System.out.println("服务端已接收客户端数据，数据内容是：" + msg);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
