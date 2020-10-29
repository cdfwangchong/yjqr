package com.cdfg.yjqr.service.impl;

import com.cdfg.yjqr.dao.SellDetailDao;
import com.cdfg.yjqr.pojo.dto.PickBillDto;
import com.cdfg.yjqr.pojo.dto.PickNumDto;
import com.cdfg.yjqr.service.QryBillDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QryBillDetailServiceImpl implements QryBillDetailService {
    @Autowired
    SellDetailDao selldetaildao;

    @Override
    public List<PickBillDto> getselldetail(PickNumDto picknumdto) {
        return selldetaildao.QrySellDetail(picknumdto);
    }
}
