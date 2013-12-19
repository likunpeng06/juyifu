package cn.mixpay.engine.controller;

import cn.mixpay.core.type.OutputType;
import cn.mixpay.engine.response.IResponse;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by qatang on 13-12-17.
 */
public abstract class BaseController {
    protected final transient Logger logger = LoggerFactory.getLogger(this.getClass());

    protected void writeRes(HttpServletResponse response, IResponse res){
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("text/html;charset=utf-8");
        try{
            response.getWriter().print(res.output());
            response.getWriter().close();
        }catch(IOException e){
            logger.error(e.getMessage(),e);
        }
    }

    protected void writeRes(HttpServletResponse response, IResponse res, OutputType outputType){
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("text/html;charset=utf-8");
        try{
            response.getWriter().print(res.output(outputType));
            response.getWriter().close();
        }catch(IOException e){
            logger.error(e.getMessage(),e);
        }
    }
}
