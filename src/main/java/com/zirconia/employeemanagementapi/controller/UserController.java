package com.zirconia.employeemanagementapi.controller;

import com.zirconia.employeemanagementapi.dao.UserRepository;
import com.zirconia.employeemanagementapi.entities.User;
import com.zirconia.employeemanagementapi.pojo.UserCredentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
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

    @GetMapping("/get/username/{name}")
    public ResponseEntity getUserDetailsByUsername(@PathVariable String name){
        User user = userRepository.getUserDetailsByUsername(name);
        if(user != null){
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/get/users")
    public ResponseEntity getUserDetailsByUsername(){
        List<User> userList = userRepository.getAllUsers();
        if(userList != null){
            return new ResponseEntity<>(userList, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/authenticate")
    public ResponseEntity authenticateUser(@RequestBody UserCredentials userCredentials){
        User user = userRepository.authenticateUser(userCredentials.getUsername(), userCredentials.getPassword());
        if(user != null){
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping("/add")
    public User addUser(@RequestBody User user){
        User addedUser  = userRepository.saveAndFlush(user);
        return addedUser;
    }

    @ResponseStatus(code = HttpStatus.OK)
    @PutMapping("/update")
    public void updateUser(@RequestBody User user){
        userRepository.saveAndFlush(user);
    }

    @ResponseStatus(code = HttpStatus.OK)
    @PutMapping("/update/users")
    public void updateUsers(@RequestBody List<User> users){
        userRepository.saveAllAndFlush(users);
    }

    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @DeleteMapping("/delete")
    public void deleteUser(@RequestParam int userId){
        userRepository.deleteById(userId);
    }

}
