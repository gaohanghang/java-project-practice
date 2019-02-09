package src.com.imooc.service;

import src.com.imooc.entity.File;
import src.com.imooc.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Description:
 * @author: Gao Hang Hang
 * @date 2019/02/09 13:44
 */
public class FileService {
    private Connection conn = null;
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;

    // 将文件保存到数据库中
    public void save(File file) {
        String sql = "insert into tb_file(fname,fcontent) values (?,?)";
        try {
            conn = conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, file.getFname());
            pstmt.setBytes(2, file.getFcontent());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.clostAll(rs, pstmt, conn);
        }
    }
}
