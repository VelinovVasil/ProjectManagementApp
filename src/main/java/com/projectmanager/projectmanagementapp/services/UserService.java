package com.projectmanager.projectmanagementapp.services;

import com.projectmanager.projectmanagementapp.models.dtos.CreateUserDTO;
import com.projectmanager.projectmanagementapp.models.dtos.UpdateUserDTO;
import com.projectmanager.projectmanagementapp.models.dtos.UserDTO;
import com.projectmanager.projectmanagementapp.models.entities.User;

import java.util.Optional;

public interface UserService {

    UserDTO createUser(CreateUserDTO createUserDTO);

    String deleteUser(Long userId);

    String updateUserInfo(UpdateUserDTO updateUserDTO);

    Optional<User> getUserById(Long userId);

    String assignTask(Long userId, Long taskId);

    String unassignTask(Long userId, Long taskId);
}
