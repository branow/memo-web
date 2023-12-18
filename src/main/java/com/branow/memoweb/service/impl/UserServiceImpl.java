package com.branow.memoweb.service.impl;

import com.branow.memoweb.dto.user.*;
import com.branow.memoweb.exception.EntityNotFoundException;
import com.branow.memoweb.mapper.UserMapper;
import com.branow.memoweb.model.User;
import com.branow.memoweb.repository.UserRepository;
import com.branow.memoweb.service.JwtBelongingChecker;
import com.branow.memoweb.service.ModuleService;
import com.branow.memoweb.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final UserMapper mapper;

    private final JwtBelongingChecker jwtBelongingChecker;
    private final PasswordEncoder passwordEncoder;
    private final ModuleService moduleService;

    public User getById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(User.class, "id", id));
    }

    public User getByEmail(String email) {
        return repository.findUserByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException(User.class, "email", email));
    }

    @Override
    public User getByUserId(Integer userId) {
        return repository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException(User.class, "id", userId));
    }

    @Override
    public UserDetailsDto getDetailsDtoByJwtToken(String jwtToken) {
        Integer id = jwtBelongingChecker.getUserId(jwtToken);
        return getDetailsDtoByUserId(id);
    }

    @Override
    public UserDetailsDto getDetailsDtoByUserId(Integer id) {
        return mapper.toUserDetailsDto(repository.findUserDetailsByUserId(id)
                .orElseThrow(() -> new EntityNotFoundException(User.class, "id", id)));
    }

    @Override
    public UserPrivateShortDetailsDto getPrivateShortDetailsDtoByUserId(Integer id) {
        return mapper.toUserPrivateShortDetailsDto(repository.findUserPrivateShortDetailsByUserId(id)
                .orElseThrow(() -> new EntityNotFoundException(User.class, "id", id)));
    }

    @Override
    public UserGeneralDetailsDto getGeneralDetailsDtoByJwtTokenAndUserId(String jwt, Integer userId) {
        Integer jwtId = jwt != null ? jwtBelongingChecker.getUserId(jwt) : -1;
        UserGeneralDetailsRepositoryDto details = repository.findUserGeneralDetailsByUserId(userId)
                .orElseThrow(() -> new EntityNotFoundException(User.class, "id", userId));
        List<Integer> moduleIds = jwtId.equals(userId) ? moduleService.getModuleIdAllByUserId(userId) :
                moduleService.getModuleIdWithPublicAccessAllByUserId(userId);
        return mapper.toUserGeneralDetailsDto(details, moduleIds);
    }

    @Override
    public UserPrivateShortDetailsDto getPrivateShortDetailsDtoByJwtToken(String jwtToken) {
        Integer id = jwtBelongingChecker.getUserId(jwtToken);
        return getPrivateShortDetailsDtoByUserId(id);
    }

    @Override
    public void deleteByJwtToken(String jwt) {
        repository.deleteById(jwtBelongingChecker.getUserId(jwt));
    }

    @Override
    public void changePassword(String jwt, ChangePasswordDto dto) {
        Integer id = jwtBelongingChecker.getUserId(jwt);
        User user = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(User.class, "id", id));
        if (passwordEncoder.matches(dto.getCurrentPassword(), user.getPassword())) {
            user.setPassword(passwordEncoder.encode(dto.getNewPassword()));
            repository.save(user);
        } else {
            throw new IllegalStateException("Invalid current password");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return getByEmail(email);
    }

    @Override
    public UserSaveDto save(String jwt, UserSaveDto dto) {
        if (dto == null || dto.getUserId() == null)
            throw new IllegalStateException("User or User id is null");
        jwtBelongingChecker.belongToOrThrow(jwt, dto.getUserId());
        User oldUser = getById(dto.getUserId());
        User saved = repository.save(mapper.toUser(dto, oldUser.getPassword(), oldUser.isEnabled()));
        return mapper.toUserSaveDto(saved);
    }

    @Override
    public User save(User user) {
        return repository.save(user);
    }
}
