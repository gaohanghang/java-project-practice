package controller;

import entity.Card;
import service.CardService;

import javax.xml.transform.Templates;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * @Description:
 * @author: Gao Hang Hang
 * @date 2019/01/22 21:28
 */
public class CardController {

    /**
     * 饭卡系统
     *
     * @param args
     * @throws InterruptedException
     * @throws SQLException
     */
    public static void main(String[] args) throws InterruptedException, SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("----------------------欢迎进入**大学饭卡业务系统---------------------");
        Integer power = 0; // 权限，power为 10 时为管理员权限，power为 0 时为用户权限
        Loop:
        while (true) {
            System.out.println(" 1.登录界面，请按 1 \n 2.注册界面，请按 2 \n 0.退出系统，请按 0 ");
            if (power == 10) {
                System.out.println(" 9.退出管理员身份，请输入 9 ");
            }
            String flag = scanner.nextLine();  // 通过判断输入的值，来确定用户接下来的操作
            power = admin(flag, power);        // 管理员权限登录及回收

            // 登录
            if (flag.equals("1")) { // 输入"1"，首先确定用户的卡号，然后，在进行其他操作
                Card card = null;

                // 用户名验证
                while (true) {
                    if (power != 10) {
                        System.out.print("请输入你的学号：");//通过学号查询，调用CardDao的findCard方法
                    } else {
                        System.out.print("请输入您要操作的目标学号：");
                    }

                    // 输入0 退回主界面
                    String id = scanner.nextLine().trim();
                    if (id.equals("0")) { // 若是0则退出
                        System.out.println("退回主界面\n");
                        break Loop;
                    }

                    card = CardService.findCardById(id);// 如果id不合法，则令旧卡id="-1"，使其报错
                    if (card.getId().equals("-1")) {
                        System.out.println("----------------------您输入的用户不存在----------------------");
                        System.out.println("请重新输入用户名，或输入 0 退出主界面");
                    } else {
                        break;   // 成功查找到该用户，进入密码验证
                    }
                }

                // 验证密码
                while (true) {
                    if (power == 0) {
                        System.out.print("请输入您的密码: ");
                    } else {
                        System.out.println("管理员输入回车即可强制登录。");
                    }
                    String password = scanner.nextLine().trim();
                    if (power == 10) {  // 判断是否是管理员权限
                        System.out.println("管理员强制登录中。。。。");
                        Thread.sleep(1000);
                        System.out.println("----------------------管理员强制登录成功----------------------------");
                        break;
                    } else if (card.getPassword().equals(password)) { //判断用户输入的密码是否正确，正确则进入下一步
                        break;
                    } else { // 既不是管理员，密码输入又错误，那么需要重新输入
                        System.out.println("您的密码输入错误，请重新输入.\n");
                    }
                }

                System.out.println("---------------------------欢迎使用饭卡系统-----------------------");
                while (true) {
                    if (power == 10) {
                        System.out.println("0.注销饭卡                    请按 0 (仅管理员可见)");
                    }
                    System.out.println("1.充值饭卡.                   请按 1 ");
                    System.out.println("2.查询余额.                   请按 2 ");
                    System.out.println("3.查询信息.                   请按 3 ");
                    System.out.println("4.饭卡消费.                   请按 4 ");
                    System.out.println("5.查询银行卡上余额             请按 5 ");
                    System.out.println("6.修改信息.                   请按 6 ");
                    System.out.println("7.退出系统.                   请按 7 ");

                    String input = scanner.nextLine();//判断用户输入的值
                    if (power == 10 && input.equals("0")) {
                        CardService.delete(card); // 删除用户
                    } else if(input.equals("1")){
                        CardService.recharge(card);
                    } else if(input.equals("2")){
                        CardService.queryCardMoney(card);
                    }else if(input.equals("3")){
                        CardService.showCard(card);
                    }else if(input.equals("4")){
                        CardService.consume(card);
                    }else if(input.equals("5")){
                        CardService.queryBankCard(card);
                    }else if(input.equals("6")){
                        CardService.update(card);
                    } else if(input.equals("7")){
                        System.out.println("退回主界面\n");
                        break;
                    } else {
                        pritError();
                    }
                }
            } else if (flag.equals("2")) { // 2 办理一张饭卡
                CardService.add();
                System.out.println("您办理饭卡余额为100.0元\n");
            } else if (flag.equals("0")) { // 输入"0"，退出系统
                prinEnd();
                break;
            } else if (!flag.equals("admin")) { // 输入错误，提醒用户重新输入
                pritError();
            }
        }
    }

    // 打印错误信息
    public static void pritError() {
        System.out.println("您的输入不合法，请重新输入.");
    }

    // 打印end
    public static void prinEnd() {
        System.out.println("--------期待您的下次光临！--------");
    }

    // 管理员登录
    public static int admin(String flag, int power) throws InterruptedException {
        if (flag.equals("admin")) {
            Scanner in = new Scanner(System.in);
            System.out.print("请输入管理员密码:");
            String pass = in.nextLine();
            if (pass.equals("000000")) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss\n"); //设置日期格式
                System.out.println("管理员,您好，现在是" + dateFormat.format(new Date()));
                return 10;
            } else {
                System.out.println("密码错误，未得到管理员权限！！");
            }
        } else if (flag.equals("9")) {
            Thread.sleep(1000);
            System.out.println("-------您已成功退出管理员系统---------");
            return 0;
        }
        return power;
    }
}
