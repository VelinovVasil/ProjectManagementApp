package com.projectmanager.projectmanagementapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Task extends JpaRepository<Task, Long> {

}
