package com.example.forum.result;

import org.springframework.stereotype.Service;

/**
 * @author genghaoran
 */
public enum ResultCodeMessage {
    //成功
    SUCCESS(200, "successful"),
    // 重定向
    REDIRECT(301, "redirect"),
    // 资源未找到
    NOT_FOUND(404, "not found"),
    // 服务器错误
    SERVER_ERROR(500,"server error"),

    LOGIN_ERROR(501, "login error"),

    USERNAME_PASSWORD_ERROR(500, "username or password error");



    private int code;
    private String message;

    ResultCodeMessage(int code, String message){
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
