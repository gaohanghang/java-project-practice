package service;

import dao.CardDao;
import entity.Card;

import java.sql.SQLException;
import java.util.Scanner;

/**
 * @Description:
 * @author: Gao Hang Hang
 * @date 2019/01/22 20:18
 */
public class CardService {

    private static CardDao cardDao;

    // 增加
    public static void add() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("大学饭卡欢迎您的使用！！！");
        Card card = new Card();
        while (true) {
            System.out.print("请输入您的学号:");
            String id = scanner.nextLine();
            Card findById = CardDao.findById(id);
            if (id.equals("0")) {
                System.out.println("输入不合法，请重新输入");
            } else if (findById.getId().equals("-1")) {
                card.setId(id); // 数据库中查不到就设置学号
                break;
            } else {
                System.out.println("用户学号已存在，请重新输入");
            }
        }
        System.out.print("请输入您的姓名:");
        card.setName(scanner.nextLine());
        System.out.print("请输入您的班级名称:");
        card.setClassName(scanner.nextLine());
        System.out.print("请输入您的密码（---重要----）:");
        card.setPassword(scanner.nextLine());
        CardDao.addCard(card);
        System.out.println();
    }

    // 充值
    public static void recharge(Card card) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入您要充值的金额");
        int rechargeMoney = scanner.nextInt();
        CardDao.recharge(card, rechargeMoney);
    }

    public static void queryCardMoney(Card card) {
        System.out.println("饭卡余额:" + card.getMoney());
    }

    public static void showCard(Card card) {
        System.out.println("您饭卡的完整信息为：");
        System.out.println("学号："+card.getId());
        System.out.println("姓名："+card.getName());
        System.out.println("班级："+card.getClassName());
    }

    public static Card findCardById(String id) throws SQLException {
        return CardDao.findById(id);
    }

    // 删除
    public static void delete(Card card) throws SQLException {
        System.out.println("您真的要删除这个饭卡吗？\n输入 1 确定，其他退出修改");
        Scanner scanner = new Scanner(System.in);
        Card byId = CardDao.findById(card.getId());
        if (byId.getId().equals("-1")){
            System.out.println("学号不存在");
        } else if (scanner.nextLine().equals("1")) {
            CardDao.delete(card);
            System.out.println("已经成功删除学号为：" + card.getId() + "的用户");
        } else {
            System.out.println("未删除" + card.getId() + " " + card.getName());
        }
    }

    // 消费
    public static void consume(Card card) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入您要花费的金额：");
        double cost = scanner.nextDouble();
        CardDao.consume(card, cost);
    }

    public static void queryBankCard(Card card) {
        System.out.println("银行卡余额为：" + card.getNumOfBankCard());
    }

    public static void update(Card card) throws SQLException {
        System.out.println("您当前的信息为：");
        showCard(card);
        System.out.println("您确定要修改信息吗？ 按 1 确认，其他退出修改");
        Scanner scanner = new Scanner(System.in);
        if (scanner.nextLine().equals("1")) {
            System.out.println("您已确认修改！");
            CardDao.updateCard(card);
        }
    }

    public static void main(String[] args) throws SQLException {
        // add();
        // recharge(CardDao.findById("1"));
        // delete(findCard("4"));
        // consume(findCard("2"));
        update(findCardById("2"));
    }
}
