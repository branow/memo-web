delimiter &&
create procedure count_all_user_by_query(in p_query varchar(50))
begin
    select count(*)
    from user
    where username like concat('%', p_query, '%') or
            description like concat('%', p_query, '%');
end &&