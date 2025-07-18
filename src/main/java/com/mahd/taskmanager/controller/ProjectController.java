package com.mahd.taskmanager.controller;

import com.mahd.taskmanager.dto.ProjectDTO;
import com.mahd.taskmanager.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/projects")
@RequiredArgsConstructor
@CrossOrigin
public class ProjectController {

    private final ProjectService projectService;

    @GetMapping
    public List<ProjectDTO> getAllProjects() {
        return projectService.getAllProjects();
    }

    @PostMapping
    public ProjectDTO createProject(@RequestBody @Valid ProjectDTO dto) {
        return projectService.createProject(dto);
    }

    @PutMapping("/{id}")
    public ProjectDTO updateProject(@PathVariable Long id, @RequestBody @Valid ProjectDTO dto) {
        return projectService.updateProject(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
    }
}


