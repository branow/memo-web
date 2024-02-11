delimiter &&
create procedure get_user_id_by_text_id(in p_text_id int)
begin
    select u.user_id
    from formatted_text ft
             join flashcard fc on ft.text_id = fc.back_side or ft.text_id = fc.front_side
             join collection c on c.collection_id = fc.collection
             join module m on m.module_id = c.module
             join user u on m.user = u.user_id
    where ft.text_id = p_text_id;
end &&