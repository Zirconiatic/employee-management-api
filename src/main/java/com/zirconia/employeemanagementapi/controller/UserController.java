package com.zirconia.employeemanagementapi.controller;

import com.zirconia.employeemanagementapi.dao.UserRepository;
import com.zirconia.employeemanagementapi.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @ResponseStatus(code= HttpStatus.OK)
    @GetMapping("/all")
    public List<User> getAllRegisteredUsers(){
        return userRepository.findAll();
    }

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping("/add")
    public void addUser(@RequestBody User user){
        userRepository.save(user);
    }

    @ResponseStatus(code = HttpStatus.OK)
    @PutMapping("/update")
    public void updateUser(@RequestBody User user){
        userRepository.save(user);
    }

    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @DeleteMapping("/delete")
    public void deleteUser(@RequestParam int userId){
        userRepository.deleteById(userId);
    }

}
