create table if not exists collection (
    collection_id int primary key auto_increment,
    collection_name varchar(100) not null,
    module int not null,
    constraint fk_collection_module foreign key (module) references module(module_id)
      on update cascade on delete cascade
);