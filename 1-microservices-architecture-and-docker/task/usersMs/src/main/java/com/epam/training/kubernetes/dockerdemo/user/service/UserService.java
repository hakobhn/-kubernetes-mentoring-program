package com.epam.training.kubernetes.dockerdemo.user.service;

import com.epam.training.kubernetes.dockerdemo.user.domain.model.User;
import com.epam.training.kubernetes.dockerdemo.user.domain.repository.UserRepository;
import com.epam.training.kubernetes.dockerdemo.user.dto.UserDto;
import com.epam.training.kubernetes.dockerdemo.user.dto.UserInput;
import com.epam.training.kubernetes.dockerdemo.user.exception.NotFoundException;
import com.epam.training.kubernetes.dockerdemo.user.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserDto create(UserInput input) {
        User entity = new User();
        entity.setUsername(input.getUsername());
        return userMapper.userToUserDto(userRepository.save(entity));
    }

    public UserDto getById(Long id) {
        return userMapper.userToUserDto(userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User with id "+ id +", not found.")));
    }

    public UserDto update(Long id, UserInput input) {
        return userRepository.findById(id)
                .map(
                        user -> {
                            user.setUsername(input.getUsername());
                            userRepository.save(user);
                            return userMapper.userToUserDto(user);
                        }
                ).orElseThrow(() -> new NotFoundException("No user with id "+ id +" found."));
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

}
