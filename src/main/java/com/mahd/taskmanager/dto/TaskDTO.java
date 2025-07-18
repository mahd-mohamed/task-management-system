package com.mahd.taskmanager.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TaskDTO {

    private Long id;

    @NotBlank(message = "Title is required")
    private String title;

    private String description;

    @NotNull(message = "Status is required")
    private String status;  //

    @NotNull(message = "Priority is required")
    private String priority;

    @FutureOrPresent(message = "Due date must be today or in the future")
    private LocalDate dueDate;


    @NotNull(message = "Assigned user is required")
    private Long assignedToId;

    @NotNull(message = "Project ID is required")
    private Long projectId;
}

