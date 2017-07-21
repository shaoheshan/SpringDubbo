package com.heshan.dubbo.interceptor;

import com.heshan.framework.common.http.HttpUtils;
import com.heshan.framework.exception.ActionException;
import com.heshan.framework.exception.BaseException;
import com.heshan.framework.log.LogUtils;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


public class ExceptionHandler implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object object,
            Exception exception) {

        BaseException e = null;

        switch (exception.getClass().getSimpleName()) {

        case "WebException":
            // web请求返回，区别同步异步
            LogUtils.serviceLog("[WEB]异常[url]" + request.getRequestURL().toString() + "[info]"
                    + (exception.getMessage()));
            if (dealWriteAjax(request, response, exception)) {
                return null;
            }
            return new ModelAndView("forward:/pages/error/404.html");

        default:
            // 其它错误记录日志
            LogUtils.error("[其它]异常[url]" + request.getRequestURL().toString() + "[info]" + exception.toString(),
                    exception);
             e=new ActionException(exception);
            // 处理 AJAX
            boolean isAjax = HttpUtils.isAjax(request);
            if (isAjax) {
                respWriter(response, e.toJson());
                return null;
            } else {
                request.setAttribute("exceptionResp", e.getMessage());
            }
            return new ModelAndView("/error/500");
        }
    }

    /**
     * 
     * @Description:处理ajax请求
     * @param request
     * @param response
     * @param exception
     * @return
     * @return boolean
     * @throws null
     */
    private boolean dealWriteAjax(HttpServletRequest request, HttpServletResponse response, Exception exception) {
        BaseException e = (BaseException) exception;
        // 此为可控制异常，记录日志
        LogUtils.error(e.getMessage(), new Throwable(e));
        request.setAttribute("exceptionResp", e.getMessage());
        boolean isAjax = HttpUtils.isAjax(request) || e.isAjax();
        if (isAjax) {
            respWriter(response, e.toJson());
        }
        return isAjax;
    }

    /**
     * 
     * @Description:写数据流到response
     * @param response
     * @param str
     * @return void
     * @throws null
     */
    private void respWriter(HttpServletResponse response, String str) {
        try {
            response.setContentType("application/json;charset=UTF-8");
            PrintWriter pw = response.getWriter();
            pw.println(str);
            pw.flush();
            pw.close();
        } catch (IOException ex) {
            LogUtils.error(ex.getMessage(), new Throwable(ex));
        }
    }

}