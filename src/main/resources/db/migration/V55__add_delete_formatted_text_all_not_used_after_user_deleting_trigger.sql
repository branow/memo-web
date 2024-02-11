delimiter $$
create trigger delete_formatted_text_all_not_used_after_user_deleting
    after delete on user
    for each row
begin
    call delete_formatted_text_all_not_used();
end $$