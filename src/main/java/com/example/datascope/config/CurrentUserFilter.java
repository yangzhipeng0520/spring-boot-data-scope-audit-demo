package com.example.datascope.config;

import com.example.datascope.entity.SysUser;
import com.example.datascope.mapper.SysUserMapper;
import com.example.datascope.security.CurrentUserContext;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class CurrentUserFilter extends OncePerRequestFilter {
    private final SysUserMapper sysUserMapper;

    public CurrentUserFilter(SysUserMapper sysUserMapper) {
        this.sysUserMapper = sysUserMapper;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String userId = request.getHeader("X-User-Id");
        if (userId == null || userId.isBlank()) {
            userId = "1";
        }
        SysUser user = sysUserMapper.selectById(Long.valueOf(userId));
        CurrentUserContext.set(user);
        try {
            filterChain.doFilter(request, response);
        } finally {
            CurrentUserContext.clear();
        }
    }
}

