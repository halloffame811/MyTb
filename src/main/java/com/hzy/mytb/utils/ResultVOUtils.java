package com.hzy.mytb.utils;

import com.hzy.mytb.MyTbException.MyTbAppException;
import com.hzy.mytb.mEnum.ResultVOEnum;
import com.hzy.mytb.vo.ResultVO;

public class ResultVOUtils {
    public static ResultVO success(Object data){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(ResultVOEnum.SUCCESS.getCode());
        resultVO.setMsg(ResultVOEnum.SUCCESS.getMsg());
        resultVO.setData(data);
        return resultVO;

    }
    public static ResultVO error(String msg,Integer code){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(code);
        resultVO.setMsg(msg);
        resultVO.setData(null);
        return resultVO;

    }
    public static ResultVO error(ResultVOEnum resultVOEnum){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(resultVOEnum.getCode());
        resultVO.setMsg(resultVOEnum.getMsg());
        resultVO.setData(null);
        return resultVO;
    }
      public static ResultVO error(MyTbAppException e){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(e.getCode());
        resultVO.setMsg(e.getMessage());
        resultVO.setData(null);
        return resultVO;
    }

}
