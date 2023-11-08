

delimiter &&
create procedure get_user_details_by_email(in p_email varchar(320))
begin
    select
        user_id as userId,
        username as username,
        description as description,
        email as email
    from user
    where email = p_email;
end &&


delimiter &&
create procedure get_user_general_details_by_email(in p_email varchar(320))
begin
    select
        user_id as userId,
        username as username,
        description as description
    from user
    where email = p_email;
end &&


delimiter &&
create procedure get_user_general_details_by_user_id(in p_user_id int)
begin
    select
        user_id as userId,
        username as username,
        description as description
    from user
    where user_id = p_user_id;
end &&


delimiter &&
create procedure get_user_private_short_details_by_email(in p_email varchar(320))
begin
    select
        user_id as userId,
        username as username,
        email as email,
        enabled as enabled
    from user
    where email = p_email;
end &&

