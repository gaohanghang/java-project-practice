package src.com.imooc.util;

import java.sql.*;

/**
 * @Description:
 * @author: Gao Hang Hang
 * @date 2019/02/09 13:59
 */
public class DBUtil {

    // 获取数据库连接
    public static Connection getConnection() {
        String driverClassName = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/test";
        String username = "root";
        String password = "root";
        Connection conn = null;
        try {
            Class.forName(driverClassName);
            conn = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    // 关闭所有
    public static void clostAll(ResultSet rs, Statement stmt, Connection conn) {
        try {
            if(rs != null)
                rs.close();
            if (stmt != null)
                stmt.close();
            if (conn != null)
                conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
