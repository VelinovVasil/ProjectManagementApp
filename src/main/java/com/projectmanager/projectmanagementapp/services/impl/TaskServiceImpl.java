package com.projectmanager.projectmanagementapp.services.impl;

import com.projectmanager.projectmanagementapp.models.dtos.CreateTaskDTO;
import com.projectmanager.projectmanagementapp.models.dtos.TaskReturnDTO;
import com.projectmanager.projectmanagementapp.models.dtos.UpdateTaskDTO;
import com.projectmanager.projectmanagementapp.models.entities.Task;
import com.projectmanager.projectmanagementapp.models.entities.User;
import com.projectmanager.projectmanagementapp.repositories.TaskRepository;
import com.projectmanager.projectmanagementapp.repositories.UserRepository;
import com.projectmanager.projectmanagementapp.services.TaskService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

// TODO: Add error handling

@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {

    private TaskRepository taskRepository;

    private UserRepository userRepository;

    private ModelMapper modelMapper;

    @Override
    public String createTask(CreateTaskDTO createTaskDTO) {

        Optional<User> optionalUser = this.userRepository.findById(createTaskDTO.getTaskAdmin());
        if (optionalUser.isEmpty()) {
//            return "User does not exist";
        }

        Task task = this.modelMapper.map(createTaskDTO, Task.class);
        task.setTaskAdmin(optionalUser.get());

        this.taskRepository.saveAndFlush(task);

        return "Task created successfully";
    }

    @Override
    public String deleteTask(Long taskId) {
        Optional<Task> task = this.taskRepository.findById(taskId);

        if (task.isEmpty()) {
//            return "Task does not exist";
        }

        this.taskRepository.deleteById(taskId);

        return "Task deleted successfully";
    }

    @Override
    public String updateTask(UpdateTaskDTO updateTaskDTO) {

        // TODO

        return null;
    }

    @Override
    public TaskReturnDTO getTaskById(Long taskId) {

        Task optionalTask = this.taskRepository.findById(taskId).orElse(null);

        return this.modelMapper.map(optionalTask, TaskReturnDTO.class);
     }
}
