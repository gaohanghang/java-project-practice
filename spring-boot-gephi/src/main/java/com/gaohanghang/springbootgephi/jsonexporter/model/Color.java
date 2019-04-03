package com.gaohanghang.springbootgephi.jsonexporter.model;

import java.util.Objects;

public class Color {
    private String color;

    private String highlight;

    public Color() {
    }

    public Color(String color, String highlight) {
        this.color = color;
        this.highlight = highlight;
    }

    public String getColor(){
        return this.color;
    }

    public void setColor(String color){
        this.color = color;
    }

    public String getHighlight(){
        return this.highlight;
    }

    public void setHighlight(String highlight){
        this.highlight = highlight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Color color1 = (Color) o;
        return Objects.equals(color, color1.color) &&
                Objects.equals(highlight, color1.highlight);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, highlight);
    }

    @Override
    public String toString() {
        return "Color{" +
                "color='" + color + '\'' +
                ", highlight='" + highlight + '\'' +
                '}';
    }
}
