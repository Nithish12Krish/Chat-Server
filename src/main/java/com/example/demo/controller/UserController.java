package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.RegisterBean;
import com.example.demo.service.UserService;

@RestController
public class UserController {

	@Autowired(required=true)
	private UserService service;
	
	@PostMapping("/users1")
	public RegisterBean addUser(@RequestBody RegisterBean user) {
		return service.saveUser(user);
	}
	
	@PostMapping("/addUsers")
	public List<RegisterBean> addUsers(@RequestBody List<RegisterBean> users){
		return service.saveUsers(users);
		}
	
	@GetMapping("/users1")
	public List<RegisterBean> findAllUsers(){
		return service.getUsers();
	}
	
	@GetMapping("/userById/{id}")
	public RegisterBean findUserById(@PathVariable Long id) {
		return service.getUserById(id);
	}
	@PutMapping("/update")
	public RegisterBean updateUser(@RequestBody RegisterBean user) {
	        return service.updateUser(user);
	    }

	 @DeleteMapping("/delete/{id}")
	 public String deleteUser(@PathVariable Long id) {
	        return service.deleteUser(id);
	    }
	
	
}
