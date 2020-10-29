package com.cdfg.yjqr.dao;

import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface QryBillIsPostDao {

    Map qryPostBill(Map<String, String> param);

    Map updateLeaved(Map<String, String> param);
}
