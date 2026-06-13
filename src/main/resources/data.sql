INSERT INTO sys_dept (id, parent_id, dept_name) VALUES (1, 0, '总部');
INSERT INTO sys_dept (id, parent_id, dept_name) VALUES (10, 1, '华东区');
INSERT INTO sys_dept (id, parent_id, dept_name) VALUES (11, 10, '上海一部');
INSERT INTO sys_dept (id, parent_id, dept_name) VALUES (20, 1, '华南区');

INSERT INTO sys_user (id, user_name, dept_id, data_scope) VALUES (1, 'admin', 1, 'ALL');
INSERT INTO sys_user (id, user_name, dept_id, data_scope) VALUES (2, 'manager_a', 10, 'DEPT_AND_CHILD');
INSERT INTO sys_user (id, user_name, dept_id, data_scope) VALUES (3, 'staff_a1', 11, 'SELF');
INSERT INTO sys_user (id, user_name, dept_id, data_scope) VALUES (4, 'manager_b', 20, 'DEPT');

INSERT INTO customer (id, customer_name, owner_user_id, dept_id, amount, create_time)
VALUES (1001, '上海客户A', 3, 11, 12000.00, CURRENT_TIMESTAMP);
INSERT INTO customer (id, customer_name, owner_user_id, dept_id, amount, create_time)
VALUES (1002, '华东客户B', 2, 10, 8600.00, CURRENT_TIMESTAMP);
INSERT INTO customer (id, customer_name, owner_user_id, dept_id, amount, create_time)
VALUES (1003, '华南客户C', 4, 20, 4300.00, CURRENT_TIMESTAMP);
INSERT INTO customer (id, customer_name, owner_user_id, dept_id, amount, create_time)
VALUES (1004, '总部客户D', 1, 1, 9900.00, CURRENT_TIMESTAMP);

