package com.branow.memoweb.mapper;

import com.branow.memoweb.dto.search.UserSearchingDto;
import com.branow.memoweb.dto.search.UserSearchingRepositoryDto;
import org.springframework.stereotype.Service;

@Service
public class SearchMapper {

    public UserSearchingDto toUserSearchingDto(UserSearchingRepositoryDto dto) {
        return UserSearchingDto.builder()
                .userId(dto.getUserId())
                .publicModuleNumber(dto.getPublicModuleNumber())
                .privateModuleNumber(dto.getPrivateModuleNumber())
                .shortDescription(dto.getShortDescription())
                .username(dto.getUsername())
                .build();
    }

}
