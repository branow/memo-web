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
end $$