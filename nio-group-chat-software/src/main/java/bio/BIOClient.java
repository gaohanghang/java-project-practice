package bio;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @Description:
 * @author: Gao Hang Hang
 * @create: 2019/04/29 08:40
 */
public class BIOClient {
    public static void main(String[] args) {
        try {
            Socket client = new Socket("localhost", 8081);
            OutputStream out = client.getOutputStream();
            out.write("jack要连接服务器".getBytes());
            out.close();
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
