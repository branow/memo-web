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