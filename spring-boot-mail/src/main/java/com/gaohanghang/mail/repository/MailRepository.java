package com.gaohanghang.mail.repository;

import com.gaohanghang.mail.entity.OaEmail;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Description: 邮件管理
 * @author: Gao Hang Hang
 * @date 2019/01/29 11:47
 */
public interface MailRepository extends JpaRepository<OaEmail, Integer> {
}
