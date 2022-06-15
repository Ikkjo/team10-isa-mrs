INSERT INTO users(id, deleted, email, enabled, first_name, last_name, password, phone_number, role)
VALUES ('cd95284b-b6fe-46b4-ade2-491c28e55050', false, 'admin@rentr.com', true, 'Admin', 'Adminovic',  '$2a$12$aSdnp/ZQTnSDHWFuBZlybOIjhMWcY7GRNlZMHPYoQekbN3BaHM4AS', '', 'MAIN_ADMIN');
INSERT into admins(id) VALUES ('cd95284b-b6fe-46b4-ade2-491c28e55050');

INSERT INTO users(id, deleted, email, enabled, first_name, last_name, password, phone_number, role)
VALUES ('cd95284b-b6fe-46b4-ade2-491c28e55051', false, 'damir@rentr.com', true, 'Damir', 'Adamovic',  '$2a$12$aSdnp/ZQTnSDHWFuBZlybOIjhMWcY7GRNlZMHPYoQekbN3BaHM4AS', '', 'UNVERIFIED_ADMIN');
INSERT into admins(id) VALUES ('cd95284b-b6fe-46b4-ade2-491c28e55051');
