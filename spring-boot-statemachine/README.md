# Spring StateMachine，教你快速实现一个状态机

> 原文链接 [Spring StateMachine，教你快速实现一个状态机](https://mp.weixin.qq.com/s/3swpZWJufg5k7jJeNiok5A)


> 运行效果 

![](https://ws4.sinaimg.cn/large/006tNc79ly1g2pbixgye6j30tj09t791.jpg)
 
> 通过上面的例子，我们可以对如何使用Spring StateMachine做如下小结：

- 定义状态和事件枚举
- 为状态机定义使用德所有状态以及初始状态
- 为状态机定义状态的迁移动作
- 为状态机指定监听处理器

> 注意 不是线程安全