package com.gaohanghang.springbootmanagebooks.service;

import com.gaohanghang.springbootmanagebooks.dao.BookMapper;
import com.gaohanghang.springbootmanagebooks.entity.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Description:
 * @author: Gao Hang Hang
 * @date 2019/03/04 13:24
 */
public class BookServiceImpl implements BookService {

    @Autowired
    BookMapper bookMapper;

    @Override
    public List<BookList> getList() {
        return null;
    }

    @Override
    public List<BookList> getListByQuery(String query) {
        return null;
    }

    @Override
    public List<Book> getListById(int userId) {
        return null;
    }

    @Override
    public int deleteById(int bookId) {
        return 0;
    }

    @Override
    public void processRes(String ISBN, User user) {

    }

    @Override
    public List<ReservationDetail> getResById(User user) {
        return null;
    }

    @Override
    public void returnBookById(int borrowId) {

    }

    @Override
    public void addBookList(BookList bookList, int state) {

    }

    @Override
    public void insertBorrow(int reservationId, int operator) {

    }

    @Override
    public List<Reservation> getResInfo() {
        return null;
    }

    @Override
    public List<BorrowDetail> getBorInfo(User user) {
        return null;
    }

    @Override
    public List<ReservationDetail> getResList() {
        return null;
    }

    @Override
    public List<BorrowDetail> getBorList() {
        return null;
    }

    @Override
    public int deleteBookList(BookList bookList) {
        return 0;
    }
}
