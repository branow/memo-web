delimiter $$
create function count_modules_of_user_with_access(p_user_id int, p_access_id int)
    returns int
    deterministic
begin
    declare v_count int;
    select count(module_id)
    into v_count
    from module
    where user = p_user_id and access = p_access_id;
    return v_count;
end $$