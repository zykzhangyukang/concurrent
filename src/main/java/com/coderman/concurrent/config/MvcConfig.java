package com.coderman.concurrent.config;

import com.coderman.concurrent.util.HttpUtil;
import com.coderman.concurrent.vo.response.RestApiResponse;
import com.coderman.concurrent.vo.response.error.BusinessException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.HashMap;
import java.util.List;

/**
 * @Author zhangyukang
 * @Date 2021/1/18 11:58
 * @Version 1.0
 **/
@Configuration
@Slf4j
public class MvcConfig implements WebMvcConfigurer {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {
        resolvers.add((httpServletRequest, httpServletResponse, o, e) -> {
            final String requestURL=httpServletRequest.getRequestURL().toString();
            final String defaultErrorMsg="系统异常,请联系管理员";
            final HashMap<String,Object> error=new HashMap<>();
            log.info("请求地址:{}",requestURL);
            if(e instanceof BusinessException){
                BusinessException businessException= (BusinessException) e;
                log.error("业务异常,errorMsg:{},errorCode:{}",businessException.getBaseError().getErrorMsg(),businessException.getBaseError().getErrorCode());
                error.put("errorCode",businessException.getBaseError().getErrorCode());
                error.put("errorMsg",businessException.getBaseError().getErrorMsg());
            }else {
                log.error("系统异常,errorMsg:{},errorCode:{}",e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(),e);
                error.put("errorCode",HttpStatus.INTERNAL_SERVER_ERROR.value());
                error.put("errorMsg",defaultErrorMsg);
            }
            try {
                HttpUtil.response(httpServletResponse,objectMapper.writeValueAsString(RestApiResponse.error(error)));
            } catch (JsonProcessingException jsonProcessingException) {
                jsonProcessingException.printStackTrace();
            }
            return  new ModelAndView();
        });
    }
}
