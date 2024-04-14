package com.projectmanager.projectmanagementapp.repositories;

import com.projectmanager.projectmanagementapp.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
