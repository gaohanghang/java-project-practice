package com.gaohanghang.springbootmongodb.dao.impl;


import com.gaohanghang.springbootmongodb.dao.DemoDao;
import com.gaohanghang.springbootmongodb.entity.DemoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

/**
 * @Description: Demo DAO 实现
 * @author: Gao Hang Hang
 * @date 2019/01/29 19:32
 */
@Component
public class DemoDaoImpl implements DemoDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void saveDemo(DemoEntity demoEntity) {
        mongoTemplate.save(demoEntity);
    }

    @Override
    public void removeDemo(Long id) {
        DemoEntity demoEntity = new DemoEntity();
        demoEntity.setId(id);
        mongoTemplate.remove(demoEntity);
    }

    @Override
    public void updateDemo(DemoEntity demoEntity) {
        Query query = new Query(Criteria.where("id").is(demoEntity.getId()));

        Update update = new Update();
        update.set("title", demoEntity.getTitle());
        update.set("description", demoEntity.getDescription());
        update.set("by", demoEntity.getBy());
        update.set("url", demoEntity.getUrl());

        mongoTemplate.updateFirst(query, update, DemoEntity.class);
    }

    @Override
    public DemoEntity findDemoById(Long id) {
        Query query = new Query(Criteria.where("id").is(id));
        DemoEntity demoEntity = mongoTemplate.findOne(query, DemoEntity.class);
        return demoEntity;
    }
}
