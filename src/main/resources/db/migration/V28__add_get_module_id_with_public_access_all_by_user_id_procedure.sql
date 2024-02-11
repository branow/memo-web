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