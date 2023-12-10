
create or replace view flashcard_details as
select f.flashcard_id,
       ftf.text as front_side,
       ftb.text as back_side,
       c.collection_name,
       m.module_name
    from flashcard f
    left join formatted_text ftf on f.front_side = ftf.text_id
    left join formatted_text ftb on f.back_side = ftb.text_id
    left join collection c on f.collection = c.collection_id
    left join module m on c.module = m.module_id
