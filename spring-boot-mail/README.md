# spring-boot-mail

邮件发送服务，文本，附件，模板，队列，多线程，定时任务实现多种功能！！！

## 开发环境

JDK1.8、Maven、Idea、SpringBoot1.5.9、spring-boot-starter-mail、spring-boot-starter-thymeleaf、spring-boot-starter-freemarker、Dubbo、Zookeeper、Redis、Spring data jpa

## 流程图

### 平台架构
![输入图片说明](https://git.oschina.net/uploads/images/2017/0801/190708_991f282a_87650.png "2574887637.png")

### 进程内邮件队列
![邮件队列](https://git.oschina.net/uploads/images/2017/0804/135111_3b197795_87650.png "邮件队列.png")

## 项目结构

- 普通文本发送
- 富文本发送(图片、附件)
- freeMarker模版发送邮件
- thymeleaf模版发送邮件


# 评测生成模版时间对比(1000次循环)


- Thymeleaf用时:2686ms
- Freemarker用时:498ms

对比测试，建议使用Freemarker模版




