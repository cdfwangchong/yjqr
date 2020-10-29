package com.cdfg.yjqr.service;

import com.cdfg.yjqr.pojo.dto.PickBillDto;
import com.cdfg.yjqr.pojo.dto.PickNumDto;

import java.util.List;

public interface QryBillDetailService {

    List<PickBillDto> getselldetail(PickNumDto picknumdto);
}
