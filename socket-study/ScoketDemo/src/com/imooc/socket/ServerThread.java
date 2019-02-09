package src.com.imooc.socket;

import com.sun.istack.internal.localization.NullLocalizable;
import src.com.imooc.entity.File;
import src.com.imooc.entity.User;
import src.com.imooc.service.FileService;
import src.com.imooc.service.UserService;
import src.com.imooc.util.CommandTransfer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * @Description: 服务器端线程处理类
 * @author: Gao Hang Hang
 * @date 2019/02/09 14:56
 */
public class ServerThread extends Thread {
    private Socket socket = null;
    private ObjectInputStream ois = null; // 对象输入流
    private ObjectOutputStream oos = null; // 对象输出流
    private UserService us = new UserService(); // 用户业务对象
    private FileService fs = new FileService(); // 文件业务对象

    // 通过构造方法，初始化socket
    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            ois = new ObjectInputStream(socket.getInputStream());
            oos = new ObjectOutputStream(socket.getOutputStream());
            CommandTransfer transfer = (CommandTransfer) ois.readObject(); // 读取指令
            execute(transfer);
            oos.writeObject(transfer); //
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 执行客户端发送到服务器的指令操作
     * @param transfer
     */
    public void execute(CommandTransfer transfer) {
        String cmd = transfer.getCmd();//获取当前操作的指令
        if (cmd.equals("login")) { // 获取当前操作的指令
            User user = (User) transfer.getData();
            boolean flag = us.login(user);
            transfer.setFlag(flag);
            if (flag) { // 判断登录是否成功
                transfer.setResult("登录成功！");
            } else {
                transfer.setResult("用户名或密码不正确，请重新登录！");
            }
        } else if (cmd.equals("register")) { //用户注册
            User user = (User) transfer.getData();
            us.register(user); //注册用户
            boolean flag = us.login(user);
            transfer.setFlag(flag);
            if (flag){
                transfer.setResult("注册成功");
            } else {
                transfer.setResult("注册失败..");
            }
        } else if (cmd.equals("uploadFile")) {
            File file = (File) transfer.getData();
            fs.save(file);
            transfer.setResult("上传成功");
        }
    }
}
