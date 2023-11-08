

delimiter &&
create procedure get_collection_short_details_all_by_module_id(in p_module_id int)
begin
    select c.collection_id as collectionId,
           c.collection_name as collectionName,
           count_cards_in_collection(c.collection_id) as size
    from collection c
    where c.module = p_module_id;
end &&


