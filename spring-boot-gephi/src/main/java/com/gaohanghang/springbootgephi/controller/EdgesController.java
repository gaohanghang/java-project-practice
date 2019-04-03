package com.gaohanghang.springbootgephi.controller;

import com.gaohanghang.springbootgephi.dao.EdgesRepository;
import com.gaohanghang.springbootgephi.entity.Edges;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/edges")
public class EdgesController {

    @Autowired
    private EdgesRepository edgesRepository;

    /**
     * 新增或编辑
     */
    @PostMapping("/save")
    public Object save(Edges edges){
        return edgesRepository.save(edges);
    }

    /**
     * 删除
     */
    @GetMapping("/delete")
    public Object delete(int id){
        Optional<Edges> edges=edgesRepository.findById(id);
        if(edges.isPresent()){
            edgesRepository.deleteById(id);
            return "删除成功";
        }else{
            return "没有找到该对象";
        }
    }

    /**
     * 查询
     */
    @GetMapping("/find")
    public Object find(int id){
        Optional<Edges> edges=edgesRepository.findById(id);
        if(edges.isPresent()){
            return edges.get();
        }else{
            return "没有找到该对象";
        }
    }

    /**
     * 分页查询
     */
    @PostMapping("/list")
    public Object list(Edges edges,
                       @RequestParam(required = false, defaultValue = "0") int pageNumber,
                       @RequestParam(required = false, defaultValue = "10") int pageSize) {

        //创建匹配器，需要查询条件请修改此处代码
        ExampleMatcher matcher = ExampleMatcher.matchingAll();

        //创建实例
        Example<Edges> example = Example.of(edges, matcher);
        //分页构造
        Pageable pageable = PageRequest.of(pageNumber,pageSize);

        return edgesRepository.findAll(example, pageable);
    }

}

