delimiter $$
create procedure get_media_short_details_by_media_id(in p_media_id int)
begin
    select m.media_id as mediaId,
           m.format as format
    from media m
    where m.media_id = p_media_id;
end $$
