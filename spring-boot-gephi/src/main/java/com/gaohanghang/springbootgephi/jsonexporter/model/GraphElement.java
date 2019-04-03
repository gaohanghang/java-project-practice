/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gaohanghang.springbootgephi.jsonexporter.model;

import java.util.HashMap;

/**
 * @author shale
 */
public class GraphElement {
    protected double size;
    private HashMap<String, String> attributes;

    public GraphElement() {
        attributes = new HashMap<String, String>();
    }

    public void putAttribute(String key, String value) {
        attributes.put(key, value);
    }

    public HashMap<String, String> getAttributes() {
        return attributes;
    }

    public void putAttributes(HashMap<String, String> attributes) {
        this.attributes = attributes;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }


}

