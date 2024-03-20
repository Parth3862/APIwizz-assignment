package com.tm.TaskManagementSystem.Requests;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AddTaskRequest {
    private String title;
    private String descprition;
    private LocalDate dueDate;
}
