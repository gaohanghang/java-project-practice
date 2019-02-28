package com.gaohanghang.springbootmanagebooks.entity;

import java.io.Serializable;

/**
 * @Description:
 * @author: Gao Hang Hang
 * @date 2019/02/28 12:43
 */
public class Book implements Serializable {
    private int bookId;
    private String ISBN;
    private String location;
    private int state;
    private int operator;

    public Book(int bookId, String ISBN, String location, int state, int operator) {
        this.bookId = bookId;
        this.ISBN = ISBN;
        this.location = location;
        this.state = state;
        this.operator = operator;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getOperator() {
        return operator;
    }

    public void setOperator(int operator) {
        this.operator = operator;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", ISBN='" + ISBN + '\'' +
                ", location='" + location + '\'' +
                ", state=" + state +
                ", operator=" + operator +
                '}';
    }
}
