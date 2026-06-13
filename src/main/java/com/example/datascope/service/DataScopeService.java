package com.example.datascope.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.datascope.entity.DataScopeType;
import com.example.datascope.entity.SysDept;
import com.example.datascope.entity.SysUser;
import com.example.datascope.mapper.SysDeptMapper;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class DataScopeService {
    private final SysDeptMapper sysDeptMapper;

    public DataScopeService(SysDeptMapper sysDeptMapper) {
        this.sysDeptMapper = sysDeptMapper;
    }

    public List<Long> visibleDeptIds(SysUser user) {
        if (user.getDataScope() == DataScopeType.ALL) {
            return List.of();
        }
        if (user.getDataScope() == DataScopeType.DEPT) {
            return List.of(user.getDeptId());
        }
        if (user.getDataScope() == DataScopeType.SELF) {
            return List.of(user.getDeptId());
        }
        List<Long> ids = new ArrayList<>();
        collectChildren(user.getDeptId(), ids);
        return ids;
    }

    private void collectChildren(Long deptId, List<Long> ids) {
        ids.add(deptId);
        List<SysDept> children = sysDeptMapper.selectList(
                new LambdaQueryWrapper<SysDept>().eq(SysDept::getParentId, deptId));
        for (SysDept child : children) {
            collectChildren(child.getId(), ids);
        }
    }
}

