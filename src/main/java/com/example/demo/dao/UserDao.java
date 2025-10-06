package com.example.demo.dao;

import com.example.demo.model.User;
import java.util.*;

public interface UserDao {
    Optional<User> findByUsername(String username);
    User save(User u);
    List<User> findAll();
}
