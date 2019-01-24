package util;

import java.sql.*;

/**
 * @Description:
 * @author: Gao Hang Hang
 * @date 2019/01/23 16:42
 */
public class DBUtil {
    private static final String URL = "jdbc:mysql://localhost:3306/imooc?characterEncoding=utf-8";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    private static Connection connection = null;

    static {
        try {
            // 1.加载驱动程序
            // Class.forName("com.mysql.jdbc.Driver");
            // 2.获得数据库的连接
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return connection;
    }

    public static void main(String[] args) throws Exception {
        // 1.加载驱动程序
        // Class.forName("com.mysql.jdbc.Driver");
        // 2.获得数据库的连接
        Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
        // 3.通过数据库的连接操作数据库，实现增删改查
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select user_name,age from goddess");

        while (resultSet.next()) {
            System.out.println(resultSet.getString("user_name")+ "," + resultSet.getInt("age"));
        }
    }
}
