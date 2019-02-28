package com.gaohanghang.springbootmanagebooks.dao;

import com.gaohanghang.springbootmanagebooks.entity.Book;
import com.gaohanghang.springbootmanagebooks.entity.BookList;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description:
 * @author: Gao Hang Hang
 * @date 2019/02/28 16:22
 */
public interface BookMapper {
    List<BookList> getList();

    List<BookList> getListByQuery(@Param("query") String query);

    /*得到能被预约的书*/
    List<Book> getRes(@Param("isbn") String ISBN);
}
