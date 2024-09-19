package com.example.parkingmanagement.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.parkingmanagement.model.User;
import com.example.parkingmanagement.service.UserService;


@RestController
@RequestMapping("/api/users")
@CrossOrigin("*")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/{userId}")
	public User getUserById(@PathVariable Long userId) {
		return userService.getUserById(userId);
	}

	@PutMapping("/{userId}")
	public User updateUser(@PathVariable Long userId, @RequestBody User user) {
		return userService.updateUser(userId, user);
	}

	@PostMapping
	public User createUser(@RequestBody User user) {
		return userService.saveUser(user);
	}

	@DeleteMapping("/{userId}")
	public void deleteUser(@PathVariable Long userId) {
		userService.deleteUser(userId);
	}

	@PostMapping("/login")
	public User loginUser(@RequestBody User loginRequest) {
		return userService.authenticateUser(loginRequest.getUsername(), loginRequest.getPassword());
	}
	
	
	
	
	
	

}
