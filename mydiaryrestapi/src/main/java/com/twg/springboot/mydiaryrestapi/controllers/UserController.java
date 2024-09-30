package com.twg.springboot.mydiaryrestapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.twg.springboot.mydiaryrestapi.entities.User;
import com.twg.springboot.mydiaryrestapi.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/")
	public List<User> getAllUsers()
	{
		List<User> users=userService.findAll();
		return users;
		
	}
	
	@PostMapping("/")
	public User insertUser(@RequestBody User user)
	{
		User user1 = userService.saveUser(user);
		return user1;
	}
	
	@PutMapping("/")
	public User updateUser(@RequestBody User user)
	{
		User user1 = userService.updateUser(user);
		return user1;
	}
	
	@GetMapping("/{id}")
	public User getUser(@PathVariable("id") int id)
	{
		User user=userService.findById(id);
		return user;
	}
	
	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable("id") int id)
	{
		User user=userService.findById(id);
		userService.deleteUser(user);
	}
	
	

}
