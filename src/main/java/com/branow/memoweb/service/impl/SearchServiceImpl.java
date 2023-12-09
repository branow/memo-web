package com.branow.memoweb.service.impl;

import com.branow.memoweb.dto.search.ModuleSearchingDto;
import com.branow.memoweb.dto.search.UserSearchingDto;
import com.branow.memoweb.mapper.SearchMapper;
import com.branow.memoweb.repository.SearchRepository;
import com.branow.memoweb.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SearchServiceImpl implements SearchService {

    private final static int PAGE_SIZE = 20;
    private final SearchRepository repository;
    private final SearchMapper mapper;

    @Override
    public Page<UserSearchingDto> searchUserAllByQuery(String query, Integer pageNumber) {
        if (query.isEmpty() || query.isBlank())
            throw new IllegalArgumentException("Query must not be empty or black");
        return repository.searchUserAllByQuery(query, pageNumber, PAGE_SIZE)
                .map(mapper::toUserSearchingDto);
    }

    @Override
    public Page<ModuleSearchingDto> searchModuleAllByQuery(String query, Integer pageNumber) {
        if (query.isEmpty() || query.isBlank())
            throw new IllegalArgumentException("Query must not be empty or black");
        return repository.searchModuleAllByQuery(query, pageNumber, PAGE_SIZE)
                .map(mapper::toModuleSearchingDto);
    }


}
