/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gaohanghang.springbootgephi.jsonexporter.model;

public class GraphEdge extends GraphElement {


    private String label;
    private String from;
    private String to;
    private String id;
    private Color color;

    public GraphEdge(String id) {
        super();
        this.id = id;
        label = "";
        from = "";
        to = "";
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
