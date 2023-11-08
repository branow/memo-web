package com.branow.memoweb.service.impl;

import com.branow.memoweb.dto.user.UserPrivateShortDetailsDto;
import com.branow.memoweb.dto.user.UserPublicGeneralDetailsDto;
import com.branow.memoweb.exception.entitynotfound.UserNotFoundException;
import com.branow.memoweb.mapper.UserMapper;
import com.branow.memoweb.model.User;
import com.branow.memoweb.repository.UserRepository;
import com.branow.memoweb.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final JwtTokenService jwtTokenService;
    private final UserMapper userMapper;


    public User getByEmail(String email) {
        return repository.findUserByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("email", email));
    }

    @Override
    public UserPrivateShortDetailsDto getPrivateShortDetailsByEmail(String email) {
        return repository.findUserPrivateShortDtoByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("email", email));
    }

    @Override
    public UserPrivateShortDetailsDto getPrivateShortDetailsByJwtToken(String jwtToken) {
        String email = jwtTokenService.getSubject(jwtToken);
        return getPrivateShortDetailsByEmail(email);
    }

    @Override
    public UserPublicGeneralDetailsDto getPublicGeneralDetailsById(Integer id) {
        return repository.findUserPublicDtoById(id)
                .orElseThrow(() -> new UserNotFoundException("id", id));
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
