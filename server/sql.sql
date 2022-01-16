create table t_log
(
	id INT auto_increment,
	level TEXT not null,
	thread_name TEXT not null,
	content TEXT not null,

	time DATETIME not null,
	constraint t_log_pk
		primary key (id)
);