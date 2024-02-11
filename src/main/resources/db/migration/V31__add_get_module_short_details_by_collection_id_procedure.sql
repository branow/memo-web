delimiter &&
create procedure get_module_short_details_by_collection_id(in p_collection_id int)
begin
    declare v_module_id int;
    select c.module into v_module_id from collection c where c.collection_id = p_collection_id;
    call get_module_short_details_by_module_id(v_module_id);
end &&