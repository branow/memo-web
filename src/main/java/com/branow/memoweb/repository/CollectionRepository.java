package com.branow.memoweb.repository;

import com.branow.memoweb.dto.collection.CollectionShortDto;
import com.branow.memoweb.model.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CollectionRepository extends JpaRepository<Collection, Integer> {

    @Query(value = "call get_collection_short_all_by_module_id(?1)", nativeQuery = true)
    List<CollectionShortDto> findCollectionShortDtoAllByModuleId(Integer moduleId);

}
