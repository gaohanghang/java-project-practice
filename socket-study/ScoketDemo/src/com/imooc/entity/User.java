package src.com.imooc.entity;

import java.io.Serializable;

/**
 * @Description: 用户实体类
 * @author: Gao Hang Hang
 * @date 2019/02/09 13:39
 */
public class User implements Serializable {

    private int id;//编号
    private String username;//用户名
    private String password;//密码

    public User() {
        super();
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
