package com.projectmanager.projectmanagementapp.repositories;

import com.projectmanager.projectmanagementapp.models.entities.Submission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubmissionRepository extends JpaRepository<Submission, Long> {


}
