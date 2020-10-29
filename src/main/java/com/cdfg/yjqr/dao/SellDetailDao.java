package com.cdfg.yjqr.dao;

import com.cdfg.yjqr.pojo.dto.PickBillDto;
import com.cdfg.yjqr.pojo.dto.PickNumDto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SellDetailDao {
    List<PickBillDto> QrySellDetail(PickNumDto picknumdto);
}
