package com.hzy.mytb.MyTbException;

import com.hzy.mytb.config.AddressConfig;
import com.hzy.mytb.utils.ResultVOUtils;
import com.hzy.mytb.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class MyTbExceptionHandler {
    @Autowired
    private AddressConfig addressConfig;

    @ExceptionHandler(value = MyTbAppException.class)//处理返回给前端的异常
    @ResponseBody
    @ResponseStatus(value = HttpStatus.FORBIDDEN)//返回的status参数为403，和业务的code有所区别
    public ResultVO handleMyTbAPPException(MyTbAppException e) {
        log.error("捕获到异常[" + e.getMessage() + "],错误码：[" + e.getCode() + "] 并处理异常!");
        return ResultVOUtils.error(e);
    }

    @ExceptionHandler(value = MyTbMvcException.class)//处理返回给后端管理界面的异常
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    public ModelAndView handleMyTbMvcException(MyTbMvcException e
    ) {
        log.error("【捕获到MyTbMvcException】------>");
        Map<String, Object> map = new HashMap<>();
        map.put("msg", e.getMessage());
        map.put("code", e.getCode());
        map.put("url", addressConfig.getUrl());
        ModelAndView modelAndView = new ModelAndView("freemarker/result/error", map);
        return modelAndView;
    }

    @ExceptionHandler(value = Exception.class)//处理返回给后端管理界面的异常
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    public ModelAndView handleException(Exception e
    ) {
        log.error("【Exception】------>"+e.getMessage());
        Map<String, Object> map = new HashMap<>();
        map.put("msg","服务器内部错误："+ e.getMessage());
        map.put("code",500);
        map.put("url", addressConfig.getUrl());
        ModelAndView modelAndView = new ModelAndView("freemarker/result/error", map);
        return modelAndView;
    }
}
