package com.projectmanager.projectmanagementapp.repositories;

import com.projectmanager.projectmanagementapp.models.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

}
