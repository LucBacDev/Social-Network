package com.doan.MyWechat.com.Controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.doan.MyWechat.com.Common.COMMON;
import com.doan.MyWechat.com.Entities.Report;
import com.doan.MyWechat.com.Entities.Role;
import com.doan.MyWechat.com.Repositories.ReporterRepository;
import com.doan.MyWechat.com.Repositories.RoleRepository;
import com.doan.MyWechat.com.Services.UserService;

@Controller
public class LoginController {
	public static int idUserLogin;
	@Autowired
	UserService userService;
	
	@Autowired
	RoleRepository roleRepo;
	
	@Autowired
	ReporterRepository reportRepo;
	
	@RequestMapping(value = "/login",method = RequestMethod.GET)
	public String index() {
		return "loginpage";
	}
	@RequestMapping(value = "/login",method = RequestMethod.POST)
	public ResponseEntity<String> login(HttpServletRequest request,@RequestParam String email,@RequestParam String password) {
		COMMON status=userService.login(request,email,password);
		if(status.equals(COMMON.LOGIN_SUCCESS)) {
			
			return ResponseEntity.ok("success");
		}else if(status.equals(COMMON.LOGIN_BY_NEW_EMAIL)) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("login by new email");
		}else if(status.equals(COMMON.LOGIN_ERROR_INVALID_PASSWORD)){
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("wrong password");
		}else if(status.equals(COMMON.LOGIN_BY_ROLE_ADMIN)) {
			return ResponseEntity.ok("admin login");
		}else if(status.equals(COMMON.LOGIN_BY_ROLE_BLOCK)){
			return ResponseEntity.ok("blockuser login");
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("error");
	}
	
	@RequestMapping(value = "/admin",method = RequestMethod.GET)
	public String indexAdmin(Model model) {
		Role role = roleRepo.findRoleByUserId(idUserLogin);
		if(role!=null) {
			if(role.getRoleName().equals("admin")) {
				List<Report> listReport=reportRepo.findAll();
				model.addAttribute("listReport", listReport);
				return "adminpage";
			}else return "blockpage";
			
		}
		return "errorpage";
		
	}
	
	@PostMapping(value = "/blockuser")
	public ResponseEntity<String> blockUser(@RequestParam String userIdBeLocked) {
		Role newBlockRole = new Role();
		newBlockRole.setRoleName("blockuser");
		newBlockRole.setUserId(Integer.parseInt(userIdBeLocked));
		roleRepo.save(newBlockRole);
		return ResponseEntity.ok("success");
		
	}
}
