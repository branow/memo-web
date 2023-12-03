package com.branow.memoweb.repository;

import com.branow.memoweb.dto.module.ModuleDetailsRepositoryDto;
import com.branow.memoweb.dto.module.ModuleShortDetailsRepositoryDto;
import com.branow.memoweb.model.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface ModuleRepository extends JpaRepository<Module, Integer> {

    @Query(value = "call get_module_details_by_module_id(?1)", nativeQuery = true)
    Optional<ModuleDetailsRepositoryDto> findDetailsByModuleId(Integer id);

    @Query("select m.moduleId from User u join u.modules m where u.userId = ?1")
    List<Integer> findModuleIdAllByUserId(Integer userId);

    @Query(value = "call get_module_id_with_public_access_all_by_user_id(?1)", nativeQuery = true)
    List<Integer> findModuleIdWithPublicAccessAllByUserId(Integer userId);

    @Query(value = "call get_module_short_details_by_module_id(?1)", nativeQuery = true)
    Optional<ModuleShortDetailsRepositoryDto> findShortDetailsByModuleId(Integer id);

    @Query(value = "call get_module_short_details_by_collection_id(?1)", nativeQuery = true)
    Optional<ModuleShortDetailsRepositoryDto> findShortDetailsByCollectionId(Integer collectionId);

    List<Module> findAllByUser(Integer userId);

    @Transactional
    @Modifying
    @Query(value = "delete from module m where m.module_id = ?1", nativeQuery = true)
    void deleteByModuleId(Integer moduleId);

}
