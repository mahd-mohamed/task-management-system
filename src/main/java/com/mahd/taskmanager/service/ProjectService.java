package com.mahd.taskmanager.service;

import com.mahd.taskmanager.dto.ProjectDTO;
import com.mahd.taskmanager.model.Project;
import com.mahd.taskmanager.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;

    public List<ProjectDTO> getAllProjects() {
        return projectRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public ProjectDTO createProject(ProjectDTO dto) {
        Project project = Project.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .build();
        return convertToDTO(projectRepository.save(project));
    }

    public ProjectDTO updateProject(Long id, ProjectDTO dto) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found"));
        updateEntityFromDTO(project, dto);
        return convertToDTO(projectRepository.save(project));
    }

    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }

    public Project getById(Long id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found"));
    }

    // ===== Helper Methods =====

    public ProjectDTO convertToDTO(Project project)
    {
        ProjectDTO dto = new ProjectDTO();
        dto.setId(project.getId());
        dto.setTitle(project.getTitle());
        dto.setDescription(project.getDescription());
        return dto;
    }

    private void updateEntityFromDTO(Project project, ProjectDTO dto) {
        project.setTitle(dto.getTitle());
        project.setDescription(dto.getDescription());
    }
}
