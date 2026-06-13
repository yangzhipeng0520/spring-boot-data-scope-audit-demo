package com.example.datascope.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.datascope.entity.AuditRecord;
import com.example.datascope.mapper.AuditRecordMapper;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class AuditQueryService {
    private final AuditRecordMapper auditRecordMapper;

    public AuditQueryService(AuditRecordMapper auditRecordMapper) {
        this.auditRecordMapper = auditRecordMapper;
    }

    public List<AuditRecord> recentLogs() {
        return auditRecordMapper.selectList(new LambdaQueryWrapper<AuditRecord>()
                .orderByDesc(AuditRecord::getCreateTime)
                .last("limit 20"));
    }
}

