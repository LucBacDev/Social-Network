package com.doan.MyWechat.com.Controller;



import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.doan.MyWechat.com.Common.COMMON;
import com.doan.MyWechat.com.Entities.User;
import com.doan.MyWechat.com.Repositories.UserRepository;
import com.doan.MyWechat.com.Services.UserService;


@Controller
public class RegisterController {
	@Autowired
	UserService userService;
	@Autowired 
	UserRepository userRepo;
	
	public static int idTemp;
	
	@RequestMapping(value = "/register",method = RequestMethod.GET)
	public String index() {
		return "registerpage";
	}
	
	@RequestMapping(value = "/register",method = RequestMethod.POST)
	public ResponseEntity<String> registerAccount(@RequestBody User user,Model model) {
		COMMON status=userService.reigisterAccount(user.getEmail(),user.getPassword());
		if (status.equals(COMMON.REGISTER_STATUS_SUCESS)) {
			User userRegistred=userRepo.findTopUserByEmail(user.getEmail());
			idTemp=userRegistred.getId();
			return ResponseEntity.ok(userRegistred.getId()+"");
		}else if (status.equals(COMMON.REGISTER_STATUS_ERROR_DUPLICATE_MAIL)) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("error by dupplicate mail");
		}
		else return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("error");
	}
	
	@RequestMapping(value = "/addinfor/{id}",method = RequestMethod.GET)
	public String returnAddInforFrom(@PathVariable int id) {
		if(id!=idTemp) {
			return "errorpage";
		}
		return "addinforpage";
	}
	
	@RequestMapping(value = "/addinfor/{id}",method = RequestMethod.POST)
	public ResponseEntity<String> addInforUser(@PathVariable int id,
			@RequestParam String username,
			@RequestParam String gender,
			@RequestParam String birthday,
			@RequestParam String address,
			@RequestParam String address2,
			@RequestParam("avatar") MultipartFile avatar
			) {
		if(id!=idTemp) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("error_forbidden");
		}
		COMMON status=userService.reigisterInforUser(id,username,gender,birthday,address,address2,avatar);
		if (status.equals(COMMON.ADDINFOR_STATUS_SUCCESS)) {
			return ResponseEntity.ok("success");
		}else return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("error");
	}
}
