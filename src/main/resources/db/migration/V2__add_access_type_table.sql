create table if not exists access_type (
   access_id int primary key auto_increment,
   access varchar(50) not null unique
);