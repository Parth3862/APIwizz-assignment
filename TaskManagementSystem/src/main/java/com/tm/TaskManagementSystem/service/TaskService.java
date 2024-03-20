package com.tm.TaskManagementSystem.service;

import com.tm.TaskManagementSystem.Repository.TaskRepository;
import com.tm.TaskManagementSystem.Models.Task;
import com.tm.TaskManagementSystem.Requests.AddTaskRequest;
import com.tm.TaskManagementSystem.Requests.TaskUpdateRequest;
import com.tm.TaskManagementSystem.enums.TaskStatus;
import com.tm.TaskManagementSystem.exceptions.TaskNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public List<Task> getAllTasks(){
        return taskRepository.findAll();
    }

    //Retrieve a task by their Id
    public Optional<Task> getTask(Long taskId){
        return taskRepository.findById(Math.toIntExact(taskId));
    }


    public Task addTask(AddTaskRequest addTaskRequest){
        Task task = Task.builder().title(addTaskRequest.getTitle())
                    .description(addTaskRequest.getDescprition())
                    .dueDate(addTaskRequest.getDueDate())
                    .taskStatus(TaskStatus.TODO)
                    .build();
        return taskRepository.save(task);
    }

    //to update the existing task
    public Task updateTask(Integer taskId, @Valid TaskUpdateRequest taskUpdateRequest) throws Exception {
        Optional<Task> taskOptional = taskRepository.findById(taskId);
        if (taskOptional.isEmpty()) {
            throw new Exception("Task Id is Incorrect");
        } else {
            Task existingTask = taskOptional.get();
            // Update the existing task with the fields from the update request
            if (taskUpdateRequest.getTitle() != null) {
                existingTask.setTitle(taskUpdateRequest.getTitle());
            }
            if (taskUpdateRequest.getDescription() != null) {
                existingTask.setDescription(taskUpdateRequest.getDescription());
            }
            if (taskUpdateRequest.getDueDate() != null) {
                existingTask.setDueDate(taskUpdateRequest.getDueDate());
            }
            if (taskUpdateRequest.getTaskStatus() != null) {
                existingTask.setTaskStatus(taskUpdateRequest.getTaskStatus());
            }
            // Save the updated task
            return taskRepository.save(existingTask);
        }
    }

    //delete a task by Id
    public void deleteTask(Integer taskId) {
        if(taskRepository.existsById(taskId)){
            taskRepository.deleteById(taskId);
        } else {
            throw new TaskNotFoundException("Task with ID " + taskId + " does not exist.");
        }
    }
}
