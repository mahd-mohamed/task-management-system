package com.mahd.taskmanager.controller;

import com.mahd.taskmanager.dto.UserDTO;
import com.mahd.taskmanager.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@CrossOrigin
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping
    public UserDTO createUser(@RequestBody @Valid UserDTO dto) {
        return userService.createUser(dto);
    }

    @PutMapping("/{id}")
    public UserDTO updateUser(@PathVariable Long id, @RequestBody @Valid UserDTO dto) {
        return userService.updateUser(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}

