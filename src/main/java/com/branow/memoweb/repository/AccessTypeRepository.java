package com.branow.memoweb.repository;

import com.branow.memoweb.model.AccessType;
import com.branow.memoweb.model.auxilary.Access;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccessTypeRepository extends JpaRepository<AccessType, Integer> {

    Optional<AccessType> findByAccess(Access access);

}
