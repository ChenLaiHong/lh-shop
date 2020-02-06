package com.lh.shop.common.util;

import com.lh.entity.Result;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * Created by laiHom on 2019/8/22.
 */
public class ResponseUtil {
    public static void write(HttpServletResponse response, Object o)
            throws Exception {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.println(o.toString());
        out.flush();
        out.close();
    }

    public static Result res(int resultTotal){
        Result result = new Result();
        if (resultTotal > 0) {
            result.setSuccess(true);
            result.setMsg("操作成功");
        } else {
            result.setSuccess(false);
            result.setMsg("操作失败");
        }
        return result;
    }
}
