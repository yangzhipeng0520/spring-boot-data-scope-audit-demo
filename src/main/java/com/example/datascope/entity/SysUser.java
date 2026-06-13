package com.example.datascope.entity;

import com.baomidou.mybatisplus.annotation.TableName;

@TableName("sys_user")
public class SysUser {
    private Long id;
    private String userName;
    private Long deptId;
    private DataScopeType dataScope;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }
    public Long getDeptId() { return deptId; }
    public void setDeptId(Long deptId) { this.deptId = deptId; }
    public DataScopeType getDataScope() { return dataScope; }
    public void setDataScope(DataScopeType dataScope) { this.dataScope = dataScope; }
}

