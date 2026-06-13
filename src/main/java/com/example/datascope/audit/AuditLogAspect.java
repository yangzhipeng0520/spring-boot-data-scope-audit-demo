package com.example.datascope.audit;

import com.example.datascope.annotation.AuditLog;
import com.example.datascope.entity.AuditRecord;
import com.example.datascope.entity.SysUser;
import com.example.datascope.mapper.AuditRecordMapper;
import com.example.datascope.security.CurrentUserContext;
import java.time.LocalDateTime;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AuditLogAspect {
    private final AuditRecordMapper auditRecordMapper;

    public AuditLogAspect(AuditRecordMapper auditRecordMapper) {
        this.auditRecordMapper = auditRecordMapper;
    }

    @Around("@annotation(auditLog)")
    public Object record(ProceedingJoinPoint point, AuditLog auditLog) throws Throwable {
        long start = System.currentTimeMillis();
        try {
            Object result = point.proceed();
            save(auditLog, true, System.currentTimeMillis() - start, null);
            return result;
        } catch (Throwable ex) {
            save(auditLog, false, System.currentTimeMillis() - start, ex.getMessage());
            throw ex;
        }
    }

    private void save(AuditLog auditLog, boolean success, long costMs, String errorMessage) {
        SysUser user = CurrentUserContext.get();
        AuditRecord record = new AuditRecord();
        if (user != null) {
            record.setUserId(user.getId());
            record.setUserName(user.getUserName());
        }
        record.setAction(auditLog.action());
        record.setTarget(auditLog.target());
        record.setSuccess(success);
        record.setCostMs(costMs);
        record.setErrorMessage(errorMessage);
        record.setCreateTime(LocalDateTime.now());
        auditRecordMapper.insert(record);
    }
}

