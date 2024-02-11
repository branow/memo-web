delimiter &&
create procedure get_module_short_details_by_module_id(in p_module_id int)
begin
    select m.module_id as moduleId,
           m.module_name as moduleName,
           a.access as access,
           substr(m.description, 1, 100) as shortDescription
    from module m
             join access_type a on a.access_id = m.access
    where m.module_id = p_module_id;
end &&