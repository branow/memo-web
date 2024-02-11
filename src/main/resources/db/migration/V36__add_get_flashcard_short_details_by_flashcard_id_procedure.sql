delimiter &&
create procedure get_flashcard_short_details_by_flashcard_id(in p_flashcard_id int)
begin
    select f.flashcard_id as flashcardId,
           f.front_side as frontSide,
           f.back_side as backSide
    from flashcard f
    where f.flashcard_id = p_flashcard_id;
end &&