package com.gaohanghang.springbootgephi.common.enums;

public enum  Group {
    PERSON("Person","男人"),
    WOMAN("Woman","女人"),
    CREDITCARD("CreditCard","信用卡/银行卡"),
    INSURGENT("Insurgent","嫌疑人"),
    QQ("QQ","微信"),
    WHATSAPP("WhatsApp","WhatsApp"),
    DD("DD","钉钉"),
    BUILDING("Building","建筑"),;

    //自定义字段
    private String code;
    private String name;

    //构造方法
    private Group(String code, String name) {
        this.name = name;
    }

    //一般方法
    public String getCode() {
        return this.code;
    }

    public String getName() {
        return this.name;
    }
}
