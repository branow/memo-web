

delimiter $$
create trigger delete_formatted_text_after_flashcard_deleting
    after delete on flashcard
    for each row
begin
    delete from formatted_text f
    where f.text_id = old.front_side or f.text_id = old.back_side;
end $$


delimiter $$
create trigger delete_media_after_formatted_text_deleting
    after delete on formatted_text
    for each row
begin
    call delete_media_if_not_used(old.audio);
    call delete_media_if_not_used(old.image);
end $$


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


delimiter $$
create trigger delete_formatted_text_all_not_used_after_collection_deleting
    after delete on collection
    for each row
begin
    call delete_formatted_text_all_not_used();
end $$


delimiter $$
create trigger delete_formatted_text_all_not_used_after_module_deleting
    after delete on module
    for each row
begin
    call delete_formatted_text_all_not_used();
end $$


delimiter $$
create trigger delete_formatted_text_all_not_used_after_user_deleting
    after delete on user
    for each row
begin
    call delete_formatted_text_all_not_used();
end $$


