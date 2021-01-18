package com.coderman.concurrent.util;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author zhangyukang
 * @Date 2021/1/18 12:07
 * @Version 1.0
 **/
public class HttpUtil {

    public static void response(HttpServletResponse response,String json){
        response.setContentType("application/json;charset=utf-8;");
        try {
            response.getWriter().println(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
