package com.gaohanghang.mail.common.util;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @Description:
 * @author: Gao Hang Hang
 * @date 2019/01/30 18:34
 */
public class CommonUtil {
    private static ObjectMapper mapper;

    public static synchronized ObjectMapper getMapperInstance(boolean createNew) {
        if (createNew) {
            return new ObjectMapper();
        } else if (mapper == null) {
            mapper = new ObjectMapper();
        }
        return mapper;
    }
}
