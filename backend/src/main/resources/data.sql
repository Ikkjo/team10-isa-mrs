-- ****** USER TEST DATA ****** START

-- ADMIN
-- mail/username: admin@rentr.com
-- password: admin
INSERT INTO users(id, deleted, email, enabled, first_name, last_name, password, phone_number, role)
VALUES ('4325ba8c-ad6a-4a2e-b661-ebfc667ab625', false, 'admin@rentr.com', true, 'Admin', 'Adminovic',  '$2a$12$aSdnp/ZQTnSDHWFuBZlybOIjhMWcY7GRNlZMHPYoQekbN3BaHM4AS', '', 'MAIN_ADMIN');
INSERT into admins(id) VALUES ('4325ba8c-ad6a-4a2e-b661-ebfc667ab625');

-- UNVERIFIED_ADMIN
-- mail/username: damir@rentr.com
-- password: admin
INSERT INTO users(id, deleted, email, enabled, first_name, last_name, password, phone_number, role)
VALUES ('6447c259-812c-4d33-afae-484bb00a5628', false, 'damir@rentr.com', true, 'Damir', 'Adamovic',  '$2a$12$aSdnp/ZQTnSDHWFuBZlybOIjhMWcY7GRNlZMHPYoQekbN3BaHM4AS', '', 'UNVERIFIED_ADMIN');
INSERT into admins(id) VALUES ('6447c259-812c-4d33-afae-484bb00a5628');

-- CLIENT
-- mail/username: nikola@mail.com
-- password: client
INSERT INTO users(id, deleted, email, enabled, first_name, last_name, password, phone_number, role)
VALUES ('f39ea854-da20-4995-89d8-33038a797bbb', false, 'nikola@mail.com', true, 'Nikola', 'Damjanović',  '$2a$12$YLS0DixoqqDLLyYhcqfJoe.ugEwUZOj11sAea4UPZq3P.mtzOlequ', '+38168223909', 'CLIENT');
INSERT into client(is_penalized, penalty_points, id)
VALUES (false, 0, 'f39ea854-da20-4995-89d8-33038a797bbb');

-- CLIENT
-- mail/username: ilija@mail.com
-- password: client
INSERT INTO users(id, deleted, email, enabled, first_name, last_name, password, phone_number, role)
VALUES ('d4bf14a8-bb24-4730-b313-7a29c8db26f8', false, 'ilija@mail.com', true, 'Ilija', 'Kalinić',  '$2a$12$YLS0DixoqqDLLyYhcqfJoe.ugEwUZOj11sAea4UPZq3P.mtzOlequ', '+38164998010', 'CLIENT');
INSERT into client(is_penalized, penalty_points, id)
VALUES (false, 0, 'd4bf14a8-bb24-4730-b313-7a29c8db26f8');

-- CLIENT
-- mail/username: vladan@mail.com
-- password: client
INSERT INTO users(id, deleted, email, enabled, first_name, last_name, password, phone_number, role)
VALUES ('02002c55-c3d1-4dda-9657-53049c08918d', false, 'vladan@mail.com', true, 'Vladan', 'Mikić',  '$2a$12$YLS0DixoqqDLLyYhcqfJoe.ugEwUZOj11sAea4UPZq3P.mtzOlequ', '+38164223878', 'CLIENT');
INSERT into client(is_penalized, penalty_points, id)
VALUES (false, 0, '02002c55-c3d1-4dda-9657-53049c08918d');

-- VACATION_HOUSE_OWNER
-- mail/username: test@gmail.com
-- password: 123456

INSERT INTO users(id, deleted, email, enabled, first_name, last_name, password, phone_number, role)
VALUES ('cd95284b-b6fe-46b4-ade2-491c28e55051', false, 'test@gmail.com', true, 'Testko', 'Testic',  '$2a$10$zMZOrOVf3fso50pcA/XVg.EgBiEnosehGy6aMt2UWlG/ZsDEx7NJy', '+381654587514', 'HOUSE_OWNER');

INSERT INTO address(id, address, city, country)
VALUES ('faef9bb2-ec14-11ec-8ea0-0242ac120002', 'Bulevar Oslobodjenja 1', 'Novi Sad', 'Serbia');

INSERT INTO loyalty(id, loyalty_points, loyalty_status)
VALUES ('33213a0e-ec15-11ec-8ea0-0242ac120002', 0, 0);

