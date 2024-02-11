delimiter &&
create procedure count_all_module_by_query(in p_query varchar(50))
begin
    select count(*)
    from module
    where module.module_name like concat('%', p_query, '%') or
            description like concat('%', p_query, '%');
end &&