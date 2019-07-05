package com.kunyong.rds.utils;

/**
 * @author 贺
 * @Title: Result
 * @ProjectName fwpg
 * @Description: TODO数据返回实体
 * @date 2018/8/1814:17
 */
public class Result {

    private Integer code;//状态码
    private Boolean success;//状态
    private String message;//消息
    private Object data;//数据对象

    /**
     * 无参构造器
     */
    public Result(){
        super();
    }

    /**
     * 只返回状态，状态码，消息
     * @param success
     * @param code
     * @param message
     */
    public Result(Boolean success, Integer code, String message){
        super();
        this.success =success;
        this.code=code;
        this.message=message;
    }

    /**
     * 只返回状态，状态码，数据对象
     * @param success
     * @param code
     * @param result
     */
    public Result(Boolean success, Integer code, Object result){
        super();
        this.success =success;
        this.code=code;
        this.data=result;
    }

    /**
     * 返回全部信息即状态，状态码，消息，数据对象
     * @param success
     * @param code
     * @param massege
     * @param result
     */
    public Result(Boolean success, Integer code, String massege, Object result){
        super();
        this.success =success;
        this.code=code;
        this.message=massege;
        this.data=result;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }



    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
