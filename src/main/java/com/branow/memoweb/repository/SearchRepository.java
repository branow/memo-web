package com.branow.memoweb.repository;

import com.branow.memoweb.dto.search.UserSearchingRepositoryDto;
import com.branow.memoweb.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface SearchRepository extends JpaRepository<User, Integer> {


    default Page<UserSearchingRepositoryDto> searchUserAllByQuery(String query, Integer pageNumber, Integer pageSize) {
        List<UserSearchingRepositoryDto> list = searchUserAllByQueryInPage(query, pageNumber, pageSize);
        int total = countUserAllByQuery(query);
        return new PageImpl<>(list, PageRequest.of(pageNumber, pageSize), total);
    }

    @Query(value = "call search_all_user_by_query_in_page(:p_query, :p_page_number, :p_page_size)",nativeQuery = true)
    List<UserSearchingRepositoryDto> searchUserAllByQueryInPage(@Param("p_query") String query,
                                                                @Param("p_page_number") Integer pageNumber,
                                                                @Param("p_page_size") Integer pageSize);

    @Query(value = "call count_all_user_by_query(:p_query)",nativeQuery = true)
    Integer countUserAllByQuery(@Param("p_query") String query);

}
