package com.branow.memoweb.service;

import com.branow.memoweb.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User save(User user);

    User getByEmail(String email);
}
