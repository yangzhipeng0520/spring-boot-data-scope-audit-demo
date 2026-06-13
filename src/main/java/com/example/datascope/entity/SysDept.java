package com.example.datascope.entity;

import com.baomidou.mybatisplus.annotation.TableName;

@TableName("sys_dept")
public class SysDept {
    private Long id;
    private Long parentId;
    private String deptName;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getParentId() { return parentId; }
    public void setParentId(Long parentId) { this.parentId = parentId; }
    public String getDeptName() { return deptName; }
    public void setDeptName(String deptName) { this.deptName = deptName; }
}

