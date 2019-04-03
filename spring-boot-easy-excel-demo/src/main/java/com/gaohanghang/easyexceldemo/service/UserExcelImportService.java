package com.gaohanghang.easyexceldemo.service;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.gaohanghang.easyexceldemo.dao.UserRepository;
import com.gaohanghang.easyexceldemo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

/**
 * @Description:
 * @author: Gao Hang Hang
 * @date 2019/01/28 15:37
 */
@Service
public class UserExcelImportService {

    @Autowired
    private UserRepository userRepository;

    // 设置head
    public static List<List<String>> createTestListStringHead(){
        //写sheet3  模型上没有注解，表头数据动态传入
        List<List<String>> head = new ArrayList<List<String>>();
        List<String> headCoulumn1 = new ArrayList<String>();
        List<String> headCoulumn2 = new ArrayList<String>();
        List<String> headCoulumn3 = new ArrayList<String>();

        headCoulumn1.add("id");
        headCoulumn2.add("username");
        headCoulumn3.add("age");

        head.add(headCoulumn1);
        head.add(headCoulumn2);
        head.add(headCoulumn3);
        return head;
    }

    public void excelImport(MultipartFile file) throws IOException {
        List<Object> data = EasyExcelFactory.read(file.getInputStream(), new Sheet(1, 1, User.class));
        print(data);

        for (Object user : data) {
            userRepository.save((User) user);
        }
    }

    public void print(List<Object> datas){
        int i=0;
        for (Object ob:datas) {
            System.out.println(i++);
            System.out.println(ob);
        }
    }

    public void downloadTest() throws IOException {
        OutputStream outputStream = new FileOutputStream("/Users/gaohanghang/2007.xlsx");
        ExcelWriter writer = EasyExcelFactory.getWriter(outputStream);
        // 写第一个sheet, sheet1  数据全是List<String> 无模型映射关系
        Sheet sheet1 = new Sheet(1, 3);
        sheet1.setSheetName("第一个sheet");

        //设置列宽 设置每列的宽度
        sheet1.setHead(createTestListStringHead());
        //or 设置自适应宽度
        sheet1.setAutoWidth(Boolean.TRUE);
        writer.write1(createTestListObject(), sheet1);

        writer.finish();
    }

    public void download() throws IOException {
        OutputStream outputStream = new FileOutputStream("/Users/gaohanghang/2007.xlsx");
        ExcelWriter writer = EasyExcelFactory.getWriter(outputStream);
        // 写第一个sheet, sheet1  数据全是List<String> 无模型映射关系
        Sheet sheet1 = new Sheet(1, 3);
        sheet1.setSheetName("第一个sheet");

        //设置列宽 设置每列的宽度
        sheet1.setHead(createTestListStringHead());
        //or 设置自适应宽度
        sheet1.setAutoWidth(Boolean.TRUE);
        writer.write1(createTestListObject(), sheet1);

        writer.finish();
    }

    // 设置每列数据
    public List<List<Object>> createTestListObject() {
        List<List<Object>> object = new ArrayList<List<Object>>();

        List<User> all = userRepository.findAll();
        for (User user : all) {
            List<Object> da = new ArrayList<Object>();
            da.add(user.getId());
            da.add(user.getName());
            da.add(user.getAge());
            object.add(da);
        }
        return object;
    }

    // excel分页下载
    public void pageDownload(int page, int size) throws FileNotFoundException {
        Pageable pageable = PageRequest.of(page - 1, size);

        OutputStream outputStream = new FileOutputStream("/Users/gaohanghang/2009.xlsx");
        ExcelWriter writer = EasyExcelFactory.getWriter(outputStream);
        // 写第一个sheet, sheet1  数据全是List<String> 无模型映射关系
        Sheet sheet1 = new Sheet(1, 3);
        sheet1.setSheetName("第一个sheet");

        //设置列宽 设置每列的宽度
        sheet1.setHead(createTestListStringHead());
        //or 设置自适应宽度
        sheet1.setAutoWidth(Boolean.TRUE);


        // 设置数据
        List<List<Object>> object = new ArrayList<List<Object>>();
        Page<User> all = userRepository.findAll(pageable);
        List<User> content = all.getContent();

        for (User user : content) {
            List<Object> da = new ArrayList<Object>();
            da.add(user.getId());
            da.add(user.getName());
            da.add(user.getAge());
            object.add(da);
        }
        writer.write1(object, sheet1);
        writer.finish();
    }
}
