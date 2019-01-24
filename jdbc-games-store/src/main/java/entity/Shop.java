package entity;

import java.io.Serializable;

/**
 * @Description:
 * @author: Gao Hang Hang
 * @date 2019/01/23 09:53
 */
public class Shop implements Serializable {
    private Integer id;//主键
    private String name;//
    private Double money;

    public Shop() {
    }

    public Shop(Integer id, String name, Double money) {
        this.id = id;
        this.name = name;
        this.money = money;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }
}
