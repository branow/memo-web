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
end $$