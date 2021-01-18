package com.example.belajarAPI.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResultVO {
    private Integer status;
    private String message;
    private Object results;

    public ResultVO() {
    }

    public ResultVO(Integer status, String message, Object results) {
        this.status = status;
        this.message = message;
        this.results = results;
    }
}