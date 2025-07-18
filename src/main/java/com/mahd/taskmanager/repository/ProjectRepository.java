package com.mahd.taskmanager.repository;
import com.mahd.taskmanager.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
