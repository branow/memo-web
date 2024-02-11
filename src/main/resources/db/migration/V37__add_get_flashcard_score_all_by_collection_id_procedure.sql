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