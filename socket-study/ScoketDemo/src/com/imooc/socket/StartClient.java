package src.com.imooc.socket;

/**
 * @Description: 启动客户端
 * @author: Gao Hang Hang
 * @date 2019/02/09 14:08
 */
public class StartClient {
    public static void main(String[] args) {
        SocketClient client = new SocketClient();
        client.showMainMenu();// 显示主菜单
    }
}
