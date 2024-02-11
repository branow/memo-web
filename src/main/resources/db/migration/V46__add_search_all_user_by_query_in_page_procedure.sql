delimiter &&
create procedure search_all_user_by_query_in_page(in p_query varchar(50), in p_page_number int, in p_page_size int)
begin
    declare v_offset int;

    set v_offset = p_page_number * p_page_size;

    select u.user_id as userId,
           u.username as username,
           substr(u.description, 1, 100) as shortDescription,
           count_modules_of_user_with_access(u.user_id, 1) as publicModuleNumber,
           count_modules_of_user_with_access(u.user_id, 2) as privateModuleNumber
    from user u
    where u.username like concat('%', p_query, '%') or
            u.description like concat('%', p_query, '%')
    order by publicModuleNumber desc
    limit v_offset, p_page_size;
end &&