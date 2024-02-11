delimiter &&
create procedure get_user_public_short_details_by_module_id(in p_module_id int)
begin
    select u.user_id as userId,
           u.username as username,
           substr(u.description, 1, 100) as shortDescription
    from module m
             join user u on m.user = u.user_id
    where module_id = p_module_id;
end &&