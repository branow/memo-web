

delimiter &&
create procedure get_flashcard_short_details_by_flashcard_id(in p_flashcard_id int)
begin
    select f.flashcard_id as flashcardId,
           f.front_side as frontSide,
           f.back_side as backSide
    from flashcard f
    where f.flashcard_id = p_flashcard_id;
end &&


delimiter &&
create procedure get_flashcard_score_all_by_collection_id(in p_collection_id int, in p_study_type_id int)
begin
    select f.flashcard_id as flashcardId,
           s.score as score,
           s.study_type as studyType,
           s.study_repetition as studyRepetition,
           s.reset_time as resetTime,
           s.study_time as studyTime
        from flashcard f
        left join score s on f.flashcard_id = s.flashcard and s.study_type = p_study_type_id
        where f.collection = p_collection_id;
end &&


