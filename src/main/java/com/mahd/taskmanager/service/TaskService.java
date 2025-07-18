package com.mahd.taskmanager.service;

import com.mahd.taskmanager.dto.TaskDTO;
import com.mahd.taskmanager.model.*;
import com.mahd.taskmanager.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserService userService;
    private final ProjectService projectService;

    public List<TaskDTO> getAllTasks() {
        return taskRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public TaskDTO getTaskById(Long id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
        return convertToDTO(task);
    }

    public TaskDTO createTask(TaskDTO dto) {
        Task task = new Task();
        updateEntityFromDTO(task, dto);
        return convertToDTO(taskRepository.save(task));
    }

    public TaskDTO updateTask(Long id, TaskDTO dto) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
        updateEntityFromDTO(task, dto);
        return convertToDTO(taskRepository.save(task));
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    public List<TaskDTO> getTasksByProject(Long projectId) {
        Project project = projectService.getById(projectId);
        return taskRepository.findByProject(project).stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public List<TaskDTO> getTasksByUser(Long userId) {
        User user = userService.getById(userId);
        return taskRepository.findByAssignedTo(user).stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public List<TaskDTO> getTasksByStatus(String status) {
        return taskRepository.findByStatus(Status.valueOf(status.toUpperCase()))
                .stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public List<TaskDTO> getTasksByPriority(String priority) {
        return taskRepository.findByPriority(Priority.valueOf(priority.toUpperCase()))
                .stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public Page<TaskDTO> getPaginatedTasks(int page, int size, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("asc") ?
                Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Task> taskPage = taskRepository.findAll(pageable);

        return taskPage.map(this::convertToDTO); // Page<TaskDTO>
    }

    // ======= Helpers ========
    private TaskDTO convertToDTO(Task task) {
        TaskDTO dto = new TaskDTO();
        dto.setId(task.getId());
        dto.setTitle(task.getTitle());
        dto.setDescription(task.getDescription());
        dto.setStatus(task.getStatus().name());
        dto.setPriority(task.getPriority().name());
        dto.setDueDate(task.getDueDate());
        dto.setAssignedToId(task.getAssignedTo().getId());
        dto.setProjectId(task.getProject().getId());
        return dto;
    }

    private void updateEntityFromDTO(Task task, TaskDTO dto) {
        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setStatus(Status.valueOf(dto.getStatus().toUpperCase()));
        task.setPriority(Priority.valueOf(dto.getPriority().toUpperCase()));
        task.setDueDate(dto.getDueDate());
        task.setAssignedTo(userService.getById(dto.getAssignedToId()));
        task.setProject(projectService.getById(dto.getProjectId()));
    }
}
