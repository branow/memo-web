delimiter $$
create trigger delete_media_after_formatted_text_deleting
    after delete on formatted_text
    for each row
begin
    call delete_media_if_not_used(old.audio);
    call delete_media_if_not_used(old.image);
end $$