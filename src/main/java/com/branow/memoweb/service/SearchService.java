package com.branow.memoweb.service;

import com.branow.memoweb.dto.search.UserSearchingDto;
import org.springframework.data.domain.Page;

public interface SearchService {

    Page<UserSearchingDto> searchUserAllByQuery(String query, Integer pageNumber);

}
