package com.branow.memoweb.repository;

import com.branow.memoweb.model.Media;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MediaRepository extends JpaRepository<Media, Integer> {
}
