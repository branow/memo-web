delimiter $$
create trigger delete_media_after_formatted_text_updating
    after update on formatted_text
    for each row
begin
    if (old.image != new.image) then
        call delete_media_if_not_used(old.image);
    end if;
    if (old.audio != new.audio) then
        call delete_media_if_not_used(old.audio);
    end if;
end $$