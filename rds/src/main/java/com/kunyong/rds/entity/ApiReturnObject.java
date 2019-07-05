package com.kunyong.rds.entity;

import java.io.Serializable;

/**
 * @author 贺
 * @Description: TODO
 */
public class ApiReturnObject implements Serializable {
    String errorCode="00";
    Object errorMessage;
    Object returnObject;
    String pageNumber;
    String pageSize;
    String totalElements;
    String totalPages;
    public ApiReturnObject(String pageNumber,String pageSize,String totalElements,String totalPages,String errorCode, Object errorMessage, Object returnObject) {
        super();
        this.pageNumber = pageNumber;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.returnObject = returnObject;
        this.pageSize = pageSize;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public Object getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(Object errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Object getReturnObject() {
        return returnObject;
    }

    public void setReturnObject(Object returnObject) {
        this.returnObject = returnObject;
    }

    public String getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(String pageNumber) {
        this.pageNumber = pageNumber;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public String getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(String totalElements) {
        this.totalElements = totalElements;
    }

    public String getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(String totalPages) {
        this.totalPages = totalPages;
    }
}