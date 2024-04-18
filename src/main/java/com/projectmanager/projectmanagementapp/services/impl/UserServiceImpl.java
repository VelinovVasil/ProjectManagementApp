package com.projectmanager.projectmanagementapp.services.impl;

import com.projectmanager.projectmanagementapp.handler.exceptions.EntityNotFoundException;
import com.projectmanager.projectmanagementapp.handler.exceptions.TaskAlreadyAssignedToUserException;
import com.projectmanager.projectmanagementapp.handler.exceptions.UserAlreadyExistsException;
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


import static com.projectmanager.projectmanagementapp.util.RepositoryUtil.findById;


@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final TaskRepository taskRepository;

    private final ModelMapper modelMapper;


    @Override
    public UserDTO createUser(CreateUserDTO createUserDTO) {

        if (this.userRepository.existsByEmailOrUsername(createUserDTO.getEmail(), createUserDTO.getUsername())) {
            throw new UserAlreadyExistsException("User with email or username already exists");
        }

        User user = this.modelMapper.map(createUserDTO, User.class);

        this.userRepository.saveAndFlush(user);

        UserDTO dto = this.modelMapper.map(user, UserDTO.class);

        return dto;
    }

    @Override
    public String deleteUser(Long userId) {

        this.userRepository.deleteById(userId);

        return "User deleted successfully";
    }

    @Override
    public String updateUserInfo(Long userId, UpdateUserDTO updateUserDTO) {

        User user = findById(this.userRepository, userId);

        if (this.userRepository.existsByEmailOrUsername(updateUserDTO.getEmail(), updateUserDTO.getUsername())) {
            throw new UserAlreadyExistsException("User with email or username already exists");
        }

        user.setUsername(updateUserDTO.getUsername());
        user.setEmail(updateUserDTO.getEmail());
        user.setPassword(updateUserDTO.getPassword());

        this.userRepository.saveAndFlush(user);

        return String.format("User %s, %s updated successfully", user.getUsername(), user.getEmail());
    }

    @Override
    public User getUserById(Long userId) {
        return findById(this.userRepository, userId);
    }

    @Override
    public String assignTask(Long userId, Long taskId) {

        User user = findById(this.userRepository, userId);
        Task task = findById(this.taskRepository, taskId);


            if (!user.getTasks().contains(task)) {

//                user.getTasks().add(task);
                task.getUsers().add(user);

//                this.userRepository.saveAndFlush(user);
                this.taskRepository.saveAndFlush(task);
                return "Task assigned successfully";

            } else {
                throw new TaskAlreadyAssignedToUserException("Task is already assigned to the user");
            }

    }
    @Override
    public String unassignTask(Long userId, Long taskId) {

        User user = findById(this.userRepository, userId);
        Task task = findById(this.taskRepository, taskId);

        task.getUsers().remove(user);

        this.taskRepository.saveAndFlush(task);

        return "Task unassigned successfully";
    }
}
