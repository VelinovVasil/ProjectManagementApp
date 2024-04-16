package com.projectmanager.projectmanagementapp.services;


import com.projectmanager.projectmanagementapp.models.dtos.CreateTaskDTO;
import com.projectmanager.projectmanagementapp.models.dtos.TaskReturnDTO;
import com.projectmanager.projectmanagementapp.models.dtos.UpdateTaskDTO;
import com.projectmanager.projectmanagementapp.models.entities.Task;

import java.util.Optional;

public interface TaskService {

    TaskReturnDTO createTask(CreateTaskDTO createTaskDTO);

    String deleteTask(Long taskId);

    String updateTask(Long taskId, UpdateTaskDTO updateTaskDTO);

    TaskReturnDTO getTaskById(Long taskId);


}
