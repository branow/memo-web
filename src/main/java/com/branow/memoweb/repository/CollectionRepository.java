package com.branow.memoweb.repository;

import com.branow.memoweb.dto.collection.CollectionShortDetailsRepositoryDto;
import com.branow.memoweb.model.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CollectionRepository extends JpaRepository<Collection, Integer> {

    @Query(value = "call get_collection_short_details_all_by_module_id(?1)", nativeQuery = true)
    List<CollectionShortDetailsRepositoryDto> findCollectionShortDetailsDtoAllByModuleId(Integer moduleId);

    @Query("select c from Module m join m.collections c where m.moduleId = ?1")
    List<Collection> findAllByModuleId(Integer moduleId);

}
