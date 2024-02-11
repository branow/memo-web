create table if not exists module (
    module_id int primary key auto_increment,
    module_name varchar(100) not null,
    description varchar(500),
    access int not null,
    user int not null,
    constraint fk_module_access_type foreign key (access) references access_type(access_id)
      on update cascade,
    constraint fk_module_user foreign key (user) references user(user_id)
      on update cascade on delete cascade
);