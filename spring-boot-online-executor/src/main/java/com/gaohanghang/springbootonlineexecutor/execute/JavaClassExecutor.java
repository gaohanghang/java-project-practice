package com.gaohanghang.springbootonlineexecutor.execute;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 执行外部传来的一个代表Java类的byte数组
 * 执行过程：
 * 1. 清空HackSystem中的缓存
 * 2. new ClassModifier，并传入需要被修改的字节数组
 * 3. 调用ClassModifier#modifyUTF8Constant修改：
 *      java/lang/System -> com/jvm/ch9/remoteinvoke/HackSystem
 * 4. new一个类加载器，把字节数组加载为Class对象
 * 5. 通过反射调用Class对象的main方法
 * 6. 从HackSystem中获取返回结果
 */
public class JavaClassExecutor {

    /* 程序中正在运行的客户端代码个数 */
    private static volatile AtomicInteger runningCount = new AtomicInteger(0);

    public static String execute(byte[] classBytes) {
        // 2. new ClassModifier，并传入需要被修改的字节数组
        ClassModifier cm = new ClassModifier();

        // 3. 调用ClassModifier#modifyUTF8Constant修改
        byte[] modifyBytes = cm.modifyUTF8Constant("java/lang/System",
                "org/olexec/execute/HackSystem");

        // 4. new一个类加载器，把字节数据加载为Class对象


        return null;
    }
}
