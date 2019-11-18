package com.hzy.mytb.MyTbException;

import com.hzy.mytb.mEnum.ResultVOEnum;
import lombok.Data;

@Data
public class MyTbAppException extends  RuntimeException {
    private  Integer code;

    public MyTbAppException(ResultVOEnum resultVOEnum) {
        super(resultVOEnum.getMsg());
        this.code = resultVOEnum.getCode();
    }
    public MyTbAppException(String msg, Integer code) {
        super(msg);
        this.code =code;
    }
}
