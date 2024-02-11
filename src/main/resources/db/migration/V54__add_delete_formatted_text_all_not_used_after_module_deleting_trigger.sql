delimiter $$
create trigger delete_formatted_text_all_not_used_after_module_deleting
    after delete on module
    for each row
begin
    call delete_formatted_text_all_not_used();
end $$