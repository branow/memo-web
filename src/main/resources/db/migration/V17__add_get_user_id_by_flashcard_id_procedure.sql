delimiter &&
create procedure get_user_id_by_flashcard_id(in p_flashcard_id int)
begin
    select u.user_id
    from flashcard fc
             join collection c on c.collection_id = fc.collection
             join module m on m.module_id = c.module
             join user u on m.user = u.user_id
    where fc.flashcard_id = p_flashcard_id;
end &&