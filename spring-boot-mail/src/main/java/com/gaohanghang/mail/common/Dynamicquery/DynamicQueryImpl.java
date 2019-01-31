package com.gaohanghang.mail.common.Dynamicquery;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.object.SqlQuery;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * @Description:
 * @author: Gao Hang Hang
 * @date 2019/01/30 17:43
 */
public class DynamicQueryImpl implements DynamicQuery {

    Logger logger = LoggerFactory.getLogger(DynamicQuery.class);

    @PersistenceContext
    private EntityManager em;

    public EntityManager getEntityManager() {
        return em;
    }

    @Override
    public void save(Object entity) {
        em.persist(entity);
    }

    @Override
    public void update(Object entity) {
        em.merge(entity);
    }

    @Override
    public <T> void delete(Class<T> entityClass, Object entityid) {
        delete(entityClass, new Object[]{ entityid });
    }

    @Override
    public <T> void delete(Class<T> entityClass, Object[] entityids) {
        for (Object id : entityids) {
            em.remove(em.getReference(entityClass, id));
        }
    }

    private Query createNativeQuery(String sql, Object... params) {
        Query nativeQuery = em.createNativeQuery(sql);
        if (params != null && params.length > 0) {
            for (int i = 0; i < params.length; i++) {
                nativeQuery.setParameter(i + 1, params[i]);  // 与Hiberante不同,jpa
                                                                // query从位置1开始
            }
        }
        return nativeQuery;
    }

    @SuppressWarnings({"unchecked"})// 告诉编译器忽略 unchecked 警告信息，如使用List，ArrayList等未进行参数化产生的警告信息。
    @Override
    public <T> List<T> nativeQueryList(String nativeSql, Object... params) {
        Query nativeQuery = createNativeQuery(nativeSql, params);
        nativeQuery.unwrap(SQLQuery.class).setResultTransformer(Transformers.TO_LIST);
        return nativeQuery.getResultList();
    }

    @SuppressWarnings({ "unchecked"})
    @Override
    public <T> List<T> nativeQueryListMap(String nativeSql, Object... params) {
        Query q = createNativeQuery(nativeSql, params);
        q.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        return q.getResultList();
    }

    @SuppressWarnings({ "unchecked"})
    @Override
    public <T> List<T> nativeQueryListModel(Class<T> resultClass, String nativeSql, Object... params) {
        Query q = createNativeQuery(nativeSql, params);
        q.unwrap(SQLQuery.class).setResultTransformer(Transformers.aliasToBean(resultClass));
        return q.getResultList();
    }

    @Override
    public Long nativeQueryCount(String nativeSql, Object... params) {
        Object count = createNativeQuery(nativeSql, params).getSingleResult();
        return ((Number) count).longValue();
    }
}
