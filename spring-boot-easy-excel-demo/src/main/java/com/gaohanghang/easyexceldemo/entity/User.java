package com.gaohanghang.easyexceldemo.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Description:
 * @author: Gao Hang Hang
 * @date 2019/01/28 15:36
 */
@Entity
@Table(name = "user")
public class User extends BaseRowModel {

    @Id
    @ExcelProperty(index = 0)
    private String id;
    @ExcelProperty(index = 1)
    private String name;
    @ExcelProperty(index = 2)
    private String age;

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

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}
