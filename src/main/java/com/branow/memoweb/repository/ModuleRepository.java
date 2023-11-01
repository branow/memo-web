package com.branow.memoweb.repository;

import com.branow.memoweb.dto.module.ModuleShortDetailsRepositoryDto;
import com.branow.memoweb.model.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ModuleRepository extends JpaRepository<Module, Integer> {

    @Query("select m.moduleId from User u join u.modules m where u.userId = ?1")
    List<Integer> findModuleIdAllByUserId(Integer userId);

    @Query(value = "call get_module_id_from_module_with_public_access_by_user_id(?1)", nativeQuery = true)
    List<Integer> findModuleIdWithPublicAccessAllByUserId(Integer userId);

    @Query(value = "call get_module_short_details_by_id(?1)", nativeQuery = true)
    Optional<ModuleShortDetailsRepositoryDto> findModuleShortDetailsDtoById(Integer id);

}
