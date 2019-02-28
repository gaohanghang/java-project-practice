package com.gaohanghang.springbootmanagebooks.dao;

import com.gaohanghang.springbootmanagebooks.entity.MailDetail;

import java.util.List;

/**
 * @Description:
 * @author: Gao Hang Hang
 * @date 2019/02/28 16:28
 */
public interface MailMapper {
    List<MailDetail> returnReminder(); //返回提醒

    // 注意一下这里MailDetail.btime字段为空
    List<MailDetail> resReminder(); //预定提醒
}
