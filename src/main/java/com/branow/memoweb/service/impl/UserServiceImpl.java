package com.branow.memoweb.service.impl;

import com.branow.memoweb.dto.user.UserPrivateShortDto;
import com.branow.memoweb.dto.user.UserPublicDto;
import com.branow.memoweb.exception.UserNotFoundException;
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

    private final UserRepository userRepository;


    public User getByEmail(String email) {
        return userRepository.findUserByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User with such email not found: " + email));
    }

    @Override
    public UserPrivateShortDto getPrivateShortByEmail(String email) {
        return userRepository.findUserPrivateShortDtoByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User with such email not found: " + email));
    }

    @Override
    public UserPublicDto getPublicDtoById(Integer id) {
        return userRepository.findUserPublicDtoById(id)
                .orElseThrow(() -> new UserNotFoundException("User with such email not found: " + id));
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return getByEmail(email);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }
}
