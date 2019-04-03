package com.gaohanghang.springbootgephi.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@Table(name="edges")
@Getter
@Setter
@ToString
@AllArgsConstructor
public class Edges implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    private String id;


    private String source;

    private String target;

    private String weight;


    private String label;


    private Date date;


    private Date createTime;


    private Date updateTime;

    public Edges() {
    }
}
