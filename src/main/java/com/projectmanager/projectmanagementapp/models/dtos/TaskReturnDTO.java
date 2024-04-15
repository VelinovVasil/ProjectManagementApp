package com.projectmanager.projectmanagementapp.models.dtos;

import com.projectmanager.projectmanagementapp.models.entities.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
public class TaskReturnDTO {

    private String title;

    private String description;

    private LocalDateTime assignedOn;

    private LocalDateTime deadline;

    private User taskAdmin;

    private Set<Long> users;

}
