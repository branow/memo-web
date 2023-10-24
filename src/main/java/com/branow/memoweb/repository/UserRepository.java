package com.branow.memoweb.repository;

import com.branow.memoweb.dto.user.UserPrivateShortDto;
import com.branow.memoweb.dto.user.UserPublicDto;
import com.branow.memoweb.dto.user.UserPublicShortDto;
import com.branow.memoweb.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findUserByEmail(String email);

    @Query("select u from User u where u.email = ?1")
    Optional<UserPrivateShortDto> findUserPrivateShortDtoByEmail(String email);

    @Query("select u from User u where u.userId = ?1")
    Optional<UserPublicDto> findUserPublicDtoById(Integer id);

}
