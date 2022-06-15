-- OLD
INSERT INTO users(id, deleted, email, enabled, first_name, last_name, password, phone_number, role)
VALUES ('cd95284b-b6fe-46b4-ade2-491c28e55050', false, 'admin@rentr.com', true, 'Admin', 'Adminovic',  '$2a$12$aSdnp/ZQTnSDHWFuBZlybOIjhMWcY7GRNlZMHPYoQekbN3BaHM4AS', '', 'ADMIN');
INSERT into admins(initial_password_changed, main, id) VALUES (true, true, 'cd95284b-b6fe-46b4-ade2-491c28e55050');

-- ****** USER TEST DATA ****** START

-- -- ADMIN
-- -- mail/username: admin@rentr.com
-- -- password: admin
-- INSERT INTO users(id, deleted, email, enabled, first_name, last_name, password, phone_number, role)
-- VALUES ('cd95284b-b6fe-46b4-ade2-491c28e55050', false, 'admin@rentr.com', true, 'Admin', 'Adminovic',  '$2a$12$aSdnp/ZQTnSDHWFuBZlybOIjhMWcY7GRNlZMHPYoQekbN3BaHM4AS', '', 'MAIN_ADMIN');
-- INSERT into admins(id) VALUES ('cd95284b-b6fe-46b4-ade2-491c28e55050');
--
-- -- UNVERIFIED_ADMIN
-- -- mail/username: damir@rentr.com
-- -- password: admin
-- INSERT INTO users(id, deleted, email, enabled, first_name, last_name, password, phone_number, role)
-- VALUES ('cd95284b-b6fe-46b4-ade2-491c28e55051', false, 'damir@rentr.com', true, 'Damir', 'Adamovic',  '$2a$12$aSdnp/ZQTnSDHWFuBZlybOIjhMWcY7GRNlZMHPYoQekbN3BaHM4AS', '', 'UNVERIFIED_ADMIN');
-- INSERT into admins(id) VALUES ('cd95284b-b6fe-46b4-ade2-491c28e55051');

-- CLIENT
-- mail/username: nikola@mail.com
-- password: client
INSERT INTO users(id, deleted, email, enabled, first_name, last_name, password, phone_number, role)
VALUES ('f39ea854-da20-4995-89d8-33038a797bbb', false, 'nikola@mail.com', true, 'Nikola', 'Damjanović',  '$2a$12$YLS0DixoqqDLLyYhcqfJoe.ugEwUZOj11sAea4UPZq3P.mtzOlequ', '', 'CLIENT');
INSERT into client(id) VALUES ('f39ea854-da20-4995-89d8-33038a797bbb');

-- CLIENT
-- mail/username: ilija@mail.com
-- password: client
INSERT INTO users(id, deleted, email, enabled, first_name, last_name, password, phone_number, role)
VALUES ('d4bf14a8-bb24-4730-b313-7a29c8db26f8', false, 'ilija@mail.com', true, 'Ilija', 'Kalinić',  '$2a$12$YLS0DixoqqDLLyYhcqfJoe.ugEwUZOj11sAea4UPZq3P.mtzOlequ', '', 'CLIENT');
INSERT into client(id) VALUES ('d4bf14a8-bb24-4730-b313-7a29c8db26f8');

-- CLIENT
-- mail/username: vladan@mail.com
-- password: client
INSERT INTO users(id, deleted, email, enabled, first_name, last_name, password, phone_number, role)
VALUES ('02002c55-c3d1-4dda-9657-53049c08918d', false, 'vladan@mail.com', true, 'Vladan', 'Mikić',  '$2a$12$YLS0DixoqqDLLyYhcqfJoe.ugEwUZOj11sAea4UPZq3P.mtzOlequ', '', 'CLIENT');
INSERT into client(id) VALUES ('02002c55-c3d1-4dda-9657-53049c08918d');

-- VACATION_HOUSE_OWNER
-- mail/username: houseowner@mail.com
-- password: houseowner
INSERT INTO users(id, deleted, email, enabled, first_name, last_name, password, phone_number, role)
VALUES ('b9ac9b1b-85fd-4fcc-9509-96a53a706539', false, 'houseowner@mail.com', true, 'Mirko', 'Zarich',  '$2a$12$RVK5NqR1PNPmq.fUPrqonu2ecBs4Sao8UZDSmtk.MgLFKf4u9P6JG', '', 'HOUSE_OWNER');
INSERT into vacation_home_owner(id) VALUES ('b9ac9b1b-85fd-4fcc-9509-96a53a706539');

-- VACATION_HOUSE_OWNER
-- mail/username: ikkjohouses@mail.com
-- password: houseowner
INSERT INTO users(id, deleted, email, enabled, first_name, last_name, password, phone_number, role)
VALUES ('8ba04d80-3ff0-48b0-81b4-e432b7f19a60', false, 'ikkjohouses@mail.com', true, 'Ikkjo', 'Ikkjić',  '$2a$12$RVK5NqR1PNPmq.fUPrqonu2ecBs4Sao8UZDSmtk.MgLFKf4u9P6JG', '', 'HOUSE_OWNER');
INSERT into vacation_home_owner(id) VALUES ('8ba04d80-3ff0-48b0-81b4-e432b7f19a60');

