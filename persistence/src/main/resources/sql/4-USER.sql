drop table if exists users;
drop table if exists authorities;
create table users(
	username varchar(50) not null primary key,
	password varchar(100) not null,
	enabled boolean not null
);
create table authorities (
	username varchar(50) not null,
	authority varchar(50) not null,
	constraint fk_authorities_users foreign key(username) references users(username)
);
create unique index ix_auth_username on authorities (username,authority);

/*admin@123*/
insert into users(username,password,enabled)
	values('admin','$2a$10$hbxecwitQQ.dDT4JOFzQAulNySFwEpaFLw38jda6Td.Y/cOiRzDFu',true);
insert into authorities(username,authority) 
	values('admin','ROLE_ADMIN');
/*bbbb5555*/
insert into users(username,password,enabled)
	values('visitor','$2a$10$WqFZNEOf8oNXINzM7MvtmuAHhk46plHdzIF1T2sqgAAXX5Su.rFdS',true);
insert into authorities(username,authority) 
	values('visitor','ROLE_VISITOR');

