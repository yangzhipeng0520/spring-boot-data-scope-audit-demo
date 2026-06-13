package com.example.datascope.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.datascope.entity.Customer;
import java.util.Collection;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CustomerMapper extends BaseMapper<Customer> {
    List<Customer> selectByScope(@Param("userId") Long userId, @Param("deptIds") Collection<Long> deptIds);
}

