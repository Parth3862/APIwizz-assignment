package com.tm.TaskManagementSystem.Requests;

import com.tm.TaskManagementSystem.enums.TaskStatus;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TaskUpdateRequest {
    private String title;
    private String description;
    private LocalDate dueDate;
    private TaskStatus taskStatus;
}
