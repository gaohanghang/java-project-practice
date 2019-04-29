package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @Description:
 * @author: Gao Hang Hang
 * @create: 2019/04/29 21:42
 */
public class ClinetSocket {

    Selector selector;

    SocketChannel client;

    public static void main(String[] args) {
        ClinetSocket ss = new ClinetSocket();
        ss.start();
        ss.bindEvent();
    }

    public void start() {
        try {
            client = SocketChannel.open();
            client.configureBlocking(false);
            client.connect(new InetSocketAddress("localhost", 8080));
            selector = Selector.open();
            client.register(selector, SelectionKey.OP_CONNECT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void bindEvent() {
        while (true) {
            try {
                int i = selector.select(); // 监视器通道

                if (i == 0) {
                    continue;
                }

                /*
                    SelectionKey 可以把它理解成某个channel通道的封装
                    可以把它认为是一个变形的通道
                 */
                Set<SelectionKey> keys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = keys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();

                    if (key.isConnectable()) { // key可接收
                        if (client.isConnectionPending()) {
                            client.finishConnect();
                            client.register(selector, SelectionKey.OP_WRITE); // 写时间触发
                        }
                    } else if (key.isWritable()) {
                        ByteBuffer bb = ByteBuffer.allocate(1024);
                        bb.clear();
                        bb.put("我要写数据到客户端通道!".getBytes());
                        bb.flip(); // 切换为写模式
                        client.write(bb);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
