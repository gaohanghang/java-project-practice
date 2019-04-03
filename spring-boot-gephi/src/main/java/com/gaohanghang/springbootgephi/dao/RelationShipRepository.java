package com.gaohanghang.springbootgephi.dao;

import com.gaohanghang.springbootgephi.entity.Relationship;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Description:
 * @author: Gao Hang Hang
 * @date 2019/01/28 15:37
 */
public interface RelationShipRepository extends JpaRepository<Relationship,String> {
}
