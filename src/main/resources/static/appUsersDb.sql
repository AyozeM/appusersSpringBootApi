# creating database
drop database if exists appusers;
create database appusers;
use appusers;

#creating tables
create table user(
	id_user int not null auto_increment,
    name varchar(20),
    password varchar(200),
    email varchar(45),
    primary key(id_user)
);

create table role(
	id_role int not null auto_increment,
    name varchar(20),
    primary key (id_role)
);

create table privilege(
	id_privilege int not null auto_increment,
    name varchar(20),
    primary key(id_privilege)
);


create table role_user(
	id_user int not null,
    id_role int not null,
    primary key(id_user,id_role)
);

create table role_privilege(
	id_role int not null,
    id_privilege int not null,
    primary key(id_role,id_privilege)
);


#adding forigns keys

alter table role_user add constraint foreign key (id_user) references user(id_user);
alter table role_user add constraint foreign key (id_role) references role(id_role);

alter table role_privilege add constraint foreign key (id_role) references role(id_role);
alter table role_privilege add constraint foreign key (id_privilege) references privilege(id_privilege);

# insert dummy data
insert into user(name,email,password) values('suso','suso@gmail.com','$2a$10$XURPShQNCsLjp1ESc2laoObo9QZDhxz73hJPaEv7/cBha4pk0AgP.'),('mamel','mamel@gmail.com','$2a$10$XURPShQNCsLjp1ESc2laoObo9QZDhxz73hJPaEv7/cBha4pk0AgP.'),('pedro','pedro@gmail.com','$2a$10$XURPShQNCsLjp1ESc2laoObo9QZDhxz73hJPaEv7/cBha4pk0AgP.'),('chabela','chabela@gmail.com','$2a$10$XURPShQNCsLjp1ESc2laoObo9QZDhxz73hJPaEv7/cBha4pk0AgP.');

insert into role(name) values('usuario'),('encargado'),('administrador');

insert into privilege(name) values('ver'),('añadir'),('modificar');

insert into role_user(id_user,id_role) values(1,1),(2,2),(3,3);

insert into role_privilege(id_role,id_privilege) values (1,1),(2,2),(3,3);