INSERT INTO business_client(id, date_of_birth, address_id, loyalty_id)
VALUES ('cd95284b-b6fe-46b4-ade2-491c28e55051', '12.06.1991.', 'faef9bb2-ec14-11ec-8ea0-0242ac120002', '33213a0e-ec15-11ec-8ea0-0242ac120002');

INSERT INTO vacation_home_owner(id)
VALUES ('cd95284b-b6fe-46b4-ade2-491c28e55051');

-- VACATION_HOUSE_OWNER
-- mail/username: houseowner@mail.com
-- password: houseowner
INSERT INTO users(id, deleted, email, enabled, first_name, last_name, password, phone_number, role)
VALUES ('b9ac9b1b-85fd-4fcc-9509-96a53a706539', false, 'houseowner@mail.com', true, 'Mirko', 'Zarich',  '$2a$12$RVK5NqR1PNPmq.fUPrqonu2ecBs4Sao8UZDSmtk.MgLFKf4u9P6JG', '+381644587514', 'HOUSE_OWNER');


INSERT INTO address(id, address, city, country)
VALUES ('ff6072d4-c3c9-4293-8ead-1e59275f86a8', 'Futoška ulica 1', 'Novi Sad', 'Serbia');

INSERT INTO loyalty(id, loyalty_points, loyalty_status)
VALUES ('f927b1ba-ed44-48f9-ad1b-305866cfb86f', 0, 0);

INSERT INTO business_client(id, date_of_birth, address_id, loyalty_id)
VALUES ('b9ac9b1b-85fd-4fcc-9509-96a53a706539', '12.06.1976.', 'ff6072d4-c3c9-4293-8ead-1e59275f86a8', 'f927b1ba-ed44-48f9-ad1b-305866cfb86f');

INSERT into vacation_home_owner(id)
VALUES ('b9ac9b1b-85fd-4fcc-9509-96a53a706539');

-- VACATION_HOUSE_OWNER
-- mail/username: ikkjohouses@mail.com
-- password: houseowner
INSERT INTO users(id, deleted, email, enabled, first_name, last_name, password, phone_number, role)
VALUES ('8ba04d80-3ff0-48b0-81b4-e432b7f19a60', false, 'ikkjohouses@mail.com', true, 'Ikkjo', 'Ikkjić',  '$2a$12$RVK5NqR1PNPmq.fUPrqonu2ecBs4Sao8UZDSmtk.MgLFKf4u9P6JG', '+38163994020', 'HOUSE_OWNER');

INSERT INTO address(id, address, city, country)
VALUES ('107f457f-d279-452f-8879-cbf661cfdc9f', 'Braće Ribnikar 1', 'Novi Sad', 'Serbia');

INSERT INTO loyalty(id, loyalty_points, loyalty_status)
VALUES ('b9326b5e-08d8-42d0-9ad6-92c7acd4d441', 0, 0);

INSERT INTO business_client(id, date_of_birth, address_id, loyalty_id)
VALUES ('8ba04d80-3ff0-48b0-81b4-e432b7f19a60', '12.06.1986.', '107f457f-d279-452f-8879-cbf661cfdc9f', 'b9326b5e-08d8-42d0-9ad6-92c7acd4d441');

INSERT into vacation_home_owner(id)
VALUES ('8ba04d80-3ff0-48b0-81b4-e432b7f19a60');

-- VACATION_HOUSE_OWNER
-- mail/username: teodor@mail.com
-- password: houseowner
INSERT INTO users(id, deleted, email, enabled, first_name, last_name, password, phone_number, role)
VALUES ('ae751645-27c5-4270-b069-27d958773080', false, 'teodor@mail.com', true, 'Teodor', 'F. Sakaw',  '$2a$12$RVK5NqR1PNPmq.fUPrqonu2ecBs4Sao8UZDSmtk.MgLFKf4u9P6JG', '+38160332090', 'HOUSE_OWNER');

INSERT INTO address(id, address, city, country)
VALUES ('65f2ecdd-374f-457f-a2c8-4df1af7e8fb9', 'Nikole Tesle 1', 'Novi Sad', 'Serbia');

INSERT INTO loyalty(id, loyalty_points, loyalty_status)
VALUES ('c0793ad4-84ba-41b5-8036-b6191a45c0f1', 0, 0);

INSERT INTO business_client(id, date_of_birth, address_id, loyalty_id)
VALUES ('ae751645-27c5-4270-b069-27d958773080', '12.06.1999.', '65f2ecdd-374f-457f-a2c8-4df1af7e8fb9', 'c0793ad4-84ba-41b5-8036-b6191a45c0f1');