-- VACATION_HOUSE_OWNER
-- mail/username: teodor@mail.com
-- password: houseowner
INSERT INTO users(id, deleted, email, enabled, first_name, last_name, password, phone_number, role)
VALUES ('ae751645-27c5-4270-b069-27d958773080', false, 'teodor@mail.com', true, 'Teodor', 'F. Sakaw',  '$2a$12$RVK5NqR1PNPmq.fUPrqonu2ecBs4Sao8UZDSmtk.MgLFKf4u9P6JG', '', 'HOUSE_OWNER');
INSERT into vacation_home_owner(id) VALUES ('ae751645-27c5-4270-b069-27d958773080');

-- SHIP_OWNER
-- mail/username: jsmith@mail.com
-- password: shipowner
INSERT INTO users(id, deleted, email, enabled, first_name, last_name, password, phone_number, role)
VALUES ('b9ac9b1b-85fd-4fcc-9509-96a53a706539', false, 'jsmith@mail.com', true, 'John', 'Smith',  '$2a$12$/gOtDu5uNP1g04zGRkk3dudMQP2Jd7vKUF6Ue5pYBnXzYDtFGfuhG', '', 'SHIP_OWNER');
INSERT into ship_owner(id) VALUES ('b9ac9b1b-85fd-4fcc-9509-96a53a706539');

-- SHIP_OWNER
-- mail/username: jdoe@mail.com
-- password: shipowner
INSERT INTO users(id, deleted, email, enabled, first_name, last_name, password, phone_number, role)
VALUES ('48c3d59a-dd95-4358-b758-79a7af29099c', false, 'jdoe@mail.com', true, 'Jane', 'Doe',  '$2a$12$/gOtDu5uNP1g04zGRkk3dudMQP2Jd7vKUF6Ue5pYBnXzYDtFGfuhG', '', 'SHIP_OWNER');
INSERT into ship_owner(id) VALUES ('48c3d59a-dd95-4358-b758-79a7af29099c');

-- SHIP_OWNER
-- mail/username: demarco@mail.com
-- password: shipowner
INSERT INTO users(id, deleted, email, enabled, first_name, last_name, password, phone_number, role)
VALUES ('e754974d-6932-4d25-a14b-dded1c92e729', false, 'demarco@mail.com', true, 'Mak', 'Marković',  '$2a$12$/gOtDu5uNP1g04zGRkk3dudMQP2Jd7vKUF6Ue5pYBnXzYDtFGfuhG', '', 'SHIP_OWNER');
INSERT into ship_owner(id) VALUES ('e754974d-6932-4d25-a14b-dded1c92e729');

-- FISHING_INSTRUCTOR
-- mail/username: borkofish@mail.com
-- password: fishinginstructor
INSERT INTO users(id, deleted, email, enabled, first_name, last_name, password, phone_number, role)
VALUES ('99523027-e4ad-40c6-ba7d-3848f0c2ee6a', false, 'borkofish@mail.com', true, 'Borivoje', 'Stantić',  '$2a$12$YfmPc3cMOG2.Fdndi1hwM.OOXEi8hkQG3KYvx7MBQlxvF9liHi16q', '', 'FISHING_INSTRUCTOR');
INSERT into ship_owner(id) VALUES ('99523027-e4ad-40c6-ba7d-3848f0c2ee6a');

-- FISHING_INSTRUCTOR
-- mail/username: ivanam@mail.com
-- password: fishinginstructor
INSERT INTO users(id, deleted, email, enabled, first_name, last_name, password, phone_number, role)
VALUES ('1a5c1107-eac5-4dbb-b679-7d7830a65ab8', false, 'ivanam@mail.com', true, 'Ivana', 'Marić',  '$2a$12$YfmPc3cMOG2.Fdndi1hwM.OOXEi8hkQG3KYvx7MBQlxvF9liHi16q', '', 'FISHING_INSTRUCTOR');
INSERT into ship_owner(id) VALUES ('1a5c1107-eac5-4dbb-b679-7d7830a65ab8');

-- FISHING_INSTRUCTOR
-- mail/username: manja@mail.com
-- password: fishinginstructor
INSERT INTO users(id, deleted, email, enabled, first_name, last_name, password, phone_number, role)
VALUES ('611619a9-394f-42b3-aad7-7fb10bf03c25', false, 'manja@mail.com', true, 'Marija', 'Ivković',  '$2a$12$YfmPc3cMOG2.Fdndi1hwM.OOXEi8hkQG3KYvx7MBQlxvF9liHi16q', '', 'FISHING_INSTRUCTOR');
INSERT into ship_owner(id) VALUES ('611619a9-394f-42b3-aad7-7fb10bf03c25');

-- ****** USER TEST DATA ****** END
