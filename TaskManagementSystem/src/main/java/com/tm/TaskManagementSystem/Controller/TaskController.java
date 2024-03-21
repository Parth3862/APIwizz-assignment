package com.tm.TaskManagementSystem.Controller;

import com.tm.TaskManagementSystem.Models.Task;
import com.tm.TaskManagementSystem.Repository.TaskRepository;
import com.tm.TaskManagementSystem.Requests.AddTaskRequest;
import com.tm.TaskManagementSystem.Requests.TaskUpdateRequest;
import com.tm.TaskManagementSystem.exceptions.TaskNotFoundException;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.tm.TaskManagementSystem.service.TaskService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;
    @Autowired
    private TaskRepository taskRepository;

    @GetMapping("/all")
    public ResponseEntity<List<Task>> getAllTasks(){
        List<Task> tasks = taskService.getAllTasks();
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<Task> getTaskById(@PathVariable("taskId") Long taskId){
        Optional<Task> task = taskService.getTask(taskId);
        if(task.isPresent()){
            return ResponseEntity.ok(task.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/getTaskList")
    public ResponseEntity<Page<Task>> getList(@RequestParam(defaultValue = "0") int page,
                                              @RequestParam(defaultValue = "10") int size) throws Exception {
        try {
            Page<Task> customerPage = taskRepository.findAll(PageRequest.of(page, size));
            return ResponseEntity.ok(customerPage);
        } catch (Exception e) {
            throw new Exception("Internal Server Issue");
        }
    }
    @PostMapping("/add")
    public ResponseEntity<Task> addTask(@RequestBody AddTaskRequest addTaskRequest){
        Task addedTask = taskService.addTask(addTaskRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedTask);
    }

    @PutMapping("/update/{taskId}")
    public ResponseEntity<Task> updateTask(@PathVariable("taskId") Integer taskId, @Valid @RequestBody TaskUpdateRequest taskUpdateRequest) {
        try {
            Task updatedTask = taskService.updateTask(taskId, taskUpdateRequest);
            if (updatedTask != null) {
                return ResponseEntity.ok(updatedTask);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/delete/{taskId}")
    public ResponseEntity<String> deleteTask(@PathVariable("taskId") Integer taskId) {
        try {
            taskService.deleteTask(taskId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Task with ID " + taskId + " has been deleted.");
        } catch (TaskNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task with ID " + taskId + " does not exist.");
        }
    }
}
