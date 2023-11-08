package com.branow.memoweb.repository;

import com.branow.memoweb.dto.user.UserGeneralDetailsRepositoryDto;
import com.branow.memoweb.dto.user.UserPrivateShortDetailsDto;
import com.branow.memoweb.dto.user.UserPrivateShortDetailsRepositoryDto;
import com.branow.memoweb.dto.user.UserPublicGeneralDetailsDto;
import com.branow.memoweb.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findUserByEmail(String email);

    @Query("select u.userId as userId, u.username as username, u.email as email, u.enabled as enabled from User u where u.email = ?1")
    Optional<UserPrivateShortDetailsRepositoryDto> findUserPrivateShortDtoByEmail(String email);

    @Query("select u.userId as userId, u.username as username, u.description as description from User u where u.email = ?1")
    Optional<UserGeneralDetailsRepositoryDto> findUserGeneralDetailsByEmail(String email);

    @Query("select u.userId as userId, u.username as username, u.description as description from User u where u.userId = ?1")
    Optional<UserGeneralDetailsRepositoryDto> findUserGeneralDetailsByUserId(Integer id);

}
