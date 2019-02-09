package src.com.imooc.service;

import src.com.imooc.entity.User;
import src.com.imooc.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Description:
 * @author: Gao Hang Hang
 * @date 2019/02/09 14:43
 */
public class UserService {
    private Connection conn = null;
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;

    // 用户登录
    public boolean login(User user) {
        String sql = "select * from tb_user where username = ? and password = ?";
        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            rs = pstmt.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.clostAll(rs, pstmt, conn);
        }
        return false;
    }

    // 用户注册
    public void register(User user) {
        String sql = "insert into tb_user(username, password) values (?,?)";
        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
