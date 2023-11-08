package com.branow.memoweb.service.impl;

import com.branow.memoweb.dto.user.*;
import com.branow.memoweb.exception.entitynotfound.UserNotFoundException;
import com.branow.memoweb.mapper.UserMapper;
import com.branow.memoweb.model.User;
import com.branow.memoweb.repository.UserRepository;
import com.branow.memoweb.service.ModuleService;
import com.branow.memoweb.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final UserMapper mapper;

    private final JwtTokenService jwtTokenService;
    private final ModuleService moduleService;


    public User getByEmail(String email) {
        return repository.findUserByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("email", email));
    }

    @Override
    public UserDetailsDto getDetailsByJwtToken(String jwtToken) {
        Integer id = jwtTokenService.getUserId(jwtToken);
        return getDetailsById(id);
    }

    @Override
    public UserDetailsDto getDetailsById(Integer id) {
        return mapper.toUserDetailsDto(repository.findUserDetailsByUserId(id)
                .orElseThrow(() -> new UserNotFoundException("id", id)));
    }

    @Override
    public UserPrivateShortDetailsDto getPrivateShortDetailsById(Integer id) {
        return mapper.toUserPrivateShortDetailsDto(repository.findUserPrivateShortDetailsByUserId(id)
                .orElseThrow(() -> new UserNotFoundException("id", id)));
    }

    @Override
    public UserPrivateGeneralDetailsDto getPrivateGeneralDetailsById(Integer id) {
        UserGeneralDetailsRepositoryDto details = repository.findUserGeneralDetailsByUserId(id)
                .orElseThrow(() -> new UserNotFoundException("id", id));
        List<Integer> moduleIds = moduleService.getIdAllByUserId(id);
        return mapper.toUserPrivateGeneralDetailsDto(details, moduleIds);
    }

    @Override
    public UserPublicGeneralDetailsDto getPublicGeneralDetailsById(Integer id) {
        UserGeneralDetailsRepositoryDto details = repository.findUserGeneralDetailsByUserId(id)
                .orElseThrow(() -> new UserNotFoundException("id", id));
        List<Integer> moduleIds = moduleService.getIdWithPublicAccessAllByUserId(id);
        return mapper.toUserPublicGeneralDetailsDto(details, moduleIds);
    }

    @Override
    public UserPrivateShortDetailsDto getPrivateShortDetailsByJwtToken(String jwtToken) {
        Integer id = jwtTokenService.getUserId(jwtToken);
        return getPrivateShortDetailsById(id);
    }

    @Override
    public UserPrivateGeneralDetailsDto getPrivateGeneralDetailsByJwtToken(String jwtToken) {
        Integer id = jwtTokenService.getUserId(jwtToken);
        return getPrivateGeneralDetailsById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return getByEmail(email);
    }

    @Override
    public User save(User user) {
        return repository.save(user);
    }
}
