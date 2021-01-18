package com.coderman.concurrent.vo.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;

/**
 * @Author zhangyukang
 * @Date 2021/1/18 11:53
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestApiResponse<T> {
    private boolean success;
    private T data;

    public static <T> RestApiResponse<T> success(){
        return new RestApiResponse<>(true,null);
    }

    public static <T> RestApiResponse<T> success(T data){
        return new RestApiResponse<>(true,data);
    }

    public static RestApiResponse<HashMap<String, Object>> error(HashMap<String, Object> error) {
        return new RestApiResponse<>(false,error);
    }
}
