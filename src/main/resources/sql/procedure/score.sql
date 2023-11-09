
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


delimiter &&
create procedure get_score_average_params_all_by_collection_id(in p_collection_id int)
begin
    select st.study_name as studyType,
           avg(s.score) as score,
           from_unixtime(avg(unix_timestamp(s.study_time))) as studyTime,
           from_unixtime(avg(unix_timestamp(s.reset_time))) as resetTime
    from score s
             join flashcard f on s.flashcard = f.flashcard_id
             join study_type st on s.study_type = st.study_id
    where f.collection = p_collection_id group by s.study_type;
end &&


delimiter &&
create procedure get_score_average_params_all_by_module_id(in p_module_id int)
begin
    select st.study_name as studyType,
           avg(s.score) as score,
           from_unixtime(avg(unix_timestamp(s.study_time))) as studyTime,
           from_unixtime(avg(unix_timestamp(s.reset_time))) as resetTime
    from score s
             join flashcard f on s.flashcard = f.flashcard_id
             join collection c on f.collection = c.collection_id
             join study_type st on s.study_type = st.study_id
    where c.module = p_module_id
    group by s.study_type;
end &&
