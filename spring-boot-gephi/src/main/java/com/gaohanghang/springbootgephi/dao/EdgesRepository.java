package com.gaohanghang.springbootgephi.dao;

import com.gaohanghang.springbootgephi.entity.Edges;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EdgesRepository extends JpaRepository<Edges,Integer> {

}