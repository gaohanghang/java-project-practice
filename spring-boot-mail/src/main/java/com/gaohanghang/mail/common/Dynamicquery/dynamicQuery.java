package com.gaohanghang.mail.common.Dynamicquery;

/**
 * @Description: 扩展SpringDataJPA, 支持动态jpql/nativesql查询并支持分页查询
 * 使用方法：注入ServiceImpl
 * @author: Gao Hang Hang
 * @date 2019/01/29 13:03
 */
public interface dynamicQuery {

    public void save(Object entity);

    public void update(Object entity);

    // 第一个<T>表示这是一个泛型方法，表示传入参数有泛型
    public <T> void delete(Class<T> entityClass, Object entityid);


}
