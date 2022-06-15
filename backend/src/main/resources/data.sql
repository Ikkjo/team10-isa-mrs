-- ADMINS
INSERT INTO users(id, deleted, email, enabled, first_name, last_name, password, phone_number, role)
VALUES ('cd95284b-b6fe-46b4-ade2-491c28e55050', false, 'admin@rentr.com', true, 'Admin', 'Adminovic',  '$2a$12$aSdnp/ZQTnSDHWFuBZlybOIjhMWcY7GRNlZMHPYoQekbN3BaHM4AS', '', 'MAIN_ADMIN');
INSERT into admins(id) VALUES ('cd95284b-b6fe-46b4-ade2-491c28e55050');

INSERT INTO users(id, deleted, email, enabled, first_name, last_name, password, phone_number, role)
VALUES ('cd95284b-b6fe-46b4-ade2-491c28e55051', false, 'damir@rentr.com', true, 'Damir', 'Adamovic',  '$2a$12$aSdnp/ZQTnSDHWFuBZlybOIjhMWcY7GRNlZMHPYoQekbN3BaHM4AS', '', 'UNVERIFIED_ADMIN');
INSERT into admins(id) VALUES ('cd95284b-b6fe-46b4-ade2-491c28e55051');

/* Vacation Home Owner 1
   email: test@gmail.com
   password: 123456
*/
INSERT INTO users(id, deleted, email, enabled, first_name, last_name, password, phone_number, role)
VALUES ('cd95284b-b6fe-46b4-ade2-491c28e55051', false, 'test@gmail.com', true, 'Testko', 'Testic',  '$2a$10$zMZOrOVf3fso50pcA/XVg.EgBiEnosehGy6aMt2UWlG/ZsDEx7NJy', '+381654587514', 'HOUSE_OWNER');

INSERT INTO address(id, address, city, country)
VALUES ('faef9bb2-ec14-11ec-8ea0-0242ac120002', 'Bulevar Oslobodjenja 1', 'Novi Sad', 'Serbia');

INSERT INTO loyalty(id, loyalty_points, loyalty_status)
VALUES ('33213a0e-ec15-11ec-8ea0-0242ac120002', 0, 0);

INSERT INTO business_client(id, date_of_birth, address_id, loyalty_id)
VALUES ('cd95284b-b6fe-46b4-ade2-491c28e55051', '12.06.1996.', 'faef9bb2-ec14-11ec-8ea0-0242ac120002', '33213a0e-ec15-11ec-8ea0-0242ac120002');

INSERT INTO vacation_home_owner(id)
VALUES ('cd95284b-b6fe-46b4-ade2-491c28e55051');

-- Vacation Home 1
-- INSERT INTO address(id, address, city, country)
-- VALUES ('f94acca8-ec16-11ec-8ea0-0242ac120002', 'Mrakovica b.b.', 'Prijedor', 'Bosnia and Herzegovina');
--
-- INSERT INTO rental_entity(id, additional_services, deleted, description, price, rules_of_conduct, title, address_id, owner_id)
-- VALUES ('698d9c52-ec17-11ec-8ea0-0242ac120002', 'wifi, jaccuzzi, free parking', false, 'Luksuzna vikendica u nacionalnom parku Mrakovica.', 100, 'Zabranjeno pusenje i ljubimci.', 'Luksuzna vikendica na Mrakovici', 'f94acca8-ec16-11ec-8ea0-0242ac120002', 'cd95284b-b6fe-46b4-ade2-491c28e55051');
--
-- INSERT INTO rental_entity_availability(rental_entity_id, availability_id)
-- VALUES ('698d9c52-ec17-11ec-8ea0-0242ac120002', 'ce8637a4-ec17-11ec-8ea0-0242ac120002');
--
-- INSERT INTO vacation_homes(id, beds, rooms)
-- VALUES ('698d9c52-ec17-11ec-8ea0-0242ac120002', 4, 3);
--
-- INSERT INTO vacation_home_owner_vacation_homes(vacation_home_owner_id, vacation_homes_id)
-- VALUES ('cd95284b-b6fe-46b4-ade2-491c28e55051', '698d9c52-ec17-11ec-8ea0-0242ac120002')

