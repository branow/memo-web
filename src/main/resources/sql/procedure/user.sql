

delimiter &&
create procedure get_user_details_by_user_id(in p_user_id int)
begin
    select
        user_id as userId,
        username as username,
        description as description,
        email as email
    from user
    where user_id = p_user_id;
end &&


delimiter &&
create procedure get_user_general_details_by_user_id(in p_user_id int)
begin
    select
        user_id as userId,
        username as username,
        description as description
    from user
    where user_id = p_user_id;
end &&


delimiter &&
create procedure get_user_private_short_details_by_user_id(in p_user_id int)
begin
    select
        user_id as userId,
        username as username,
        email as email,
        enabled as enabled
    from user
    where user_id = p_user_id;
end &&


delimiter &&
create procedure get_user_id_by_module_id(in p_module_id int)
begin
    select m.user
        from module m
        where m.module_id = p_module_id;
end &&


delimiter &&
create procedure get_user_id_by_collection_id(in p_collection_id int)
begin
    select u.user_id
        from collection c
        join module m on m.module_id = c.module
        join user u on m.user = u.user_id
        where c.collection_id = p_collection_id;
end &&


delimiter &&
create procedure get_user_id_by_flashcard_id(in p_flashcard_id int)
begin
    select u.user_id
        from flashcard fc
        join collection c on c.collection_id = fc.collection
        join module m on m.module_id = c.module
        join user u on m.user = u.user_id
        where fc.flashcard_id = p_flashcard_id;
end &&


delimiter &&
create procedure get_user_id_by_text_id(in p_text_id int)
begin
    select u.user_id
        from formatted_text ft
        join flashcard fc on ft.text_id = fc.back_side or ft.text_id = fc.front_side
        join collection c on c.collection_id = fc.collection
        join module m on m.module_id = c.module
        join user u on m.user = u.user_id
        where ft.text_id = p_text_id;
end &&

