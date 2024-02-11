create table if not exists formatted_text(
    text_id int primary key auto_increment,
    text varchar(1000) not null,
    format varchar(500),
    audio int,
    image int,
    constraint fk_formatted_text_media_audio foreign key (audio) references media(media_id)
        on update cascade,
    constraint fk_formatted_text_media_image foreign key (image) references media(media_id)
        on update cascade
);