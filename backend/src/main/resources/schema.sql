create table client_reservations
(
	client_id bigint not null,
	reservations_id bigint not null
		constraint uk_381ursei1q0n5h1wktrwf4b78
			unique,
	constraint client_reservations_pkey
		primary key (client_id, reservations_id)
);

alter table client_reservations owner to postgres;

create table client_confirmation_token
(
	id bigint not null
		constraint client_confirmation_token_pkey
			primary key,
	confirmed_at timestamp,
	created_at timestamp not null,
	expired_at timestamp not null,
	token varchar(255) not null,
	client_id bigint not null
);

alter table client_confirmation_token owner to postgres;

create table vacation_home_owner_homes
(
	vacation_home_owner_id uuid not null,
	vacation_home_id uuid not null
		constraint uk_mybcvuc55x39wxpsb34cuyx3h
			unique,
	constraint vacation_home_owner_homes_pkey
		primary key (vacation_home_owner_id, vacation_home_id)
);

alter table vacation_home_owner_homes owner to postgres;

create table addresses
(
	id uuid not null
		constraint addresses_pkey
			primary key,
	address varchar(255) not null
		constraint uk_3ficn3siays553iojee050140
			unique,
	city varchar(255) not null,
	country varchar(255) not null
);

alter table addresses owner to postgres;

create table address
(
	id uuid not null
		constraint address_pkey
			primary key,
	address varchar(255) not null
		constraint uk_h8jov0p3cffixl6yhdy4hb2gi
			unique,
	city varchar(255) not null,
	country varchar(255) not null
);

alter table address owner to postgres;

create table availability
(
	id uuid not null
		constraint availability_pkey
			primary key,
	date bigint not null
);

alter table availability owner to postgres;

create table loyalty
(
	id uuid not null
		constraint loyalty_pkey
			primary key,
	loyalty_points integer not null,
	loyalty_status integer
);

alter table loyalty owner to postgres;

create table users
(
	id uuid not null
		constraint users_pkey
			primary key,
	deleted boolean,
	email text not null,
	enabled boolean,
	first_name text not null,
	last_name text not null,
	password text not null,
	phone_number text,
	role varchar(255)
);

alter table users owner to postgres;

create table admins
(
	id uuid not null
		constraint admins_pkey
			primary key
		constraint fkanhsicqm3lc8ya77tr7r0je18
			references users
);

alter table admins owner to postgres;

create table business_client
(
	date_of_birth varchar(255) not null,
	id uuid not null
		constraint business_client_pkey
			primary key
		constraint fksfbublfb7hofmm6ecyicafyol
			references users,
	address_id uuid
		constraint fke2wtwuknt8qk2dqniq7e9djhg
			references address,
	loyalty_id uuid
		constraint fk7hsr23bmvirlatpstkpg30gan
			references loyalty
);

alter table business_client owner to postgres;

create table client
(
	is_penalized boolean,
	penalty_points integer,
	id uuid not null
		constraint client_pkey
			primary key
		constraint fk70dfjxvqnmgixqht3vea50voj
			references users
);

alter table client owner to postgres;

create table action
(
	id uuid not null
		constraint action_pkey
			primary key,
	end_date bigint not null,
	price integer not null,
	start_date bigint not null,
	status integer,
	client_id uuid
		constraint fk_kn4umpjg31moua8ds93tdw5ea
			references client,
	expires_on bigint not null,
	max_persons integer not null
);

alter table action owner to postgres;

create table confirmation_token
(
	id bigint not null
		constraint confirmation_token_pkey
			primary key,
	confirmed_at timestamp,
	created_at timestamp not null,
	expires_at timestamp not null,
	token varchar(255) not null,
	user_id uuid not null
		constraint fkah4p1rycwibwm6s9bsyeckq51
			references users
);

alter table confirmation_token owner to postgres;

create table deletion_request
(
	id uuid not null
		constraint deletion_request_pkey
			primary key,
	deletion_reason varchar(255) not null,
	reviewed boolean not null,
	user_id uuid
		constraint fk5q20bhm8b96drceocht7p3q3b
			references users
);

alter table deletion_request owner to postgres;

create table fishing_instructor
(
	id uuid not null
		constraint fishing_instructor_pkey
			primary key
		constraint fk6p5lusrqo9sopopi4p0kfg38r
			references business_client
);

alter table fishing_instructor owner to postgres;

create table registration_request
(
	id uuid not null
		constraint registration_request_pkey
			primary key,
	description varchar(255) not null,
	reviewed boolean not null,
	business_client_id uuid
		constraint fkivr3wps9hs4vrwkxqiw2v6yuf
			references business_client
);

alter table registration_request owner to postgres;

create table rental_entity
(
	id uuid not null
		constraint rental_entity_pkey
			primary key,
	additional_services varchar(200) not null,
	deleted boolean not null,
	description varchar(500) not null,
	price integer not null,
	rules_of_conduct varchar(200) not null,
	title varchar(255) not null,
	address_id uuid
		constraint fkisl8x39hufr4gs3ybmhmmsi2l
			references address,
	owner_id uuid not null
		constraint fk2rpw5ui6egcjmu817gcxyhjy8
			references business_client
);

alter table rental_entity owner to postgres;

create table adventure
(
	fishing varchar(255) not null,
	free_cancellation boolean not null,
	instructor_biography varchar(255),
	max_capacity integer not null,
	id uuid not null
		constraint adventure_pkey
			primary key
		constraint fk5rve92dlu56rfq1yxrkofu8w3
			references rental_entity
);

alter table adventure owner to postgres;

