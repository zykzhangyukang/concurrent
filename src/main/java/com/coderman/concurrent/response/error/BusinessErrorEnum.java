package com.coderman.concurrent.response.error;

/**
 * @Author zhangyukang
 * @Date 2021/1/18 12:02
 * @Version 1.0
 **/
public enum BusinessErrorEnum implements BaseError{

    PARAMETER_ERROR(10001,"参数错误");

    private Integer code;
    private String errorMsg;

    BusinessErrorEnum(Integer code, String errorMsg) {
        this.code = code;
        this.errorMsg = errorMsg;
    }

    @Override
    public String getErrorMsg() {
        return this.errorMsg;
    }

    @Override
    public Integer getErrorCode() {
        return this.code;
    }

    @Override
    public void setErrorMsg(String errorMsg) {
        this.errorMsg=errorMsg;
    }
}