INSERT into vacation_home_owner(id)
VALUES ('ae751645-27c5-4270-b069-27d958773080');

-- SHIP_OWNER
-- mail/username: jsmith@mail.com
-- password: shipowner
INSERT INTO users(id, deleted, email, enabled, first_name, last_name, password, phone_number, role)
VALUES ('30d93782-d08e-4d78-aa0c-f8df67a9826f', false, 'jsmith@mail.com', true, 'John', 'Smith',  '$2a$12$/gOtDu5uNP1g04zGRkk3dudMQP2Jd7vKUF6Ue5pYBnXzYDtFGfuhG', '+38163113221', 'SHIP_OWNER');

INSERT INTO address(id, address, city, country)
VALUES ('fc6849c2-f535-4f4a-9722-70d2d7222230', 'Bulevar Žrtava Racije 1', 'Novi Sad', 'Serbia');

INSERT INTO loyalty(id, loyalty_points, loyalty_status)
VALUES ('485f8d23-f4d2-4b63-a4b5-9f3ce78b26b2', 0, 0);

INSERT INTO business_client(id, date_of_birth, address_id, loyalty_id)
VALUES ('30d93782-d08e-4d78-aa0c-f8df67a9826f', '12.06.1992.', 'fc6849c2-f535-4f4a-9722-70d2d7222230', '485f8d23-f4d2-4b63-a4b5-9f3ce78b26b2');

INSERT into ship_owner(id)
VALUES ('30d93782-d08e-4d78-aa0c-f8df67a9826f');

-- SHIP_OWNER
-- mail/username: jdoe@mail.com
-- password: shipowner
INSERT INTO users(id, deleted, email, enabled, first_name, last_name, password, phone_number, role)
VALUES ('48c3d59a-dd95-4358-b758-79a7af29099c', false, 'jdoe@mail.com', true, 'Jane', 'Doe',  '$2a$12$/gOtDu5uNP1g04zGRkk3dudMQP2Jd7vKUF6Ue5pYBnXzYDtFGfuhG', '+38161334828', 'SHIP_OWNER');

INSERT INTO address(id, address, city, country)
VALUES ('522ee9fa-ccd8-4ecf-b8a8-1b68de67a88b', 'Ćirpanova 1', 'Novi Sad', 'Serbia');

INSERT INTO loyalty(id, loyalty_points, loyalty_status)
VALUES ('c509a80c-daef-4ca4-9b97-7a151f588cf1', 0, 0);

INSERT INTO business_client(id, date_of_birth, address_id, loyalty_id)
VALUES ('48c3d59a-dd95-4358-b758-79a7af29099c', '12.06.1989.', '522ee9fa-ccd8-4ecf-b8a8-1b68de67a88b', 'c509a80c-daef-4ca4-9b97-7a151f588cf1');

INSERT into ship_owner(id)
VALUES ('48c3d59a-dd95-4358-b758-79a7af29099c');

-- SHIP_OWNER
-- mail/username: demarco@mail.com
-- password: shipowner
INSERT INTO users(id, deleted, email, enabled, first_name, last_name, password, phone_number, role)
VALUES ('e754974d-6932-4d25-a14b-dded1c92e729', false, 'demarco@mail.com', true, 'Mak', 'Marković',  '$2a$12$/gOtDu5uNP1g04zGRkk3dudMQP2Jd7vKUF6Ue5pYBnXzYDtFGfuhG', '+38166455667', 'SHIP_OWNER');

INSERT INTO address(id, address, city, country)
VALUES ('8bfae442-176d-4097-96b2-2c0c9841702b', 'Novosadskog sajma 1', 'Novi Sad', 'Serbia');

INSERT INTO loyalty(id, loyalty_points, loyalty_status)
VALUES ('513555b4-0244-49e4-a3dd-663cc7cbec07', 0, 0);

INSERT INTO business_client(id, date_of_birth, address_id, loyalty_id)
VALUES ('e754974d-6932-4d25-a14b-dded1c92e729', '12.06.1988.', '8bfae442-176d-4097-96b2-2c0c9841702b', '513555b4-0244-49e4-a3dd-663cc7cbec07');

INSERT into ship_owner(id)
VALUES ('e754974d-6932-4d25-a14b-dded1c92e729');

