delimiter $$
create trigger delete_formatted_text_after_flashcard_deleting
    after delete on flashcard
    for each row
begin
    delete from formatted_text f
    where f.text_id = old.front_side or f.text_id = old.back_side;
end $$