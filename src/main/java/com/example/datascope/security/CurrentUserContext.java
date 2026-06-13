package com.example.datascope.security;

import com.example.datascope.entity.SysUser;

public final class CurrentUserContext {
    private static final ThreadLocal<SysUser> HOLDER = new ThreadLocal<>();

    private CurrentUserContext() {
    }

    public static void set(SysUser user) {
        HOLDER.set(user);
    }

    public static SysUser get() {
        return HOLDER.get();
    }

    public static void clear() {
        HOLDER.remove();
    }
}

