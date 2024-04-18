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

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import static com.projectmanager.projectmanagementapp.util.RepositoryUtil.findById;

// TODO: Add error handling

@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {

    private TaskRepository taskRepository;

    private UserRepository userRepository;

    private ModelMapper modelMapper;

    @Override
    public TaskReturnDTO createTask(CreateTaskDTO createTaskDTO) {

        User user = findById(this.userRepository, createTaskDTO.getTaskAdmin());

        Task task = this.modelMapper.map(createTaskDTO, Task.class);
        task.setAssignedOn(LocalDateTime.now());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        task.setDeadline(LocalDateTime.parse(createTaskDTO.getDeadline(), formatter));

        task.setTaskAdmin(user);

        this.taskRepository.saveAndFlush(task);

        TaskReturnDTO taskReturnDTO = this.modelMapper.map(task, TaskReturnDTO.class);
        taskReturnDTO.setTaskAdminId(createTaskDTO.getTaskAdmin());

        return taskReturnDTO;
    }

    @Override
    public String deleteTask(Long taskId) {

        Task task = findById(this.taskRepository, taskId);

        this.taskRepository.deleteById(taskId);

        return "Task deleted successfully";
    }

    @Override
    public String updateTask(Long taskId, UpdateTaskDTO updateTaskDTO) {

        // TODO: Add method implementation

        return null;
    }

    @Override
    public TaskReturnDTO getTaskById(Long taskId) {

        Task optionalTask = findById(this.taskRepository, taskId);

        return this.modelMapper.map(optionalTask, TaskReturnDTO.class);
     }
}
