package com.mahd.taskmanager.repository;

import com.mahd.taskmanager.model.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    // Filtering by project
    List<Task> findByProject(Project project);

    // Filtering by assigned user
    List<Task> findByAssignedTo(User user);

    // Filtering by status
    List<Task> findByStatus(Status status);

    // Filtering by priority
    List<Task> findByPriority(Priority priority);
}
