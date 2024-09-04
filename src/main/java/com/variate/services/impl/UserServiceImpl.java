package com.variate.services.impl;

import com.variate.mappers.impl.UserMapper;
import com.variate.model.dto.UserDto;
import com.variate.repositories.UserRepository;
import com.variate.services.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        var user = userMapper.mapFrom(userDto);
        user.setPassword(userMapper.encodePassword(user.getPassword()));
        return userMapper.mapTo(userRepository.save(user));
    }

    @Override
    public UserDto getUserById(Long id) {
        return userRepository.findById(id)
                .map(userMapper::mapTo)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public UserDto getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .map(userMapper::mapTo)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
