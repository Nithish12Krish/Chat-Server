package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.ChatMessage;
import com.example.demo.model.RegisterBean;
import com.example.demo.repository.UserRepository;


@Controller
public class ChatController {
	
	@Autowired
	private UserRepository repo;
	
	@GetMapping("/")
	public String viewHomePage() {
		return "Home";
	}
	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
		model.addAttribute("user", new RegisterBean());
		
		return "Register";
	}
	
	@PostMapping("/process_register")
	public String processRegister(RegisterBean user) {
		BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
		String encodedPassword=passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		repo.save(user);
		
		return "register_success";
	}
	
	 @GetMapping("/users")
	 public String listUsers(Model model) {
	      List<RegisterBean> listUsers = repo.findAll();
	      model.addAttribute("listUsers", listUsers);
	        
	        return "users";
	    }
	@GetMapping("/index")
	@MessageMapping("/chat.sendMessage")
	@SendTo("/topic/public")
	public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
		return chatMessage;
	}
	
	@MessageMapping("/chat.addUser")
	@SendTo("/topic/public")
	public ChatMessage addUser(@Payload ChatMessage chatMessage,SimpMessageHeaderAccessor headerAccessor) {
		//Add username in Web socket session
		headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
		return chatMessage;
	}
}
