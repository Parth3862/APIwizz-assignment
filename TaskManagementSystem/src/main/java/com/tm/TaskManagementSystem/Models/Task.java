package com.tm.TaskManagementSystem.Models;

import com.tm.TaskManagementSystem.enums.TaskStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name="tasktable")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private LocalDate dueDate;

    @Enumerated(value= EnumType.STRING)
    @Column(nullable = false)
    private TaskStatus taskStatus;

}
