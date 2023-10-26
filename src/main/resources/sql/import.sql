
load data local infile 'src/main/resources/sql/data/user.csv'
    into table user
    fields terminated by ';'
    enclosed by '"'
    lines terminated by '\r\n'
    ignore 1 lines
    (user_id,	username,	@description,	email,	password,	@enabled) set
        description = if(@description = '', null, @description),
        enabled = if(@enabled = '1', 1, 0);


load data local infile 'src/main/resources/sql/data/verification_token.csv'
    into table verification_token
    fields terminated by ';'
    enclosed by '"'
    lines terminated by '\r\n'
    ignore 1 lines
    (user_id, expiration, token);


load data local infile 'src/main/resources/sql/data/access_type.csv'
    into table access_type
    fields terminated by ';'
    enclosed by '"'
    lines terminated by '\r\n'
    ignore 1 lines
    (access_id, access);

load data local infile 'src/main/resources/sql/data/module.csv'
    into table module
    fields terminated by ';'
    enclosed by '"'
    lines terminated by '\r\n'
    ignore 1 lines
    (module_id, module_name, @description, access, user)
    set description = if(@description = '', null, @description);

load data local infile 'src/main/resources/sql/data/collection.csv'
    into table collection
    fields terminated by ';'
    enclosed by '"'
    lines terminated by '\r\n'
    ignore 1 lines
    (collection_id, collection_name, module);


insert into media (media_id, media)
    values (1, load_file('D:/java-project/memo-web/src/main/resources/sql/data/media/1.jpg')),
           (2, load_file('D:/java-project/memo-web/src/main/resources/sql/data/media/2.jpg')),
           (3, load_file('D:/java-project/memo-web/src/main/resources/sql/data/media/3.jpg')),
           (4, load_file('D:/java-project/memo-web/src/main/resources/sql/data/media/4.jpg')),
           (5, load_file('D:/java-project/memo-web/src/main/resources/sql/data/media/5.mp3')),
           (6, load_file('D:/java-project/memo-web/src/main/resources/sql/data/media/6.mp3'));


load data local infile 'src/main/resources/sql/data/formatted_text.csv'
    into table formatted_text
    fields terminated by ';'
    optionally enclosed by '"'
    lines terminated by '\r\n'
    ignore 1 lines
    (text_id, text, @format, @audio, @image) set
            format = if(@format = '', null, @format),
            audio = if(@audio = '', null, @audio),
            image = if(@image = '', null, @image);


load data local infile 'src/main/resources/sql/data/flashcard.csv'
    into table flashcard
    fields terminated by ';'
    optionally enclosed by '"'
    lines terminated by '\r\n'
    ignore 1 lines
    (flashcard_id, front_side, back_side, collection);


load data local infile 'src/main/resources/sql/data/study_type.csv'
    into table study_type
    fields terminated by ';'
    optionally enclosed by '"'
    lines terminated by '\r\n'
    ignore 1 lines
    (study_id, study_name);


load data local infile 'src/main/resources/sql/data/score.csv'
    into table score
    fields terminated by ';'
    optionally enclosed by '"'
    lines terminated by '\r\n'
    ignore 1 lines
    (flashcard, study_type, score, study_time, reset_time, study_repetition);
