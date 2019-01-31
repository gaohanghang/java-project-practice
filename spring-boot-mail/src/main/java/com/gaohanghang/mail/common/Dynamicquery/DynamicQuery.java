package com.gaohanghang.mail.common.Dynamicquery;

import java.util.List;

/**
 * @Description: 扩展SpringDataJPA, 支持动态jpql/nativesql查询并支持分页查询
 * 使用方法：注入ServiceImpl
 * @author: Gao Hang Hang
 * @date 2019/01/29 13:03
 */
public interface DynamicQuery {

    public void save(Object entity);

    public void update(Object entity);

    // 第一个<T>表示这是一个泛型方法，表示传入参数有泛型
    public <T> void delete(Class<T> entityClass, Object entityid);

    public <T> void delete(Class<T> entityClass, Object[] entityids);

    /**
     * 查询对象列表，返回List
     * @param nativeSql
     * @param params
     * @param <T>
     * @return
     */
    <T> List<T> nativeQueryList(String nativeSql, Object... params);

    /**
     * 查询对象列表，返回List<Map<Key, value>>
     * @param nativeSql
     * @param params
     * @param <T>
     * @return
     */
    <T> List<T> nativeQueryListMap(String nativeSql, Object... params);

    /**
     * 查询对象列表，返回List<组合对象>
     * @param resultClass
     * @param nativeSql
     * @param params
     * @param <T>
     * @return
     */
    <T> List<T> nativeQueryListModel(Class<T> resultClass, String nativeSql, Object... params);

    /**
     * 执行nativeSql统计查询
     * @param nativeSql
     * @param params
     * @return
     */
    Long nativeQueryCount(String nativeSql, Object... params);
}
