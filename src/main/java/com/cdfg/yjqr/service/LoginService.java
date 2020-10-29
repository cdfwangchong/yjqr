package com.cdfg.yjqr.service;

import com.cdfg.yjqr.pojo.dto.UserDto;

import java.util.Map;

public interface LoginService {
    public Map<String, Object> login(UserDto userDto);
}
