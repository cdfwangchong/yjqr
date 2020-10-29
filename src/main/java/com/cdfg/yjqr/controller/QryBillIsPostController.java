package com.cdfg.yjqr.controller;

import cn.cdfg.exceptionHandle.YjqrNotFoundException;
import com.cdfg.yjqr.pojo.dto.LeavedDto;
import com.cdfg.yjqr.pojo.until.CustAddrlistEntity;
import com.cdfg.yjqr.pojo.until.Login;
import com.cdfg.yjqr.pojo.until.Result;
import com.cdfg.yjqr.pojo.until.Token;
import com.cdfg.yjqr.service.QryBillIsPostService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.cdfg.yjqr.pojo.until.Constant.*;

/*
 * project name :自助邮寄
 * for:未邮寄提货单接口和已经邮寄的提货单接口
 * author：wangc
 * time：2020-10-10
 * */
@CrossOrigin
@RestController
@RequestMapping("/cdfg")
public class QryBillIsPostController {
    @Autowired
    private QryBillIsPostService qbipService=null;
    Logger logger = Logger.getLogger(QryBillIsPostController.class);

    /**
     * 邮寄提货单查询接口
     * @param login
     * @return
     */
    @PostMapping("/qrypostbill")
    @ResponseBody
    public Result<List<CustAddrlistEntity>> qryPostBill(HttpServletRequest request,@RequestBody Login login) {
        List<CustAddrlistEntity> beList;

        if (login == null){
            logger.error("邮寄提货单查询接口传入的参数值为null");
            throw new YjqrNotFoundException(errCode_5,errMsg_5);
        }

        String token = request.getHeader("Authorization");
//        new Token().CheckToken(token);

        beList = qbipService.qryPostBill(login);
        for (int i = 0; i < beList.size(); i++) {
            CustAddrlistEntity be = new CustAddrlistEntity();
            be = beList.get(i);
            String address = be.getRec_provincename()+be.getRec_cityname()+be.getRec_areaname()+be.getRec_townname()+be.getRec_detailaddress();
            logger.info("取到邮寄提货单接口返回值："+be.getRec_name()+"#"+be.getRec_xsdno()+"#"+be.getSeq_no()+address);
        }

        return new Result<List<CustAddrlistEntity>>(sucCode,sucMsg,beList);
    }

    /**
     * 邮寄提货单确认接口
     * @param leavedDto
     * @return
     */
    @PostMapping("/updateleaved")
    @ResponseBody
    public Result<String> updateLeaved(HttpServletRequest request,@RequestBody LeavedDto leavedDto) {

        if (leavedDto == null){
            logger.error("邮寄提货单查询接口传入的参数值为null");
            throw new YjqrNotFoundException(errCode_5,errMsg_5);
        }
        String token = request.getHeader("Authorization");
//        String worknumber = new Token().CheckToken(token);
//        logger.info("取到确认邮寄单的操作员"+worknumber+"序号"+leavedDto.getSeq_no());

        String ret_flag = qbipService.updateLeaved(leavedDto);
        if ("1002".equals(ret_flag)) {
            return new Result<String>(sucCode,sucMsg,"");
        }else {
            logger.info("该顾客邮寄商品确认失败"+leavedDto.getGwkh()+"商品序号："+leavedDto.getSeq_no());
            throw new YjqrNotFoundException(errCode_15,errMsg_15);
        }
    }
}
