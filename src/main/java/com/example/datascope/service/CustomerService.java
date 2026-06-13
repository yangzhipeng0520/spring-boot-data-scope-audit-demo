package com.example.datascope.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.datascope.annotation.AuditLog;
import com.example.datascope.entity.Customer;
import com.example.datascope.entity.DataScopeType;
import com.example.datascope.entity.SysUser;
import com.example.datascope.mapper.CustomerMapper;
import com.example.datascope.security.CurrentUserContext;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    private final CustomerMapper customerMapper;
    private final DataScopeService dataScopeService;

    public CustomerService(CustomerMapper customerMapper, DataScopeService dataScopeService) {
        this.customerMapper = customerMapper;
        this.dataScopeService = dataScopeService;
    }

    @AuditLog(action = "LIST_CUSTOMER", target = "customer")
    public List<Customer> listVisibleCustomers() {
        SysUser user = CurrentUserContext.get();
        if (user.getDataScope() == DataScopeType.ALL) {
            return customerMapper.selectList(new LambdaQueryWrapper<Customer>().orderByAsc(Customer::getId));
        }
        if (user.getDataScope() == DataScopeType.SELF) {
            return customerMapper.selectList(new LambdaQueryWrapper<Customer>()
                    .eq(Customer::getOwnerUserId, user.getId())
                    .orderByAsc(Customer::getId));
        }
        return customerMapper.selectByScope(user.getId(), dataScopeService.visibleDeptIds(user));
    }
}

