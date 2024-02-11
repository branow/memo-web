delimiter &&
create procedure get_user_public_short_details_by_collection_id(in p_collection_id int)
begin
    select
        u.user_id as userId,
        u.username as username,
        substr(u.description, 1, 100) as shortDescription
    from collection c
             join module m on c.module = m.module_id
             join user u on m.user = u.user_id
    where collection_id = p_collection_id;
end &&