package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.RegisterBean;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repo;

	//post Method
	public RegisterBean saveUser(RegisterBean user) {
		return repo.save(user);
		}
	
	public List<RegisterBean> saveUsers(List<RegisterBean> users){
		return repo.saveAll(users);
	}
	
	public List<RegisterBean> getUsers() {
        return repo.findAll();
    }

public RegisterBean getUserById(Long id) {
	return repo.findById(id).orElse(null);
}

//delete method

public String deleteUser(Long id) {
	repo.deleteById(id);
	return "User deleted" +id;
}

public RegisterBean updateUser(RegisterBean user) {
	RegisterBean existingUser =repo.findById(user.getId()).orElse(null);
	existingUser.setFullName(user.getFullName());
	existingUser.setEmail(user.getEmail());
	existingUser.setUserName(user.getUserName());
	existingUser.setPassword(user.getPassword());
	return repo.save(existingUser);
}
}
