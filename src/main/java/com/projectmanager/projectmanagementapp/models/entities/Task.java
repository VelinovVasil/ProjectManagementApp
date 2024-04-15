package com.projectmanager.projectmanagementapp.models.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "tasks")
@Getter
@Setter
public class Task extends BaseEntity {

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "assigned_on", nullable = false)
    private LocalDateTime assignedOn;

    @Column(nullable = false)
    private LocalDateTime deadline;

    @ManyToOne
    @JoinColumn(name = "task_admin_id", referencedColumnName = "id")
    private User taskAdmin;


    @ManyToMany
    @JoinTable(
            name = "tasks_users",
            joinColumns = @JoinColumn(name = "task_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> users;
}
