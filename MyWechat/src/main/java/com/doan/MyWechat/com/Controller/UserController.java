package com.doan.MyWechat.com.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.doan.MyWechat.com.Entities.User;
import com.doan.MyWechat.com.Services.UserService;

@Controller
public class UserController {

	@Autowired
	UserService userService;
	
	@MessageMapping("/user.addUser")
	@SendTo("/user/topic")
	public User addUser(@Payload User user) {
		userService.saveUser(user);
		return user;
	}
	
	@MessageMapping("/user.disconnectUser")
	@SendTo("/user/topic")
	public User disconnect(@Payload User user) {
		userService.disconnect(user);
		return user;
	}
	
	@GetMapping("/users")
	public ResponseEntity<List<User>> findConnectUsers(){
		return ResponseEntity.ok(userService.findConnectUsers());
	}
	
}
