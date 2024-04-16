package com.projectmanager.projectmanagementapp.controllers;

import com.projectmanager.projectmanagementapp.models.dtos.CreateUserDTO;
import com.projectmanager.projectmanagementapp.models.dtos.UpdateUserDTO;
import com.projectmanager.projectmanagementapp.models.dtos.UserDTO;
import com.projectmanager.projectmanagementapp.models.entities.User;
import com.projectmanager.projectmanagementapp.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/")
    public ResponseEntity<UserDTO> createUser(@RequestBody CreateUserDTO createUserDTO) {
        UserDTO user = this.userService.createUser(createUserDTO);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        return new ResponseEntity<>(this.userService.deleteUser(id), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody UpdateUserDTO updateUserDTO) {
        return new ResponseEntity<>(this.userService.updateUserInfo(id, updateUserDTO), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return new ResponseEntity<>(this.userService.getUserById(id).orElse(null), HttpStatus.OK);
    }

    @PostMapping("/assign/{userId}/{taskId}")
    public ResponseEntity<String> assignTask(@PathVariable Long userId, @PathVariable Long taskId) {
        return new ResponseEntity<>(this.userService.assignTask(userId, taskId), HttpStatus.OK);
    }

    @PostMapping("/unassign/{userId}/{taskId}")
    public ResponseEntity<String> unassignTask(@PathVariable Long userId, @PathVariable Long taskId) {
        return new ResponseEntity<>(this.userService.unassignTask(userId, taskId), HttpStatus.OK);
    }
}
