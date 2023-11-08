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
        String email = jwtTokenService.getSubject(jwtToken);
        return getDetailsByEmail(email);
    }

    @Override
    public UserDetailsDto getDetailsByEmail(String email) {
        return mapper.toUserDetailsDto(repository.findUserDetailsByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("email", email)));
    }

    @Override
    public UserPrivateShortDetailsDto getPrivateShortDetailsByEmail(String email) {
        return mapper.toUserPrivateShortDetailsDto(repository.findUserPrivateShortDetailsByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("email", email)));
    }

    @Override
    public UserPrivateGeneralDetailsDto getPrivateGeneralDetailsByEmail(String email) {
        UserGeneralDetailsRepositoryDto details = repository.findUserGeneralDetailsByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("email", email));
        List<Integer> moduleIds = moduleService.getIdAllByUserId(details.getUserId());
        return mapper.toUserPrivateGeneralDetailsDto(details, moduleIds);
    }

    @Override
    public UserPrivateShortDetailsDto getPrivateShortDetailsByJwtToken(String jwtToken) {
        String email = jwtTokenService.getSubject(jwtToken);
        return getPrivateShortDetailsByEmail(email);
    }

    @Override
    public UserPublicGeneralDetailsDto getPublicGeneralDetailsById(Integer id) {
        UserGeneralDetailsRepositoryDto details = repository.findUserGeneralDetailsByUserId(id)
                .orElseThrow(() -> new UserNotFoundException("id", id));
        List<Integer> moduleIds = moduleService.getIdWithPublicAccessAllByUserId(id);
        return mapper.toUserPublicGeneralDetailsDto(details, moduleIds);
    }

    @Override
    public UserPrivateGeneralDetailsDto getPrivateGeneralDetailsByJwtToken(String jwtToken) {
        String email = jwtTokenService.getSubject(jwtToken);
        return getPrivateGeneralDetailsByEmail(email);
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
