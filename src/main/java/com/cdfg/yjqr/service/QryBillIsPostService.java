package com.cdfg.yjqr.service;

import com.cdfg.yjqr.pojo.dto.LeavedDto;
import com.cdfg.yjqr.pojo.until.CustAddrlistEntity;
import com.cdfg.yjqr.pojo.until.Login;

import java.util.List;


public interface QryBillIsPostService {

    List<CustAddrlistEntity> qryPostBill(Login login);

    String updateLeaved(LeavedDto leavedDto);

}
