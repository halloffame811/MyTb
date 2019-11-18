package com.hzy.mytb.vo;

import lombok.Data;

@Data
public class ResultVO<T> {
    private String msg;
    private Integer code;
    private T data;
}
