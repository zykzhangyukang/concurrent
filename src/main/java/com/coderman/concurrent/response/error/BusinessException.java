package com.coderman.concurrent.response.error;

/**
 * @Author zhangyukang
 * @Date 2021/1/18 11:59
 * @Version 1.0
 **/
public class BusinessException extends RuntimeException{

    private BaseError baseError;

    public BusinessException(BaseError baseError){
        super(baseError.getErrorMsg());
        this.baseError=baseError;
    }

    public BusinessException(BaseError baseError,String customizedErrorMsg){
        super(customizedErrorMsg);
        this.baseError=baseError;
        this.baseError.setErrorMsg(customizedErrorMsg);
    }

    public BaseError getBaseError() {
        return baseError;
    }

    public void setBaseError(BaseError baseError) {
        this.baseError = baseError;
    }
}
