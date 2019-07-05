package com.kunyong.rds.utils;


import java.util.List;

/**
 * 我知道这里应该写注释,可是我奏似不想写，你说气人不气人
 *
 * @author 贺
 * @create 2019/6/6
 */
public class ResponseUtil<T> {
    // 返回响应吗 1：成功 0：失败
    private int responseCode =1;

    private List<T> data;

    private String errMessage;


    public ResponseUtil() {
    }

    public ResponseUtil(List<T> data) {
        this.data = data;
    }


    public ResponseUtil(int responseCode, List<T> data) {
        this.responseCode = responseCode;
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResponseUtil{" +
                "responseCode=" + responseCode +
                ", data=" + data +
                ", errMessage=" + errMessage +
                '}';
    }

    public String getErrMessage() {
        return errMessage;
    }

    public void setErrMessage(String errMessage) {
        this.errMessage = errMessage;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
