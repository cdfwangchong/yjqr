package com.cdfg.yjqr.controller;

import cn.cdfg.exceptionHandle.YjqrNotFoundException;
import com.cdfg.yjqr.pojo.dto.UserDto;
import com.cdfg.yjqr.pojo.until.Result;
import com.cdfg.yjqr.service.LoginService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;


import java.util.Map;

import static com.cdfg.yjqr.pojo.until.Constant.*;


@CrossOrigin
@RestController
@RequestMapping("/cdfg")
public class LoginController {

    @Autowired
    private LoginService loginserice = null;

    Logger logger = Logger.getLogger(LoginController.class);


    /**
     *登录
     * @param thduser
     * @return
     */
    @PostMapping(value = "/login")
    @ResponseBody
    public Result<Map<String, Object>> Login(@RequestBody UserDto thduser) {
        if (thduser == null) {
            logger.error("获取到的对象值为空");
            throw new YjqrNotFoundException(errCode_5,errMsg_5);
        }

        UserDto userdto = new UserDto();
        String userID = thduser.getUserId();

        String strToEncrypt = thduser.getPassWord();
        //获取加密后的密码
       //String passWord = AES.encrypt(strToEncrypt,key);
        String passWord = DigestUtils.md5DigestAsHex(strToEncrypt.getBytes());

        thduser.setPassWord(passWord);

        userdto.setPassWord(passWord);
        userdto.setUserId(userID);
        Map<String, Object> tokenMap= loginserice.login(userdto);

        return new Result<Map<String, Object>>(sucCode,sucMsg,tokenMap);
    }
}
