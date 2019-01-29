package utils;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @Description:
 * @author: Gao Hang Hang
 * @date 2019/01/22 17:51
 */
public class JdbcUtil {
    private static final String URL = "jdbc:mysql://localhost:3306/meal_card?characterEncoding=utf8&useSSL=true";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    // 上面分别定义了URL(统一资源定位器),USERNAME(用户名),PASSWORD(密码)

    private static Connection connection = null;    // 定义一个空的Connection

    static {
        try {
            // 自Java 6（JDBC 4.0）以来，通常不需要手动加载驱动程序类Class.forName
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD); // 获取连接
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return connection;
    }

    public static void main(String[] args) {
        Connection connection = getConnection();
        if (connection!=null) {
            System.out.println("数据库连接成功！");
        }
    }
}
