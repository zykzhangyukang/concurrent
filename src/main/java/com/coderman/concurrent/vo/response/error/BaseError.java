package com.coderman.concurrent.vo.response.error;

/**
 * @Author zhangyukang
 * @Date 2021/1/18 11:58
 * @Version 1.0
 **/
public interface BaseError {
    String getErrorMsg();
    Integer getErrorCode();
    void setErrorMsg(String errorMsg);
}
