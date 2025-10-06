package com.example.demo.controller;

import com.example.demo.dao.UserDao;
import com.example.demo.exception.UserAlreadyExistsException;
import com.example.demo.model.User;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserDao userDao;
    private final BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();

    public UserController(UserDao userDao) {
        this.userDao = userDao;
    }

    @GetMapping
    public List<User> getAll() {
        return userDao.findAll();
    }

    record CreateUserReq(@NotBlank String username, @NotBlank String password, String role) {}

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@Valid @RequestBody CreateUserReq req) {
        User user = new User();
        user.setUsername(req.username());
        user.setPasswordHash(bCrypt.encode(req.password()));
        user.setRole(req.role() == null ? "PUBLISHER" : req.role().toUpperCase());
        try {
            return userDao.save(user);
        } catch (DataIntegrityViolationException e) {
            throw new UserAlreadyExistsException(user.getUsername());
        }
    }
}
