package entity;

import java.io.Serializable;

/**
 * @Description: 植物实体类
 * @author: Gao Hang Hang
 * @date 2019/01/23 09:55
 */
public class Plant implements Serializable {
    private Integer id;
    private String userId;//购买者id
    private Integer rainNumber;//雨露值
    private Integer sunNumber;//阳光值
    private Integer heaNumber;//健康值
    private Double money;//植物价格

    public Plant() {
    }

    public Plant(Integer id, String userId, Integer rainNumber, Integer sunNumber, Integer heaNumber, Double money) {
        this.id = id;
        this.userId = userId;
        this.rainNumber = rainNumber;
        this.sunNumber = sunNumber;
        this.heaNumber = heaNumber;
        this.money = money;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getRainNumber() {
        return rainNumber;
    }

    public void setRainNumber(Integer rainNumber) {
        this.rainNumber = rainNumber;
    }

    public Integer getSunNumber() {
        return sunNumber;
    }

    public void setSunNumber(Integer sunNumber) {
        this.sunNumber = sunNumber;
    }

    public Integer getHeaNumber() {
        return heaNumber;
    }

    public void setHeaNumber(Integer heaNumber) {
        this.heaNumber = heaNumber;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "Plant{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", rainNumber=" + rainNumber +
                ", sunNumber=" + sunNumber +
                ", heaNumber=" + heaNumber +
                ", money=" + money +
                '}';
    }
}