create table fishing_instructor_adventures
(
	fishing_instructor_id uuid not null
		constraint fk1jnlnnvk1p46p7vhb7efa1w26
			references fishing_instructor,
	adventures_id uuid not null
		constraint uk_3kd108o0ql17dnojyjnjuk11o
			unique
		constraint fk9ijuledqqb6bgww1meb4ofy0p
			references adventure,
	constraint fishing_instructor_adventures_pkey
		primary key (fishing_instructor_id, adventures_id)
);

alter table fishing_instructor_adventures owner to postgres;

create table picture
(
	id uuid not null
		constraint picture_pkey
			primary key,
	pic_byte bytea,
	type varchar(255),
	picture_id uuid
		constraint fkr7i2ioh7a4o9edl81rtj82nao
			references rental_entity
);

alter table picture owner to postgres;

create table rental_entity_actions
(
	rental_entity_id uuid not null
		constraint fk20c70xf1dvwbnak2op72uqxpf
			references rental_entity,
	actions_id uuid not null
		constraint uk_ailbqs4hdydestrqcuqd6ngym
			unique
		constraint fk4q44lome71f9rww3j71wbm3h7
			references action,
	constraint rental_entity_actions_pkey
		primary key (rental_entity_id, actions_id)
);

alter table rental_entity_actions owner to postgres;

create table rental_entity_availability
(
	rental_entity_id uuid not null
		constraint fkcf2pebqimpntw5e9e3mb8oqi2
			references rental_entity,
	availability_id uuid not null
		constraint fk5hem8mi5e98f19nmvxvsj34xn
			references availability,
	constraint rental_entity_availability_pkey
		primary key (rental_entity_id, availability_id)
);

alter table rental_entity_availability owner to postgres;

create table rental_entity_reservations
(
	rental_entity_id uuid not null
		constraint fk4hafssjra2i1qjoaq8501fh5v
			references rental_entity,
	reservations_id uuid not null
		constraint uk_6o9oamuubor0qd97npapu0j6q
			unique,
	constraint rental_entity_reservations_pkey
		primary key (rental_entity_id, reservations_id)
);

alter table rental_entity_reservations owner to postgres;

create table reservation
(
	id uuid not null
		constraint reservation_pkey
			primary key,
	end_date bigint not null,
	price integer not null,
	start_date bigint not null,
	status integer,
	client_id uuid
		constraint fkoewar6f18rkn4iptr6da4oysv
			references client
);

alter table reservation owner to postgres;

create table review
(
	id uuid not null
		constraint review_pkey
			primary key,
	answered_at date,
	approved_at date,
	created_at date,
	review varchar(255),
	status integer,
	client_id uuid
		constraint fkhr2oxqr7hu3upmi4hvg9g0ghp
			references client
);

alter table review owner to postgres;

create table rating
(
	id uuid not null
		constraint rating_pkey
			primary key,
	service_rating double precision,
	user_rating double precision,
	review_id uuid
		constraint fkaxj83nexctlr90q1hesd3fbt8
			references review
);

alter table rating owner to postgres;

create table ship_owner
(
	id uuid not null
		constraint ship_owner_pkey
			primary key
		constraint fkbehgd4qqocedu4r3py5p5cb5t
			references business_client
);

alter table ship_owner owner to postgres;

create table ships
(
	capacity integer not null,
	engine_count integer not null,
	engine_power integer not null,
	fishing_equipment varchar(500) not null,
	free_cancellation boolean not null,
	length double precision not null,
	max_speed integer not null,
	navigation_equipment varchar(500) not null,
	type varchar(255) not null,
	id uuid not null
		constraint ships_pkey
			primary key
		constraint fkhtrm6x715kn06xih78ai0lpw7
			references rental_entity
);

alter table ships owner to postgres;

create table ship_owner_ships
(
	ship_owner_id uuid not null
		constraint fk87dxylxpguum9oyf0lmdne41c
			references ship_owner,
	ship_id uuid not null
		constraint uk_3clv156pmmaph8wodxyy96jel
			unique
		constraint fkaglj8ss6tax5gfc21vvynf1d2
			references ships,
	constraint ship_owner_ships_pkey
		primary key (ship_owner_id, ship_id)
);

alter table ship_owner_ships owner to postgres;

create table vacation_homes
(
	beds integer not null,
	rooms integer not null,
	id uuid not null
		constraint vacation_homes_pkey
			primary key
		constraint fkekgujvfdlpfts4eo6q7a6cyx8
			references rental_entity
);

alter table vacation_homes owner to postgres;

create table vacation_home_owner
(
	id uuid not null
		constraint vacation_home_owner_pkey
			primary key
		constraint fkiwk2uitysylkn4m4iadrxidae
			references business_client
);

alter table vacation_home_owner owner to postgres;

create table vacation_home_owner_vacation_homes
(
	vacation_home_owner_id uuid not null
		constraint fkdv0grr5kamk3w2lhiv48glo9l
			references vacation_home_owner,
	vacation_homes_id uuid not null
		constraint uk_q9kn8kyseb1g9q0xywegoknf4
			unique
		constraint fkl99irv2h1iutff5rr7ytsmeu2
			references vacation_homes,
	constraint vacation_home_owner_vacation_homes_pkey
		primary key (vacation_home_owner_id, vacation_homes_id)
);

alter table vacation_home_owner_vacation_homes owner to postgres;

create sequence user_id_seq;

alter sequence user_id_seq owner to postgres;

create sequence client_token_sequence;

alter sequence client_token_sequence owner to postgres;

create sequence loyalty_sequence;

alter sequence loyalty_sequence owner to postgres;

create sequence reservation_sequence;

alter sequence reservation_sequence owner to postgres;

create sequence user_sequence;

alter sequence user_sequence owner to postgres;

create sequence confirmation_token_sequence;

alter sequence confirmation_token_sequence owner to postgres;
