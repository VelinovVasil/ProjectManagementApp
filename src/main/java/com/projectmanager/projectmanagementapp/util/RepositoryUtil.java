package com.projectmanager.projectmanagementapp.util;

import com.projectmanager.projectmanagementapp.handler.exceptions.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;

@RequiredArgsConstructor
public class RepositoryUtil {
    public static <T, ID> T findById(JpaRepository<T, ID> repository, ID id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Entity not found with id: " + id));
    }
}
