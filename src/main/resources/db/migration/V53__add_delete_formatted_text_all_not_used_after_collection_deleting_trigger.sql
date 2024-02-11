delimiter $$
create trigger delete_formatted_text_all_not_used_after_collection_deleting
    after delete on collection
    for each row
begin
    call delete_formatted_text_all_not_used();
end $$