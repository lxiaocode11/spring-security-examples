package com.lxiaocode.common;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Map;

/**
 * @author lixiaofeng
 * @date 2020/8/25 22:33
 */
public class ResponseUtil {

    public static void send(HttpServletResponse response, Map data){

        JSONObject json = new JSONObject(data);

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = null;

        try {
            out = response.getWriter();
            out.append(json.toString());
            out.flush();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (out != null){
                out.close();
            }
        }
    }
}
