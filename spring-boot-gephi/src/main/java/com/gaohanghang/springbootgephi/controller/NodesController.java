package com.gaohanghang.springbootgephi.controller;

import com.gaohanghang.springbootgephi.dao.NodesRepository;
import com.gaohanghang.springbootgephi.entity.Nodes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/nodes")
public class NodesController {

    @Autowired
    private NodesRepository nodesRepository;

    /**
     * 新增或编辑
     */
    @PostMapping("/save")
    public Object save(Nodes nodes){
        return nodesRepository.save(nodes);
    }

    /**
     * 删除
     */
    @GetMapping("/delete")
    public Object delete(int id){
        Optional<Nodes> nodes=nodesRepository.findById(id);
        if(nodes.isPresent()){
            nodesRepository.deleteById(id);
            return "删除成功";
        }else{
            return "没有找到该对象";
        }
    }

    /**
     * 查询
     */
    @GetMapping("/find")
    public Object find(String id){
        Nodes nodes=nodesRepository.findById(id);
        return nodes;
    }

    /**
     * 分页查询
     */
    @PostMapping("/list")
    public Object list(Nodes nodes,
                       @RequestParam(required = false, defaultValue = "0") int pageNumber,
                       @RequestParam(required = false, defaultValue = "10") int pageSize) {

        //创建匹配器，需要查询条件请修改此处代码
        ExampleMatcher matcher = ExampleMatcher.matchingAll();

        //创建实例
        Example<Nodes> example = Example.of(nodes, matcher);
        //分页构造
        Pageable pageable = PageRequest.of(pageNumber,pageSize);

        return nodesRepository.findAll(example, pageable);
    }

}
