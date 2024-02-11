create table if not exists user (
    user_id int primary key auto_increment,
    username varchar(50) not null unique,
    description varchar(500),
    email varchar(320) not null unique,
    password varchar(60) not null,
    enabled bit not null default 0
);