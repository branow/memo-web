

delimiter &&
create procedure get_module_id_with_public_access_all_by_user_id(in p_user_id int)
begin
    select m.module_id
    from module m
             join access_type a
                  on a.access_id = m.access
    where m.user = p_user_id and
            a.access = 'public';
end &&


delimiter &&
create procedure get_module_short_details_by_module_id(in p_module_id int)
begin
    select m.module_id as moduleId,
           m.module_name as moduleName,
           a.access as access,
           substr(m.description, 1, 100) as shortDescription
    from module m
             join access_type a on a.access_id = m.access
    where m.module_id = p_module_id;
end &&


delimiter &&
create procedure get_module_details_by_module_id(in p_module_id int)
begin
    select m.module_id as moduleId,
           m.module_name as moduleName,
           a.access as access,
           m.description as shortDescription
    from module m
             join access_type a on a.access_id = m.access
    where m.module_id = p_module_id;
end &&


delimiter &&
create procedure get_module_short_details_by_collection_id(in p_collection_id int)
begin
    declare v_module_id int;
    select c.module into v_module_id from collection c where c.collection_id = p_collection_id;
    call get_module_short_details_by_module_id(v_module_id);
end &&


delimiter &&
create procedure get_module_short_details_by_flashcard_id(in p_flashcard_id int)
begin
    declare v_collection_id int;
    select collection into v_collection_id from flashcard where flashcard_id = p_flashcard_id;
    call get_module_short_details_by_collection_id(v_collection_id);
end &&