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