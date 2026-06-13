# Spring Boot Data Scope Audit Demo

Spring Boot 数据权限 + 审计日志示例项目。

这个项目演示企业后台常见的两个能力：

- 数据权限：本人、本部门、本部门及下级、全部数据。
- 审计日志：通过注解和 AOP 记录关键操作。

项目使用 H2 内存数据库，启动即可运行，不依赖 MySQL、Redis 或外部中间件。

## 技术栈

| 类型 | 技术 |
|---|---|
| JDK | Java 17+ |
| 框架 | Spring Boot 3.3.x |
| ORM | MyBatis-Plus |
| 数据库 | H2 |
| 审计 | Spring AOP |

## 快速启动

```bash
mvn spring-boot:run
```

启动后访问：

```text
http://localhost:8081
```

## 示例用户

通过请求头 `X-User-Id` 模拟当前登录用户：

| 用户 | 账号 | 数据权限 |
|---:|---|---|
| 1 | admin | 全部数据 |
| 2 | manager_a | 本部门及下级 |
| 3 | staff_a1 | 本人数据 |
| 4 | manager_b | 本部门 |

## 验证接口

```bash
curl -H "X-User-Id: 1" http://localhost:8081/api/customers
curl -H "X-User-Id: 2" http://localhost:8081/api/customers
curl -H "X-User-Id: 3" http://localhost:8081/api/customers
curl -H "X-User-Id: 4" http://localhost:8081/api/customers
curl http://localhost:8081/api/audit-logs
```

## 代码重点

- `CurrentUserContext`：从请求头读取当前用户。
- `DataScopeService`：根据用户数据范围生成可访问部门。
- `CustomerService`：应用数据权限过滤。
- `@AuditLog`：声明需要审计的方法。
- `AuditLogAspect`：记录操作人、操作类型、耗时、是否成功。

## 适合扩展为付费版的内容

- SQL 拦截器版数据权限。
- 注解式数据权限。
- 字段脱敏。
- 字段变更记录。
- 登录日志。
- 多租户隔离。
- 与 Spring Security / Sa-Token 集成。

