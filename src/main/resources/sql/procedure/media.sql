

delimiter $$
create procedure delete_media_if_not_used(in p_media_id int)
begin
    if not exists(select f.text_id
                  from formatted_text f
                  where f.image = p_media_id or f.audio = p_media_id) then
        delete from media where media_id = p_media_id;
    end if;
end $$


delimiter $$
create procedure get_media_short_details_by_media_id(in p_media_id int)
begin
    select m.media_id as mediaId,
           m.format as format
        from media m
        where m.media_id = p_media_id;
end $$

