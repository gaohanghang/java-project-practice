package entity;

import java.io.Serializable;

/**
 * @Description: 饭卡实体类
 * @author: Gao Hang Hang
 * @date 2019/01/22 17:44
 */
public class Card implements Serializable {
    private String id;//学号
    private String name;//姓名
    private String className;//班级名称
    private double money;//余额
    private String password;//密码
    private double numOfBankCard;//饭卡对应的银行卡的钱数

    public Card() {
    }

    public Card(String id, String name, String className, String password) {
        this.id = id;
        this.name = name;
        this.className = className;
        this.password = password;
    }

    public Card(String id, String name, String className, double money, String password, double numOfBankCard) {
        this.id = id;
        this.name = name;
        this.className = className;
        this.money = money;
        this.password = password;
        this.numOfBankCard = numOfBankCard;
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

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getNumOfBankCard() {
        return numOfBankCard;
    }

    public void setNumOfBankCard(double numOfBankCard) {
        this.numOfBankCard = numOfBankCard;
    }

    @Override
    public String toString() {
        return "Card{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", className='" + className + '\'' +
                ", money=" + money +
                ", password='" + password + '\'' +
                ", numOfBankCard=" + numOfBankCard +
                '}';
    }
}
