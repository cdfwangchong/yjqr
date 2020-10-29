package com.cdfg.yjqr.service.impl;

import cn.cdfg.exceptionHandle.ExceptionPrintMessage;
import cn.cdfg.exceptionHandle.YjqrNotFoundException;
import com.cdfg.yjqr.dao.QryBillIsPostDao;
import com.cdfg.yjqr.pojo.dto.LeavedDto;
import com.cdfg.yjqr.pojo.until.CustAddrlistEntity;
import com.cdfg.yjqr.pojo.until.Login;
import com.cdfg.yjqr.service.QryBillIsPostService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.cdfg.yjqr.pojo.until.Constant.*;


@Service
public class QryBillIsPostServiceImpl implements QryBillIsPostService {
    @Autowired
    private QryBillIsPostDao qbipDao=null;
    Logger logger = Logger.getLogger(QryBillIsPostServiceImpl.class);

    /**
     * 邮寄提货单查询接口
     * @param login
     * @return
     */
    @Override
    public List<CustAddrlistEntity> qryPostBill(Login login) {
        Map param = new HashMap<String,String>();
        param.put("i_gwkh",login.getGwkh());
        List<CustAddrlistEntity> beyList;

        try {
            qbipDao.qryPostBill(param);

            //取出结果集
            beyList = (List<CustAddrlistEntity>) param.get("yjRc");
        } catch (Exception e) {
            logger.error(new ExceptionPrintMessage().errorTrackSpace(e));
            logger.error("查找已邮寄的提货单存储过程返回值异常");
            throw new YjqrNotFoundException(errCode,errMsg);
        }
        if (beyList == null) {
            logger.error("该客户没有邮寄的商品"+login.getGwkh());
            throw new YjqrNotFoundException(errCode_14,errMsg_14);
        }
        return beyList;
    }

    @Override
    public String updateLeaved(LeavedDto leavedDto) {
        Map param = new HashMap<String,String>();
        param.put("i_gwkh",leavedDto.getGwkh());
        param.put("seq_no",leavedDto.getSeq_no());

        String ret_flag;

        try {
            qbipDao.updateLeaved(param);

            //取出结果集
            ret_flag = (String) param.get("ret_flag");
        } catch (Exception e) {
            logger.error(new ExceptionPrintMessage().errorTrackSpace(e));
            logger.error("查找已邮寄的提货单存储过程返回值异常");
            throw new YjqrNotFoundException(errCode,errMsg);
        }
        return ret_flag;
    }
}
