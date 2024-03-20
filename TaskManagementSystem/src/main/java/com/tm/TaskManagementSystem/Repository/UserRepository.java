package com.tm.TaskManagementSystem.Repository;

import com.tm.TaskManagementSystem.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
    User findByUsername(String username);
}
