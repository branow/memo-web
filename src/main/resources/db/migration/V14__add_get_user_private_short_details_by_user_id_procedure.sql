delimiter &&
create procedure get_user_private_short_details_by_user_id(in p_user_id int)
begin
    select
        user_id as userId,
        username as username,
        email as email,
        enabled as enabled
    from user
    where user_id = p_user_id;
end &&