delimiter $$
create function count_cards_in_collection(p_collection_id int)
    returns int
    deterministic
begin
    declare v_count int;
    select count(flashcard_id)
    into v_count
    from flashcard
    where collection = p_collection_id;
    return v_count;
end $$