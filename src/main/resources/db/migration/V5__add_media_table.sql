create table if not exists media (
    media_id int primary key auto_increment,
    media mediumblob not null,
    format varchar(20) not null,
    hash char(32) as (md5(concat(format, media))) not null unique
);