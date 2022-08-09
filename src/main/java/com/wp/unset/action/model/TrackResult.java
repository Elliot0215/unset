package com.wp.unset.action.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TrackResult {
    private String type;
    private Integer errorCode;
    private Integer elapsedTime;
    private List<List<KeyResult>> translateResult;

    public TrackResult(String type, Integer errorCode, Integer elapsedTime, List<List<KeyResult>> translateResult) {
        this.type = type;
        this.errorCode = errorCode;
        this.elapsedTime = elapsedTime;
        this.translateResult = translateResult;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public Integer getElapsedTime() {
        return elapsedTime;
    }

    public void setElapsedTime(Integer elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    public List<List<KeyResult>> getTranslateResult() {
        return translateResult;
    }

    public void setTranslateResult(List<List<KeyResult>> translateResult) {
        this.translateResult = translateResult;
    }
}
