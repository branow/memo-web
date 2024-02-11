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
end $$