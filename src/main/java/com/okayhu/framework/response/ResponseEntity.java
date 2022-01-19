package com.okayhu.framework.response;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author okayhu
 * @date 2022/1/18
 */
@Getter
@Setter
public class ResponseEntity<T> implements Serializable {

    private static final long serialVersionUID = -8023430878923839788L;

    private static Integer SUCCESS_CODE = 200;
    private static Integer FAIL_CODE = 400;

    private Integer code;
    private String message;
    private T data;

    private ResponseEntity(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    private ResponseEntity(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> ResponseEntity<T> success() {
        return new ResponseEntity<>(SUCCESS_CODE, null);
    }

    public static <T> ResponseEntity<T> success(T data) {
        return new ResponseEntity<>(SUCCESS_CODE, null, data);
    }

    public static <T> ResponseEntity<T> fail(String message) {
        return new ResponseEntity<>(FAIL_CODE, message);
    }

    public static <T> ResponseEntity<T> fail(String message, T data) {
        return new ResponseEntity<>(FAIL_CODE, message, data);
    }
}
