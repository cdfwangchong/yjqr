package com.cdfg.yjqr.pojo.until;

import cn.cdfg.exceptionHandle.YjqrNotFoundException;

import java.util.Map;

import static com.cdfg.yjqr.pojo.until.Constant.errCode_4;
import static com.cdfg.yjqr.pojo.until.Constant.errMsg_4;

public class Token {

    public String CheckToken(String token) {
        if ("".equals(token) || token==null) {
            throw new YjqrNotFoundException(errCode_4,errMsg_4);
        }
        //验证Token是否有效
        Map<String, Object> resultMap = new Jwt().validToken(token);
        boolean isSuccess = (boolean) resultMap.get("isSuccess");
        int status = (int) resultMap.get("status");
        String Msg = (String) resultMap.get("Msg");
        if (!isSuccess) {
            throw new YjqrNotFoundException(status,Msg);
        }

        DtoJwt dtojwt = new DtoJwt(token);
        String worknumber = dtojwt.getWorknumber();
        return worknumber;
    }
}
