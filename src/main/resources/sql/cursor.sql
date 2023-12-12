
delimiter &&
create procedure calc_average_score_by_flashcard_id(in p_flashcard_id int)
begin
    declare v_done int;
    declare v_num int;
    declare v_sum int;
    declare v_study_id int;
    declare v_score int;
    declare cur cursor for select study_id from study_type;
    declare continue handler for sqlstate '02000' set v_done = 1;

    set v_sum = 0;
    select count(*) into v_num from study_type;

    open cur;

    score_loop: loop
        fetch cur into v_study_id;

        if v_done = 1 then
            leave score_loop;
        end if;

        select score
            into v_score
            from score
            where flashcard = p_flashcard_id and study_type = v_study_id;

        if v_score is not null then
            set v_sum = v_sum + v_score;
        end if;

    end loop score_loop;

    close cur;

    select v_sum / v_num as avarage_score;
end &&;

