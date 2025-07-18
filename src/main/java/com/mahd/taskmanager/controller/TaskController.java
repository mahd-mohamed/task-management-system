package com.mahd.taskmanager.controller;

import com.mahd.taskmanager.dto.TaskDTO;
import com.mahd.taskmanager.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
@CrossOrigin
public class TaskController {

    private final TaskService taskService;

    // Get all tasks
    @GetMapping
    public List<TaskDTO> getAllTasks() {
        return taskService.getAllTasks();
    }

    // Get single task by ID
    @GetMapping("/{id}")
    public TaskDTO getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }

    // Create task
    @PostMapping
    public TaskDTO createTask(@RequestBody @Valid TaskDTO dto) {
        return taskService.createTask(dto);
    }

    // Update task
    @PutMapping("/{id}")
    public TaskDTO updateTask(@PathVariable Long id, @RequestBody TaskDTO dto) {
        return taskService.updateTask(id, dto);
    }

    // Delete task
    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }

    // Get tasks by project
    @GetMapping("/project/{projectId}")
    public List<TaskDTO> getTasksByProject(@PathVariable Long projectId) {
        return taskService.getTasksByProject(projectId);
    }

    // Get tasks by user
    @GetMapping("/user/{userId}")
    public List<TaskDTO> getTasksByUser(@PathVariable Long userId) {
        return taskService.getTasksByUser(userId);
    }

    // Get tasks by status
    @GetMapping("/status/{status}")
    public List<TaskDTO> getTasksByStatus(@PathVariable String status) {
        return taskService.getTasksByStatus(status);
    }

    // Get tasks by priority
    @GetMapping("/priority/{priority}")
    public List<TaskDTO> getTasksByPriority(@PathVariable String priority) {
        return taskService.getTasksByPriority(priority);
    }

    // Paginated and sorted tasks
    @GetMapping("/paginated")
    public Page<TaskDTO> getPaginatedTasks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir
    ) {
        return taskService.getPaginatedTasks(page, size, sortBy, sortDir);
    }
}