-- FISHING_INSTRUCTOR
-- mail/username: borkofish@mail.com
-- password: fishinginstructor
INSERT INTO users(id, deleted, email, enabled, first_name, last_name, password, phone_number, role)
VALUES ('99523027-e4ad-40c6-ba7d-3848f0c2ee6a', false, 'borkofish@mail.com', true, 'Borivoje', 'Stantić',  '$2a$12$YfmPc3cMOG2.Fdndi1hwM.OOXEi8hkQG3KYvx7MBQlxvF9liHi16q', '+38161998290', 'FISHING_INSTRUCTOR');

INSERT INTO address(id, address, city, country)
VALUES ('dae6ea0e-6d42-4b58-8c96-7fb6dcd5a460', 'Braće Radića 1', 'Novi Sad', 'Serbia');

INSERT INTO loyalty(id, loyalty_points, loyalty_status)
VALUES ('2a947f20-c1c4-4afc-a9c8-9446af650779', 0, 0);

INSERT INTO business_client(id, date_of_birth, address_id, loyalty_id)
VALUES ('99523027-e4ad-40c6-ba7d-3848f0c2ee6a', '12.06.1985.', 'dae6ea0e-6d42-4b58-8c96-7fb6dcd5a460', '2a947f20-c1c4-4afc-a9c8-9446af650779');

INSERT into ship_owner(id)
VALUES ('99523027-e4ad-40c6-ba7d-3848f0c2ee6a');

-- FISHING_INSTRUCTOR
-- mail/username: ivanam@mail.com
-- password: fishinginstructor
INSERT INTO users(id, deleted, email, enabled, first_name, last_name, password, phone_number, role)
VALUES ('1a5c1107-eac5-4dbb-b679-7d7830a65ab8', false, 'ivanam@mail.com', true, 'Ivana', 'Marić',  '$2a$12$YfmPc3cMOG2.Fdndi1hwM.OOXEi8hkQG3KYvx7MBQlxvF9liHi16q', '+3138008548', 'FISHING_INSTRUCTOR');

INSERT INTO address(id, address, city, country)
VALUES ('135d59e7-63a2-4f65-b68a-02c7f8abec8a', 'Gogoljeva 1', 'Novi Sad', 'Serbia');

INSERT INTO loyalty(id, loyalty_points, loyalty_status)
VALUES ('66e8504b-9137-46ce-8053-47ee9632ac08', 0, 0);

INSERT INTO business_client(id, date_of_birth, address_id, loyalty_id)
VALUES ('1a5c1107-eac5-4dbb-b679-7d7830a65ab8', '12.06.1956.', '135d59e7-63a2-4f65-b68a-02c7f8abec8a', '66e8504b-9137-46ce-8053-47ee9632ac08');

INSERT into ship_owner(id)
VALUES ('1a5c1107-eac5-4dbb-b679-7d7830a65ab8');

-- FISHING_INSTRUCTOR
-- mail/username: manja@mail.com
-- password: fishinginstructor
INSERT INTO users(id, deleted, email, enabled, first_name, last_name, password, phone_number, role)
VALUES ('611619a9-394f-42b3-aad7-7fb10bf03c25', false, 'manja@mail.com', true, 'Marija', 'Ivković',  '$2a$12$YfmPc3cMOG2.Fdndi1hwM.OOXEi8hkQG3KYvx7MBQlxvF9liHi16q', '+2815150020', 'FISHING_INSTRUCTOR');

INSERT INTO address(id, address, city, country)
VALUES ('84acb707-1b0f-4dd2-8fec-2b5333cc88de', 'Puškinova 1', 'Novi Sad', 'Serbia');

INSERT INTO loyalty(id, loyalty_points, loyalty_status)
VALUES ('bd213521-8edb-418b-974e-ce764797e0c8', 0, 0);

INSERT INTO business_client(id, date_of_birth, address_id, loyalty_id)
VALUES ('611619a9-394f-42b3-aad7-7fb10bf03c25', '12.06.1956.', '84acb707-1b0f-4dd2-8fec-2b5333cc88de', 'bd213521-8edb-418b-974e-ce764797e0c8');

INSERT into ship_owner(id)
VALUES ('611619a9-394f-42b3-aad7-7fb10bf03c25');

-- ****** USER TEST DATA ****** END

-- ****** VACATION HOMES TEST DATA ****** START

-- Vacation Home 1
-- Owner: test@gmail.com, Testko Testic
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

