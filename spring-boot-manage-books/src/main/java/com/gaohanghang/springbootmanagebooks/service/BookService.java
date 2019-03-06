package com.gaohanghang.springbootmanagebooks.service;

import com.gaohanghang.springbootmanagebooks.entity.*;

import java.util.List;

/**
 * @Description:
 * @author: Gao Hang Hang
 * @date 2019/03/04 13:17
 */
public interface BookService {
    /*
     user
    */
    List<BookList> getList();

    //
    List<BookList> getListByQuery(String query);

    // 获取借阅的图书
    List<Book> getListById(int userId);

    // 通过bookId删除图书
    int deleteById(int bookId);

    // 预约图书
    void processRes(String ISBN, User user);

    // 获取预定详情
    List<ReservationDetail> getResById(User user);

    // 归还图书
    void returnBookById(int borrowId);

    /*admin*/
    void addBookList(BookList bookList, int state);

    //
    void insertBorrow(int reservationId,int operator);

    // 获取预约
    List<Reservation> getResInfo();

    //
    List<ReservationDetail> getResList();

    // 借阅详情
    List<BorrowDetail> getBorInfo(User user);

    //
    List<BorrowDetail> getBorList();

    // 删除booklist
    int deleteBookList(BookList bookList);
}
