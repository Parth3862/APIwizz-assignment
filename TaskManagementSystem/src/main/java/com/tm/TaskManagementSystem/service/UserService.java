
package com.tm.TaskManagementSystem.service;
import com.tm.TaskManagementSystem.Models.User;
import com.tm.TaskManagementSystem.Repository.UserRepository;
import com.tm.TaskManagementSystem.Requests.UserDto;
import com.tm.TaskManagementSystem.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User createUser(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRoles(userDto.getRoles());
        return userRepository.save(user);
    }

    public User updateUser(Integer userId, UserDto userDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(ResourceNotFoundException::new);

        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRoles(userDto.getRoles());
        return userRepository.save(user);
    }

    public void deleteUser(Integer userId) {
        userRepository.deleteById(userId);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Integer userId) {
        return userRepository.findById(userId)
                .orElseThrow(ResourceNotFoundException::new);
    }
}
