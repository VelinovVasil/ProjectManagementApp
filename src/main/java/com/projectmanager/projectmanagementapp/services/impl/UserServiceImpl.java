package com.projectmanager.projectmanagementapp.services.impl;

import com.projectmanager.projectmanagementapp.models.dtos.CreateUserDTO;
import com.projectmanager.projectmanagementapp.models.dtos.UpdateUserDTO;
import com.projectmanager.projectmanagementapp.models.dtos.UserDTO;
import com.projectmanager.projectmanagementapp.models.entities.Task;
import com.projectmanager.projectmanagementapp.models.entities.User;
import com.projectmanager.projectmanagementapp.repositories.TaskRepository;
import com.projectmanager.projectmanagementapp.repositories.UserRepository;
import com.projectmanager.projectmanagementapp.services.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

// TODO: Add error handling

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final TaskRepository taskRepository;

    private final ModelMapper modelMapper;
/*
* controller
 * mapper
 * facade
* service 1
* service 2
* repository
* */
    @Override
    public UserDTO createUser(CreateUserDTO createUserDTO) {

        Optional<User> userByUsername = this.userRepository.findUserByUsername(createUserDTO.getUsername());
        if (userByUsername.isPresent()) {
//            return "Username already exists";
        }

        Optional<User> userByEmail = this.userRepository.findUserByEmail(createUserDTO.getEmail());
        if (userByEmail.isPresent()) {
//            return "Email already exists";
        }

        User user = this.modelMapper.map(createUserDTO, User.class);

        this.userRepository.saveAndFlush(user);

        return String.format("User %s, %s created successfully", user.getUsername(), user.getEmail());
    }

    @Override
    public String deleteUser(Long userId) {

        Optional<User> optional = this.userRepository.findById(userId);

        if (optional.isEmpty()) {
//            return String.format("User with id %s not found", userId);
        }

        this.userRepository.deleteById(userId);

        return "User deleted successfully";
    }

    @Override
    public String updateUserInfo(UpdateUserDTO updateUserDTO) {
        Optional<User> userByUsername = this.userRepository.findUserByUsername(updateUserDTO.getUsername());
        if (userByUsername.isPresent()) {
//            return "Username already exists";
        }

        Optional<User> userByEmail = this.userRepository.findUserByEmail(updateUserDTO.getEmail());
        if (userByEmail.isPresent()) {
//            return "Email already exists";
        }

        User user = this.modelMapper.map(updateUserDTO, User.class);

        this.userRepository.saveAndFlush(user);

        return String.format("User %s, %s updated successfully", user.getUsername(), user.getEmail());
    }

    @Override
    public Optional<User> getUserById(Long userId) {
        return this.userRepository.findById(userId);
    }

    @Override
    public String assignTask(Long userId, Long taskId) {

        Optional<User> optionalUser = this.userRepository.findById(userId);
        if (optionalUser.isEmpty()) {
//            return "User not found";
        }

        Optional<Task> optionalTask = this.taskRepository.findById(taskId);
        if (optionalTask.isEmpty()) {
//            return "Task not found";
        }

        User user = optionalUser.get();
        Task task = optionalTask.get();

        if(user.getTasks().add(task)) {
            this.userRepository.saveAndFlush(user);
            return "Task assigned successfully";
        }

        return null;
    }

    @Override
    public String unassignTask(Long userId, Long taskId) {

        Optional<User> optionalUser = this.userRepository.findById(userId);
        if (optionalUser.isEmpty()) {
//            return "User not found";
        }

        Optional<Task> optionalTask = this.taskRepository.findById(taskId);
        if (optionalTask.isEmpty()) {
//            return "Task not found";
        }

        User user = optionalUser.get();
        Task task = optionalTask.get();

        if(user.getTasks().remove(task)) {
            this.userRepository.saveAndFlush(user);
            return "Task assigned successfully";
        }

        return null;
    }
}
