package com.hzy.mytb.MyTbException;

import com.hzy.mytb.mEnum.ResultVOEnum;
import lombok.Data;

@Data
public class MyTbMvcException extends RuntimeException{
    private  Integer code;

    public MyTbMvcException(ResultVOEnum resultVOEnum) {
        super(resultVOEnum.getMsg());
        this.code = resultVOEnum.getCode();
    }
}
