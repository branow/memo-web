delimiter $$
create procedure delete_formatted_text_all_not_used()
begin
    delete from formatted_text t
    where not exists(
        select f.flashcard_id
        from flashcard f
        where f.back_side = t.text_id or f.front_side = t.text_id
    );
end $$