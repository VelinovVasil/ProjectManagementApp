package com.projectmanager.projectmanagementapp.controllers;

import com.projectmanager.projectmanagementapp.models.dtos.CreateTaskDTO;
import com.projectmanager.projectmanagementapp.models.dtos.TaskReturnDTO;
import com.projectmanager.projectmanagementapp.models.dtos.UpdateTaskDTO;
import com.projectmanager.projectmanagementapp.services.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("tasks")
@AllArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping("/")
    public ResponseEntity<TaskReturnDTO> createTask(@RequestBody CreateTaskDTO createTaskDTO) {
        return new ResponseEntity<>(this.taskService.createTask(createTaskDTO), HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable Long id) {
        return new ResponseEntity<>(this.taskService.deleteTask(id), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskReturnDTO> getTaskById(@PathVariable Long id) {
        return new ResponseEntity<>(this.taskService.getTaskById(id), HttpStatus.OK);

    }

    @PutMapping("update/{id}")
    public ResponseEntity<String> updateTask(@PathVariable Long id, @RequestBody UpdateTaskDTO updateTaskDTO) {
        return new ResponseEntity<>(this.taskService.updateTask(id, updateTaskDTO), HttpStatus.OK);
    }
}
