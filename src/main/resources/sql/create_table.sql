
create table if not exists user (
    user_id int primary key auto_increment,
    username varchar(50) not null unique,
    description varchar(300),
    email varchar(320) not null unique,
    password varchar(60) not null,
    enabled bit not null default 0
);

create table if not exists verification_token (
    user_id int primary key,
    expiration datetime not null,
    token varchar(36) not null,
    constraint fk_verification_token_user foreign key (user_id) references user(user_id)
        on update cascade on delete cascade
);

create table if not exists access_type (
    access_id int primary key auto_increment,
    access varchar(50) not null unique
);

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

create table if not exists collection (
    collection_id int primary key auto_increment,
    collection_name varchar(100) not null,
    module int not null,
    constraint fk_collection_module foreign key (module) references module(module_id)
        on update cascade on delete cascade
);

create table if not exists media (
    media_id int primary key auto_increment,
    media mediumblob not null,
    format varchar(20) not null,
    hash char(32) as (md5(concat(format, media))) not null unique
);

create table if not exists formatted_text(
    text_id int primary key auto_increment,
    text varchar(1000) not null,
    format varchar(2000),
    audio int,
    image int,
    constraint fk_formatted_text_media_audio foreign key (audio) references media(media_id)
        on update cascade,
    constraint fk_formatted_text_media_image foreign key (image) references media(media_id)
        on update cascade
);

create table if not exists flashcard(
    flashcard_id int primary key auto_increment,
    front_side int not null unique,
    back_side int not null unique,
    collection int not null,
    constraint fk_flashcard_formatted_text_front foreign key (front_side) references formatted_text(text_id)
        on update cascade,
    constraint fk_flashcard_formatted_text_back foreign key (back_side) references formatted_text(text_id)
        on update cascade,
    constraint fk_flashcard_collection foreign key (collection) references collection(collection_id)
        on update cascade on delete cascade
);

create table if not exists study_type(
    study_id int primary key auto_increment,
    study_name varchar(100)
);

create table if not exists score(
    flashcard int,
    study_type int,
    score tinyint not null check (score >= 0 and score <= 100),
    study_time datetime not null default now(),
    reset_time datetime not null,
    study_repetition int not null,
    primary key (flashcard, study_type),
    constraint fk_score_study_type foreign key (study_type) references study_type(study_id)
        on update cascade,
    constraint fk_score_flashcard foreign key (flashcard) references flashcard(flashcard_id)
        on update cascade on delete cascade
);




