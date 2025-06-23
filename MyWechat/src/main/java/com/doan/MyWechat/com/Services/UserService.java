package com.doan.MyWechat.com.Services;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.doan.MyWechat.com.Common.COMMON;
import com.doan.MyWechat.com.Common.UserStatus;
import com.doan.MyWechat.com.Controller.LoginController;
import com.doan.MyWechat.com.Entities.Role;
import com.doan.MyWechat.com.Entities.User;
import com.doan.MyWechat.com.Repositories.RoleRepository;
import com.doan.MyWechat.com.Repositories.UserRepository;
import com.doan.MyWechat.com.untils.Until;

import lombok.experimental.var;

@Service
public class UserService {
	
	@Autowired 
	UserRepository userRepo; 
	
	@Autowired 
	RoleRepository roleRepo;; 
	
	@Autowired 
	ImageServices imgServices;
	public COMMON reigisterAccount(String email, String password) {
		User newUser = new User();
		User oldUser = userRepo.findTopUserByEmail(email);
		if(oldUser!=null) {
			return COMMON.REGISTER_STATUS_ERROR_DUPLICATE_MAIL ;
		}
		newUser.setEmail(email);
		newUser.setUsername(email);
		//hashPassword
		
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(password.getBytes());
			byte[] digest = md.digest();
			String hashPassword=DatatypeConverter.printHexBinary(digest).toUpperCase();
			newUser.setPassword(hashPassword);
			newUser.setCreatedAt(Until.getDateTimeNow());
			userRepo.save(newUser);
		} catch (NoSuchAlgorithmException e) {
			return COMMON.REGISTER_STATUS_ERROR ;
		}
		
		return COMMON.REGISTER_STATUS_SUCESS ;
		
	}
	
	public COMMON reigisterInforUser(int id, String username, String gender,
			String birthday, String address, String address2, MultipartFile avatar) {
		User userInDB=userRepo.findObjectById(id);
		userInDB.setUsername(username);
		userInDB.setGender(gender);
		userInDB.setAddress1(address);
		userInDB.setAddress2(address2);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate birthdayDate=LocalDate.parse(birthday,formatter);
		userInDB.setBirthDay(java.sql.Date.valueOf(birthdayDate));
		userInDB.setUpdatedAt(Until.getDateTimeNow());
		try {
			imgServices.addImage(userInDB, avatar);
			userRepo.save(userInDB);
			return COMMON.ADDINFOR_STATUS_SUCCESS;
		} catch (IllegalStateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return COMMON.ADDINFOR_STATUS_ERROR;
		
	}

	public COMMON login(HttpServletRequest request, String email, String password) {
		User userInDB =userRepo.findTopUserByEmail(email);
		if(userInDB==null) {
			return COMMON.LOGIN_BY_NEW_EMAIL;
		}
		
		else {
			try {
				MessageDigest md=MessageDigest.getInstance("MD5");
				md.update(password.getBytes());
				byte[] digest= md.digest();
				String hashPassword=DatatypeConverter.printHexBinary(digest).toUpperCase();
				if(hashPassword.equals(userInDB.getPassword())){
					HttpSession session = request.getSession();
					session.setAttribute("user", userInDB);
					LoginController.idUserLogin=userInDB.getId();
					Role role=roleRepo.findRoleByUserId(userInDB.getId());
					if(role!=null) {
						if(role.getRoleName().equals("admin")) {
							return COMMON.LOGIN_BY_ROLE_ADMIN;
						}else if(role.getRoleName().equals("blockuser")) {
							return COMMON.LOGIN_BY_ROLE_BLOCK;
						}
					}
					else return COMMON.LOGIN_SUCCESS;
				}else {
					return COMMON.LOGIN_ERROR_INVALID_PASSWORD;
				}
			} catch (NoSuchAlgorithmException e) {
				
				e.printStackTrace();
				return COMMON.LOGIN_ERROR_ALOTHIRM;
			}
			
		}
		return null;
	
	}
	
	public void saveUser(User user) {
		user.setStatus(UserStatus.ONLINE.getValue());
		userRepo.save(user);
	}
	
	public void disconnect(User user) {
		Optional<User> storedUser=userRepo.findById(user.getId());
		if(storedUser!=null) {
			User userInDb=userRepo.findObjectById(user.getId());
			userInDb.setStatus(UserStatus.OFFLINE.getValue());
			userRepo.save(userInDb);
		}
	}
	
	public List<User> findConnectUsers(){
		return userRepo.findAllByStatus(UserStatus.ONLINE.getValue());
	}

}
