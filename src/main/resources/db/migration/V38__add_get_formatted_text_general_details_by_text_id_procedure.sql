delimiter &&
create procedure get_formatted_text_general_details_by_text_id(in p_text_id int)
begin
    select t.text_id as textId,
           t.text as text,
           t.format as format,
           t.image as imageId,
           t.audio as audioId
    from formatted_text t
    where t.text_id = p_text_id;
end &&
