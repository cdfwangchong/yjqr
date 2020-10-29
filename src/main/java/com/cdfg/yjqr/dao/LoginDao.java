package com.cdfg.yjqr.dao;

import com.cdfg.yjqr.pojo.dto.ThduserDto;
import com.cdfg.yjqr.pojo.dto.UserDto;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginDao {
    ThduserDto selectByPrimaryKey(UserDto userDto);
}
