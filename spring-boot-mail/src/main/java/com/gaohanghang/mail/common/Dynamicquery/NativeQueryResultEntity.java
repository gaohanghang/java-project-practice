package com.gaohanghang.mail.common.Dynamicquery;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description:
 * @author: Gao Hang Hang
 * @date 2019/01/30 13:01
 */
@Target(ElementType.TYPE) // 注解的作用目标
@Retention(RetentionPolicy.RUNTIME) // 默认的保留策略，注解会在class字节码文件中存在，但运行时无法获得，
public @interface NativeQueryResultEntity {

}
