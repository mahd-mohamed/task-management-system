package com.mahd.taskmanager.repository;

import com.mahd.taskmanager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);

}
