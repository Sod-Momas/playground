package cc.momas.apache.hc;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @param <T> 返回的数据类型
 * @author Chen
 */
public class BaseResponse<T> implements Serializable {

    private int code = 20000;
    private String message = "OK";
    private T data;
    public Map<String, ?> extData = new HashMap<>();
    public long total;

    /*---------------------------/
    /   constructor method       /
    /---------------------------*/

    public BaseResponse() {
    }


    /*---------------------------/
    /      static method         /
    /---------------------------*/

    public static BaseResponse<String> ok() {
        return ok(null);
    }

    public static <T> BaseResponse<T> ok(T t) {
        BaseResponse<T> resp = new BaseResponse<>();
        resp.data = t;
        return resp;
    }

    public static BaseResponse<String> error(String message) {
        return error(message, null);
    }

    public static <T> BaseResponse<T> error(String message, T t) {
        BaseResponse<T> resp = new BaseResponse<>();
        resp.message = message;
        resp.data = t;
        return resp;
    }


    /*---------------------------/
    /      get and set           /
    /---------------------------*/

    public Map<String, ?> getExtData() {
        return extData;
    }

    public BaseResponse setExtData(Map<String, ? extends Object> extData) {
        this.extData = extData;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public BaseResponse setMessage(String message) {
        this.message = message;
        return this;
    }

    public T getData() {
        return data;
    }

    public BaseResponse setData(T data) {
        this.data = data;
        return this;
    }

    public int getCode() {

        return code;
    }

    public BaseResponse setCode(int code) {
        this.code = code;
        return this;
    }

    @Override
    public String toString() {
        return "BaseResponse{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                ", extData=" + extData +
                '}';
    }
}
