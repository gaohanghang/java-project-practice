package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @Description: NIO服务器
 * @author: Gao Hang Hang
 * @create: 2019/04/29 21:22
 */
public class ServerSocket {

    ServerSocketChannel serverChannel;

    Selector selector;

    public static void main(String[] args) {
        ServerSocket ss = new ServerSocket();
        ss.start();
        ss.bindEvent();
    }

    public void start() {
        try {
            serverChannel = ServerSocketChannel.open();

            serverChannel.bind(new InetSocketAddress("localhost", 8080));

            serverChannel.configureBlocking(false);

            selector = Selector.open();

            /**
             * 这个就是把我们服务端的通道注册到我们的监视器里面来
             * 然后当我们的服务端通道接收到客户端请求的时候，就会触发这个服务端通道的某个操作
             */
            serverChannel.register(selector, SelectionKey.OP_ACCEPT); // 事件注册
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
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();

                    if (key.isAcceptable()) { // key可接收
                        SocketChannel client = serverChannel.accept();
                        client.configureBlocking(false);
                        client.register(selector, SelectionKey.OP_READ);
                    } else if (key.isReadable()) {
                        SocketChannel client = (SocketChannel)key.channel(); // 客户端的通道
                        ByteBuffer bb = ByteBuffer.allocate(1024);
                        int len = client.read(bb);
                        if (len > 0) {
                            System.out.println( "服务端已经拿到了客户端的数据，数据内容是：" + new String(bb.array(), 0, len));
                            client.register(selector, SelectionKey.OP_WRITE);
                        }
                    }
                }
                iterator.remove();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
