package com.example.myapplication.begin.bean;

public class LoginBean {

    /**
     * code : 200
     * message : 登录成功
     * data : eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxOTk5OTk5OTk5OSIsImNyZWF0ZWQiOjE1ODYyNDUxMTgxNTgsImV4cCI6MTU4Njg0OTkxOH0.V7iQqe1W58nMEY830AyxJuXun3OTW3DVVSYO-T7YlPqmun8QZHUFkVforMuzUO8Hy_HT1pzoMDC8lGLt1kGNYg
     */

    private int code;
    private String message;
    private String data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
