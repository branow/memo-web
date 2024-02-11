delimiter &&
create procedure get_collection_short_details_by_collection_id(in p_collection_id int)
begin
    select c.collection_id as collectionId,
           c.collection_name as collectionName,
           count_cards_in_collection(c.collection_id) as size
    from collection c
    where c.collection_id = p_collection_id;
end &&