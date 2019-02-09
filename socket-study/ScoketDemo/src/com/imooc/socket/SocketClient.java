package src.com.imooc.socket;

import src.com.imooc.entity.File;
import src.com.imooc.entity.User;
import src.com.imooc.util.CommandTransfer;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * @Description:
 * @author: Gao Hang Hang
 * @date 2019/02/09 14:12
 */
public class SocketClient {
    Scanner input = new Scanner(System.in);
    private Socket socket = null; //客户端Socket

    // 主菜单
    public void showMainMenu() {
        System.out.println("*****欢迎使用imooc文件上传器*****");
        System.out.println("1.登录\n2.注册\n3.退出");
        System.out.println("*****************************");
        System.out.println("请选择：");
        int choice = input.nextInt();// 获取用户的选择
        switch (choice) {
            case 1:
                showLogin(); // 登录
                break;
            case 2:
                showRegister(); //注册
                break;
            case 3:
                System.out.println("再见，感谢您对本系统的支持！");
            default:
                System.out.println("输入有误！");
                System.exit(0);
        }
    }

    // 用户登录
    private void showLogin() {
        User user = new User();
        CommandTransfer transfer = new CommandTransfer();
        int count = 0; // 登录次数
        while (true) {
            count++;
            if (count > 3) {
                System.out.println("您已连续三次登录失败，程序退出！");
                System.exit(0);
            }
            System.out.println("请输入用户名：");
            user.setUsername(input.next());
            System.out.println("请输入密码：");
            user.setPassword(input.next());
            transfer.setCmd("login");
            transfer.setData(user);

            try {
                socket = new Socket("localhost", 8800);
                sendData(transfer); //将数据发送到服务器
                transfer = getData(); //获取服务器返回的数据
                System.out.println(transfer.getResult());//输出显示结果
                System.out.println("***********************");
                if (transfer.isFlag()) {
                    break;//如果登录成功，则不再重复执行登录
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                closeAll();//结束本次socket通信
            }
        }
        showUploadFile();//登录成功后进行文件的上传
    }

    // 用户注册
    private void showRegister() {
        User user = new User();
        CommandTransfer transfer = new CommandTransfer();
        while (true) {
            System.out.println("请输入用户名：");
            user.setUsername(input.next());
            System.out.println("请输入密码：");
            user.setPassword(input.next());
            System.out.println("请再次输入密码：");
            String rePassword = input.next();
            if (!user.getPassword().equals(rePassword)) {
                System.out.println("两次输入的密码不一致！");
                System.out.println("***************************");
                continue;
            }
            transfer.setCmd("register");
            transfer.setData(user);

            try {
                socket = new Socket("localhost", 8800);
                sendData(transfer); //将数据发送到服务器
                transfer = getData(); // 获取服务器返回的数据
                System.out.println(transfer.getResult()); // 输出显示结果
                System.out.println("******************************");
                if (transfer.isFlag()) {
                    break; // 如果注册成功，则不再重复执行注册
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                closeAll(); //结束本次socket通信
            }
            showLogin();// 注册成功后显示登录
        }
    }

    // 上传文件
    public void showUploadFile() {
        System.out.println("请输入上传文件的绝对路径(如e:/imooc/dog.jpg)");
        String path = input.next();
        File file = null;
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        String fname = path.substring(path.lastIndexOf("/") + 1);

        try {
            fis = new FileInputStream(path);
            byte[] fcontent = new byte[fis.available()];
            bis = new BufferedInputStream(fis);
            bis.read(fcontent);
            file = new File(fname, fcontent);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        CommandTransfer transfer = new CommandTransfer();
        transfer.setCmd("uploadFile");
        transfer.setData(file);
        try {
            socket = new Socket("localhost", 8800);
            sendData(transfer);
            transfer = getData();
            System.out.println(transfer.getResult()); //输出显示结果
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
    }

    // 发送请求
    private void sendData(CommandTransfer transfer) {
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(transfer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 获取响应
    public CommandTransfer getData() {
        ObjectInputStream ois = null;
        CommandTransfer transfer = null;
        try {
            ois = new ObjectInputStream(socket.getInputStream());
            transfer = (CommandTransfer) ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return transfer;
    }

    private void closeAll() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

