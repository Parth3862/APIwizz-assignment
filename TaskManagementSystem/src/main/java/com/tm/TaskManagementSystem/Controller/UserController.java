package com.tm.TaskManagementSystem.Controller;

import com.tm.TaskManagementSystem.Models.User;
import com.tm.TaskManagementSystem.Requests.UserDto;
import com.tm.TaskManagementSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable("userId") Integer userId) {
        User user = userService.getUserById(userId);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody UserDto userDto) {
        User user = userService.createUser(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable("userId") Integer userId, @RequestBody UserDto userDto) {
        User updatedUser = userService.updateUser(userId, userDto);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable("userId") Integer userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }
}
