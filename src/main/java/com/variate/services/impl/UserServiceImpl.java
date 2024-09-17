package com.variate.services.impl;

import com.variate.mappers.impl.UserMapper;
import com.variate.model.dto.UserDto;
import com.variate.model.entities.User;
import com.variate.repositories.UserRepository;
import com.variate.services.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    // Create User
    @Override
    public UserDto createUser(UserDto userDto) {
        User user = userMapper.mapFrom(userDto);
        user.setPassword(userMapper.encodePassword(user.getPassword()));
        return userMapper.mapTo(userRepository.save(user));
    }

    // Read One User by ID
    @Override
    public UserDto getUserById(Long id) {
        return userRepository.findById(id)
                .map(userMapper::mapTo)
                .orElseThrow(() -> new RuntimeException(STR."User not found with ID: \{id}"));
    }

    // Read One User by Username
    @Override
    public UserDto getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .map(userMapper::mapTo)
                .orElseThrow(() -> new RuntimeException(STR."User not found with username: \{username}"));
    }

    // Read Many Users
    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::mapTo)
                .collect(Collectors.toList());
    }

    // Update (PUT) User
    @Override
    public UserDto updateUser(Long id, UserDto userDto) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(STR."User not found with ID: \{id}"));

        // Update fields entirely
        existingUser.setUsername(userDto.getUsername());
        existingUser.setEmail(userDto.getEmail());
        if (userDto.getPassword() != null && !userDto.getPassword().isEmpty()) {
            existingUser.setPassword(userMapper.encodePassword(userDto.getPassword()));
        }

        return userMapper.mapTo(userRepository.save(existingUser));
    }

    // Partial Update (PATCH) User
    @Override
    public UserDto patchUser(Long id, UserDto userDto) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(STR."User not found with ID: \{id}"));

        // Update only provided fields
        if (userDto.getUsername() != null) {
            existingUser.setUsername(userDto.getUsername());
        }
        if (userDto.getEmail() != null) {
            existingUser.setEmail(userDto.getEmail());
        }
        if (userDto.getPassword() != null && !userDto.getPassword().isEmpty()) {
            existingUser.setPassword(userMapper.encodePassword(userDto.getPassword()));
        }

        return userMapper.mapTo(userRepository.save(existingUser));
    }

    // Delete User
    @Override
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException(STR."User not found with ID: \{id}");
        }
        userRepository.deleteById(id);
    }

    // Load UserDetails for Spring Security
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(STR."User not found with username: \{username}"));
    }
}
