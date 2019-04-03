package com.gaohanghang.springbootgephi.dao;

import com.gaohanghang.springbootgephi.entity.Nodes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NodesRepository extends JpaRepository<Nodes,Integer> {
    Nodes findById(String id);
}
