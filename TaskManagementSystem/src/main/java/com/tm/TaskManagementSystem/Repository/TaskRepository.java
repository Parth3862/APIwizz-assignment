package com.tm.TaskManagementSystem.Repository;

import com.tm.TaskManagementSystem.Models.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task,Integer> {


}
