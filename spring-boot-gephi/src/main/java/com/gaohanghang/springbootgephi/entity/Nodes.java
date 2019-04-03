package com.gaohanghang.springbootgephi.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Data
@Table(name="nodes")
@Getter
@Setter
@ToString
public class Nodes implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    /**
     * id
     */
    private String id;

    /**
     * node_id
     */
    private Integer nodeId;

    /**
     * label
     */
    private String label;

    /**
     * size
     */
    private String size;

    /**
     * url
     */
    private String url;

    /**
     * x
     */
    private Float x;

    /**
     * y
     */
    private Float y;

    /**
     * group
     */
    private String group;

    /**
     * color
     */
    private String color;

    /**
     * attributes
     */
    private String attributes;

    public Nodes() {
    }

}