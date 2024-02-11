delimiter &&
create procedure get_collection_short_details_by_flashcard_id(in p_flashcard_id int)
begin
    declare v_collection_id int;
    select collection into v_collection_id from flashcard where flashcard_id = p_flashcard_id;
    call get_collection_short_details_by_collection_id(v_collection_id);
end &&