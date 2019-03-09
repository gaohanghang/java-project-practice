package com.gaohanghang.springbootmanagebooks.controller;

import com.gaohanghang.springbootmanagebooks.entity.BookList;
import com.gaohanghang.springbootmanagebooks.entity.Reservation;
import com.gaohanghang.springbootmanagebooks.entity.ReservationResult;
import com.gaohanghang.springbootmanagebooks.entity.User;
import com.gaohanghang.springbootmanagebooks.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @Description:
 * @author: Gao Hang Hang
 * @date 2019/03/04 13:14
 */
@Controller
@RequestMapping("/managebooks")
public class ReaderController {

    @Autowired
    BookService bookService;

    @GetMapping(value = "/booklist")
    public String listBookList(Model model, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        model.addAttribute("user", user);
        List<BookList> list = bookService.getList();
        model.addAttribute("list", list);
        return "user_booklist";
    }

    @PostMapping(value = "query")
    public String listBookListById(Model model, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        model.addAttribute("user", user);
        String banme = request.getParameter("banme");
        List<BookList> list = bookService.getListByQuery(banme);
        model.addAttribute("list", list);
        return "user_booklist";
    }

    @GetMapping(value = "{ISBN}/booklist")
    public String listBookListById(
            Model model,
            HttpServletRequest request,
            @PathVariable(value = "ISBN") String ISBN,
            HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute("user");
        model.addAttribute("user", user);
        response.setContentType("text/html;charset=utf8");
        ReservationResult<Reservation> reservationResult; // 预定结果
        PrintWriter printWriter = null;
        Reservation reservation = null; //
        try {
            printWriter = response.getWriter();
            bookService.processRes(ISBN, user); // 预约图书
            reservationResult = new ReservationResult<>(true, reservation);
        } catch (Exception e) {
            reservationResult = new ReservationResult<>(false, "预约失败");
        }
        if (reservationResult.isSuccess()) {
            printWriter.print("<script>alert('预约成功');window.location.href='/managebooks/booklist';</script>");
        } else {
            printWriter.print("<script>alert('预约失败,请重新预约!');window.location.href='/managebooks/booklist';</script>");
        }
        List<BookList> list = bookService.getList();
        model.addAttribute("list", list);
        return "user_booklist";
    }

    @GetMapping(value = "/revervation")
    public void
}
