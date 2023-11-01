

delimiter $$
create function count_cards_in_collection(p_collection_id int)
returns int
deterministic
begin
    declare v_count int;
    select count(flashcard_id)
        into v_count
        from flashcard
        where collection = p_collection_id;
    return v_count;
end $$;

delimiter $$
create function count_cards_in_module(p_module_id int)
returns int
deterministic
begin
    declare v_count int;
    select sum(count_cards_in_collection(collection_id))
        into v_count
        from collection
        where module = p_module_id;
    return v_count;
end $$;

delimiter $$
create function count_cards_of_user(p_user_id int)
    returns int
    deterministic
begin
    declare v_count int;
    select sum(count_cards_in_module(module_id))
        into v_count
        from module
        where user = p_user_id;
    return v_count;
end $$;


delimiter $$
create function count_collections_in_module(p_module_id int)
    returns int
    deterministic
begin
    declare v_count int;
    select count(collection_id)
        into v_count
        from collection
        where module = p_module_id;
        return v_count;
end $$;


delimiter $$
create function count_collections_of_user(p_user_id int)
    returns int
    deterministic
begin
    declare v_count int;
    select sum(count_collections_in_module(module_id))
        into v_count
        from module
        where user = p_user_id;
    return v_count;
end $$;


delimiter $$
create function count_modules_of_user(p_user_id int)
    returns int
    deterministic
begin
    declare v_count int;
    select count(module_id)
        into v_count
        from module
        where user = p_user_id;
    return v_count;
end $$;






