package dao;

import model.Goddess;
import sun.jvm.hotspot.oops.GenerateOopMap;
import util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @author: Gao Hang Hang
 * @date 2019/01/23 19:13
 */
public class GoddessDao {
    public void addGoddess(Goddess goddess) throws Exception {
        Connection connection = DBUtil.getConnection();
        String sql = "insert into goddess (user_name,sex,age,birthday,email,mobile,create_user,create_date,update_user,update_date,isdel) " +
                "values(?,?,?,?,?,?,?,current_date(),?,current_date(),?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, goddess.getUserName());
        preparedStatement.setInt(2, goddess.getSex());
        preparedStatement.setInt(3, goddess.getAge());
        preparedStatement.setDate(4, new Date(goddess.getBirthday().getTime()));
        preparedStatement.setString(5, goddess.getEmail());
        preparedStatement.setString(6, goddess.getMobile());
        preparedStatement.setString(7, goddess.getCreateUser());
        preparedStatement.setString(8, goddess.getUpdateUser());
        preparedStatement.setInt(9, goddess.getIsdel());
        preparedStatement.execute();
    }

    public void updateGoddess(Goddess goddess) throws Exception {
        Connection connection = DBUtil.getConnection();
        String sql = "update goddess set user_name = ?,sex = ?,age = ?,birthday = ?,email = ?,mobile = ?,create_date = current_date(),update_user = ?,update_date = current_date(),isdel = ? where id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, goddess.getUserName());
        preparedStatement.setInt(2, goddess.getSex());
        preparedStatement.setInt(3, goddess.getAge());
        preparedStatement.setDate(4, new Date(goddess.getBirthday().getTime()));
        preparedStatement.setString(5, goddess.getEmail());
        preparedStatement.setString(6, goddess.getMobile());
        preparedStatement.setString(7, goddess.getUpdateUser());
        preparedStatement.setInt(8, goddess.getIsdel());
        preparedStatement.setInt(9, goddess.getId());
        preparedStatement.execute();
    }

    public void delGoddess(Integer id) throws Exception {
        Connection connection = DBUtil.getConnection();
        String sql = "delete from goddess where id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setInt(1, id);
        preparedStatement.execute();
    }

    public List<Goddess> query() throws Exception {
        Connection connection = DBUtil.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select id,user_name,age from goddess");

        List<Goddess> gs = new ArrayList<Goddess>();
        Goddess g = null;
        while (resultSet.next()) {
            g = new Goddess();
            g.setId(resultSet.getInt("id"));
            g.setUserName(resultSet.getString("user_name"));
            g.setAge(resultSet.getInt("age"));

            gs.add(g);
        }
        return gs;
    }

    public Goddess get(Integer id) throws SQLException {
        Goddess goddess = null;
        Connection connection = DBUtil.getConnection();
        String sql = "select * from goddess where id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            goddess = new Goddess();
            goddess.setId(resultSet.getInt("id"));
            goddess.setUserName(resultSet.getString("user_name"));
            goddess.setAge(resultSet.getInt("age"));
            goddess.setSex(resultSet.getInt("sex"));
            goddess.setBirthday(resultSet.getDate("birthday"));
            goddess.setEmail(resultSet.getString("email"));
            goddess.setMobile(resultSet.getString("mobile"));
            goddess.setCreateUser(resultSet.getString("create_user"));
            goddess.setCreateDate(resultSet.getDate("update_date"));
            goddess.setUpdateDate(resultSet.getDate("update_date"));
            goddess.setUpdateUser(resultSet.getString("update_user"));
            goddess.setIsdel(resultSet.getInt("isDel"));
        }
        return goddess;
    }

    public List<Goddess> query(String name,String mobail, String email) throws SQLException {
        List<Goddess> result = new ArrayList<Goddess>();

        Connection connection = DBUtil.getConnection();
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("select * from goddess ");
        stringBuilder.append(" where user_name like ? and mobile like ? and email = ?");

        PreparedStatement preparedStatement = connection.prepareStatement(stringBuilder.toString());
        preparedStatement.setString(1, "%" + name + "%");
        preparedStatement.setString(2, "%" + mobail + "%");
        preparedStatement.setString(3, "%" + email + "%");

        System.out.println(preparedStatement.toString());
        ResultSet resultSet = preparedStatement.executeQuery();

        Goddess goddess = null;
        while (resultSet.next()) {
            goddess = new Goddess();
            goddess = new Goddess();
            goddess.setId(resultSet.getInt("id"));
            goddess.setUserName(resultSet.getString("user_name"));
            goddess.setAge(resultSet.getInt("age"));
            goddess.setSex(resultSet.getInt("sex"));
            goddess.setBirthday(resultSet.getDate("birthday"));
            goddess.setEmail(resultSet.getString("email"));
            goddess.setMobile(resultSet.getString("mobile"));
            goddess.setCreateUser(resultSet.getString("create_user"));
            goddess.setCreateDate(resultSet.getDate("update_date"));
            goddess.setUpdateDate(resultSet.getDate("update_date"));
            goddess.setUpdateUser(resultSet.getString("update_user"));
            goddess.setIsdel(resultSet.getInt("isDel"));

            result.add(goddess);
        }
        return result;
    }

    public List<Goddess> query(List<Map<String, Object>> params) throws SQLException {
        List<Goddess> result = new ArrayList<Goddess>();

        Connection connection = DBUtil.getConnection();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("select * from goddess where 1 = 1");

        if (params!=null && params.size() > 0) {
            for (int i = 0; i < params.size(); i++) {
                Map<String, Object> map = params.get(i);
                stringBuilder.append(" and " + " " + map.get("name") + " " + map.get("rela") + " " + map.get("value"));
            }
        }

        PreparedStatement preparedStatement = connection.prepareStatement(stringBuilder.toString());

        System.out.println(preparedStatement.toString());
        ResultSet resultSet = preparedStatement.executeQuery();

        Goddess goddess = null;
        while (resultSet.next()) {
            goddess = new Goddess();
            goddess = new Goddess();
            goddess.setId(resultSet.getInt("id"));
            goddess.setUserName(resultSet.getString("user_name"));
            goddess.setAge(resultSet.getInt("age"));
            goddess.setSex(resultSet.getInt("sex"));
            goddess.setBirthday(resultSet.getDate("birthday"));
            goddess.setEmail(resultSet.getString("email"));
            goddess.setMobile(resultSet.getString("mobile"));
            goddess.setCreateUser(resultSet.getString("create_user"));
            goddess.setCreateDate(resultSet.getDate("update_date"));
            goddess.setUpdateDate(resultSet.getDate("update_date"));
            goddess.setUpdateUser(resultSet.getString("update_user"));
            goddess.setIsdel(resultSet.getInt("isDel"));

            result.add(goddess);
        }
        return result;
    }



}
