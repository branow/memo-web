package com.branow.memoweb.service.impl;

import com.branow.memoweb.dto.user.*;
import com.branow.memoweb.exception.JwtIllegalSubjectException;
import com.branow.memoweb.exception.entitynotfound.UserNotFoundException;
import com.branow.memoweb.mapper.UserMapper;
import com.branow.memoweb.model.User;
import com.branow.memoweb.repository.UserRepository;
import com.branow.memoweb.service.JwtBelongingChecker;
import com.branow.memoweb.service.JwtService;
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

    private final JwtBelongingChecker jwtBelongingChecker;
    private final ModuleService moduleService;

    public User getById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("id", id));
    }

    public User getByEmail(String email) {
        return repository.findUserByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("email", email));
    }

    @Override
    public UserDetailsDto getDetailsDtoByJwtToken(String jwtToken) {
        Integer id = jwtBelongingChecker.getUserId(jwtToken);
        return getDetailsDtoByUserId(id);
    }

    @Override
    public UserDetailsDto getDetailsDtoByUserId(Integer id) {
        return mapper.toUserDetailsDto(repository.findUserDetailsByUserId(id)
                .orElseThrow(() -> new UserNotFoundException("id", id)));
    }

    @Override
    public UserPrivateShortDetailsDto getPrivateShortDetailsDtoByUserId(Integer id) {
        return mapper.toUserPrivateShortDetailsDto(repository.findUserPrivateShortDetailsByUserId(id)
                .orElseThrow(() -> new UserNotFoundException("id", id)));
    }

    @Override
    public UserPrivateGeneralDetailsDto getPrivateGeneralDetailsDtoByUserId(Integer id) {
        UserGeneralDetailsRepositoryDto details = repository.findUserGeneralDetailsByUserId(id)
                .orElseThrow(() -> new UserNotFoundException("id", id));
        List<Integer> moduleIds = moduleService.getModuleIdAllByUserId(id);
        return mapper.toUserPrivateGeneralDetailsDto(details, moduleIds);
    }

    @Override
    public UserPublicGeneralDetailsDto getPublicGeneralDetailsDtoByUserId(Integer id) {
        UserGeneralDetailsRepositoryDto details = repository.findUserGeneralDetailsByUserId(id)
                .orElseThrow(() -> new UserNotFoundException("id", id));
        List<Integer> moduleIds = moduleService.getModuleIdWithPublicAccessAllByUserId(id);
        return mapper.toUserPublicGeneralDetailsDto(details, moduleIds);
    }

    @Override
    public UserPrivateShortDetailsDto getPrivateShortDetailsDtoByJwtToken(String jwtToken) {
        Integer id = jwtBelongingChecker.getUserId(jwtToken);
        return getPrivateShortDetailsDtoByUserId(id);
    }

    @Override
    public UserPrivateGeneralDetailsDto getPrivateGeneralDetailsDtoByJwtToken(String jwtToken) {
        Integer id = jwtBelongingChecker.getUserId(jwtToken);
        return getPrivateGeneralDetailsDtoByUserId(id);
    }

    @Override
    public void deleteByJwtToken(String jwt) {
        repository.deleteById(jwtBelongingChecker.getUserId(jwt));
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
