package entity;

/**
 * @Description: 顾客实体类
 * @author: Gao Hang Hang
 * @date 2019/01/23 09:51
 */
public class User {
    private String id;//主键
    private String name;//网名
    private String password;//登录密码
    private double money;//所拥有的金币

    public User() {
    }

    public User(String id, String name, String password, double money) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.money = money;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }
}
