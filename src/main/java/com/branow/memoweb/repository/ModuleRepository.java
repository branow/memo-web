package com.branow.memoweb.repository;

import com.branow.memoweb.dto.module.ModuleDetailsRepositoryDto;
import com.branow.memoweb.dto.module.ModuleShortDetailsRepositoryDto;
import com.branow.memoweb.model.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

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

    List<Module> findAllByUser(Integer userId);

    @Query("select m.user from Module m where m.moduleId = ?1")
    Optional<Integer> findUserByModuleId(Integer moduleId);
}
