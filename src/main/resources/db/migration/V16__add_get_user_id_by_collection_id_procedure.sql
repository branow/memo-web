delimiter &&
create procedure get_user_id_by_collection_id(in p_collection_id int)
begin
    select u.user_id
    from collection c
             join module m on m.module_id = c.module
             join user u on m.user = u.user_id
    where c.collection_id = p_collection_id;
end &&