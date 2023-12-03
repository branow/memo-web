package com.branow.memoweb.repository;

import com.branow.memoweb.dto.user.*;
import com.branow.memoweb.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findUserByEmail(String email);

    @Query(value = "call get_user_details_by_user_id(?1)", nativeQuery = true)
    Optional<UserDetailsRepositoryDto> findUserDetailsByUserId(Integer userId);

    @Query(value = "call get_user_private_short_details_by_user_id(?1)", nativeQuery = true)
    Optional<UserPrivateShortDetailsRepositoryDto> findUserPrivateShortDetailsByUserId(Integer userId);

    @Query(value = "call get_user_general_details_by_user_id(?1)", nativeQuery = true)
    Optional<UserGeneralDetailsRepositoryDto> findUserGeneralDetailsByUserId(Integer userId);

    @Query(value = "call get_user_id_by_module_id(?1)", nativeQuery = true)
    Optional<Integer> findUserIdByModuleId(Integer moduleId);

    @Query(value = "call get_user_id_by_collection_id(?1)", nativeQuery = true)
    Optional<Integer> findUserIdByCollectionId(Integer collectionId);

    @Query(value = "call get_user_id_by_flashcard_id(?1)", nativeQuery = true)
    Optional<Integer> findUserIdByFlashcardId(Integer flashcardId);

    @Query(value = "call get_user_id_by_text_id(?1)", nativeQuery = true)
    Optional<Integer> findUserIdByTextId(Integer textId);

    @Query(value = "call get_user_public_short_details_by_collection_id(?1)", nativeQuery = true)
    Optional<UserPublicShortDetailsRepositoryDto> findUserPublicShortDetailsByCollectionId(Integer collectionId);

}
