package dao;

import entity.Card;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import sun.print.PeekGraphics;
import utils.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * @Description:
 * @author: Gao Hang Hang
 * @date 2019/01/22 18:24
 */
public class CardDao {

    private static Connection connection = JdbcUtil.getConnection();

    // 查询
    public static Card findById(String id) throws SQLException {
        String sql = "select * from card where id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        Card card = new Card();
        if (resultSet.next()) {
            card.setId(resultSet.getString("id"));
            card.setName(resultSet.getString("name"));
            card.setClassName(resultSet.getString("class_name"));
            card.setMoney(resultSet.getFloat("money"));
            card.setPassword(resultSet.getString("password"));
            card.setNumOfBankCard(resultSet.getDouble("num_of_bank_card"));
        } else {
            card.setId("-1");
        }
        // System.out.println(card.toString());
        return card;
    }

    // 增加
    public static void addCard(Card card) throws SQLException {
        String sql = "insert into card (id,name,class_name,password) values (?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, card.getId());
        preparedStatement.setString(2, card.getName());
        preparedStatement.setString(3, card.getClassName());
        preparedStatement.setString(4, card.getPassword());

        preparedStatement.execute();
    }

    // 更新
    public static void updateCard(Card card) throws SQLException {
        System.out.println("请重新输入个人信息：");

        String oldId = card.getId();
        Scanner scanner = new Scanner(System.in);
        System.out.println("输入您新的学号");
        card.setId(scanner.nextLine());
        System.out.println("输入您新的用户名");
        card.setName(scanner.nextLine());
        System.out.println("输入您新的密码");
        card.setPassword(scanner.nextLine());
        System.out.println("输入您新的班级名称");
        card.setClassName(scanner.nextLine());

        String sql = "UPDATE card set id = ?, name = ?, class_name = ?, password = ? where id =?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, card.getId());
        preparedStatement.setString(2, card.getName());
        preparedStatement.setString(3, card.getClassName());
        preparedStatement.setString(4, card.getPassword());
        preparedStatement.setString(5, oldId);

        System.out.println(preparedStatement.toString()); // 打印执行的sql语句
        preparedStatement.execute();
    }

    // 删除
    public static void delete(Card card) throws SQLException {
        String sql = "delete from card where id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, card.getId());
        preparedStatement.execute();
    }

    // 充值
    public static double recharge(Card card, Integer plusMoney) throws SQLException {
        if (card.getNumOfBankCard() < plusMoney) {
            System.out.println("银行卡余额已不足，请及时到银行充值！！");
        } else {
            card.setMoney(card.getMoney() + plusMoney); // 饭卡余额
            card.setNumOfBankCard(card.getNumOfBankCard() - plusMoney); // 银行卡余额
            String sql = "update card set money = ?, num_of_bank_card = ? where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setDouble(1, card.getMoney());
            preparedStatement.setDouble(2, card.getNumOfBankCard());
            preparedStatement.setString(3, card.getId());
            System.out.println(preparedStatement.toString());
            preparedStatement.execute();
            System.out.println("充值成功，您饭卡上的余额为：" + card.getMoney());
        }
        return card.getMoney();
    }

    // 消费
    public static void consume(Card card, double cost) throws SQLException {
        if (card.getMoney() < cost) {
            System.out.println("饭卡余额已不足，请及时充值！！");
        } else {
            card.setMoney(card.getMoney() - cost); // 饭卡余额

            String sql = "update card set money = ? where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDouble(1, card.getMoney());
            preparedStatement.setString(2, card.getId());
            preparedStatement.execute();

            System.out.println("你已消费" + cost + "元,卡上余额为" + card.getMoney());
        }
    }

    public static void main(String[] args) throws SQLException {
        // 查询
        // findById("2");

        // 增加
        // Card card = new Card("1", "test1", "class1", "123456");
        // addCard(card);

        // 更新
        // Card card = new Card();
        // card.setId("1");
        // updateCard(card);

        // 删除
        // Card card = new Card();
        // card.setId("3");
        // delete(card);

        // 充值
        // recharge(findById("1"), 400);

        // 消费
        consume(findById("1"), 1000);
    }
}
