DROP TABLE IF EXISTS audit_log;
DROP TABLE IF EXISTS customer;
DROP TABLE IF EXISTS sys_user;
DROP TABLE IF EXISTS sys_dept;

CREATE TABLE sys_dept (
    id BIGINT PRIMARY KEY,
    parent_id BIGINT,
    dept_name VARCHAR(64) NOT NULL
);

CREATE TABLE sys_user (
    id BIGINT PRIMARY KEY,
    user_name VARCHAR(64) NOT NULL,
    dept_id BIGINT NOT NULL,
    data_scope VARCHAR(32) NOT NULL
);

CREATE TABLE customer (
    id BIGINT PRIMARY KEY,
    customer_name VARCHAR(128) NOT NULL,
    owner_user_id BIGINT NOT NULL,
    dept_id BIGINT NOT NULL,
    amount DECIMAL(18, 2) NOT NULL,
    create_time TIMESTAMP NOT NULL
);

CREATE TABLE audit_log (
    id BIGINT PRIMARY KEY,
    user_id BIGINT,
    user_name VARCHAR(64),
    action VARCHAR(64) NOT NULL,
    target VARCHAR(128),
    success BOOLEAN NOT NULL,
    cost_ms BIGINT NOT NULL,
    error_message VARCHAR(512),
    create_time TIMESTAMP NOT NULL
);

