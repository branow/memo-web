

delimiter &&
create procedure get_collection_short_details_all_by_module_id(in p_module_id int)
begin
    select c.collection_id as collectionId,
           c.collection_name as collectionName,
           count_cards_in_collection(c.collection_id) as size
    from collection c
    where c.module = p_module_id;
end &&


delimiter &&
create procedure get_collection_short_details_by_collection_id(in p_collection_id int)
begin
    select c.collection_id as collectionId,
           c.collection_name as collectionName,
           count_cards_in_collection(c.collection_id) as size
    from collection c
    where c.collection_id = p_collection_id;
end &&


delimiter &&
create procedure get_collection_short_details_by_flashcard_id(in p_flashcard_id int)
begin
    declare v_collection_id int;
    select collection into v_collection_id from flashcard where flashcard_id = p_flashcard_id;
    call get_collection_short_details_by_collection_id(v_collection_id);
end &&
