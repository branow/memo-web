delimiter &&
create procedure search_all_module_by_query_in_page(in p_query varchar(50), in p_page_number int, in p_page_size int)
begin
    declare v_offset int;

    set v_offset = p_page_number * p_page_size;

    select m.module_id as moduleId,
           m.module_name as moduleName,
           substr(m.description, 1, 100) as shortDescription,
           count_collections_in_module(m.module_id) collectionNumber,
           u.user_id as userId,
           u.username as username
    from module m
             join user u on u.user_id = m.user
    where m.access = 1 and
        (m.module_name like concat('%', p_query, '%') or
         m.description like concat('%', p_query, '%'))
    order by collectionNumber desc
    limit v_offset, p_page_size;
end &&