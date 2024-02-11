delimiter &&
create procedure get_score_params_all_by_flashcard_id(in p_flashcard_id int)
begin
    select st.study_name as studyType,
           s.score as score,
           s.study_time as studyTime,
           s.reset_time as resetTime
    from score s
             join study_type st on s.study_type = st.study_id
    where s.flashcard = p_flashcard_id;
end &&