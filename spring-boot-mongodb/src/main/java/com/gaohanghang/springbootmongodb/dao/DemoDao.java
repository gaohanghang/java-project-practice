package com.gaohanghang.springbootmongodb.dao;

import com.gaohanghang.springbootmongodb.entity.DemoEntity;

/**
 * @Description: 提供增删改查 MongoDB 接口
 * @author: Gao Hang Hang
 * @date 2019/01/29 19:17
 */
public interface DemoDao {

    void saveDemo(DemoEntity demoEntity);

    void removeDemo(Long id);

    void updateDemo(DemoEntity demoEntity);

    DemoEntity findDemoById(Long id);
}
