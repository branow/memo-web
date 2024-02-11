delimiter &&
create procedure get_user_id_by_module_id(in p_module_id int)
begin
    select m.user
    from module m
    where m.module_id = p_module_id;
end &&