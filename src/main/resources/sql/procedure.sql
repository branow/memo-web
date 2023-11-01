
delimiter &&
create procedure get_module_id_from_module_with_public_access_by_user_id(in p_user_id int)
begin
    select m.module_id
        from module m
        join access_type a
            on a.access_id = m.access
        where m.user = p_user_id and
              a.access = 'public';
end &&


delimiter &&
create procedure get_avg_score_for_collection(in p_collection_id int)
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
create procedure get_avg_score_for_module(in p_module_id int)
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


delimiter &&
create procedure get_module_short_details_by_id(in p_module_id int)
begin
    select m.module_id as moduleId,
           m.module_name as moduleName,
           a.access as access,
           substr(m.description, 1, 100) as shortDescription
        from module m
        join access_type a on a.access_id = m.access
        where m.module_id = p_module_id;
end &&


delimiter &&
create procedure get_collection_short_details_all_by_module_id(in p_module_id int)
begin
    select c.collection_id as collectionId,
           c.collection_name as collectionName,
           count_cards_in_collection(c.collection_id) as size
        from collection c
        where c.module = p_module_id;
end &&




