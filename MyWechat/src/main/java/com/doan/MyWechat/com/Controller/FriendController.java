package com.doan.MyWechat.com.Controller;

import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CommonAbstractCriteria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.doan.MyWechat.com.Common.COMMON;
import com.doan.MyWechat.com.Services.FriendService;

@Controller
public class FriendController {
	@Autowired
	FriendService friendService;
	@GetMapping("/friend")
	public String getFriendPage(Model model) {
		List<Object[]> listCanAddFriend= friendService.getListCanAddFriend(LoginController.idUserLogin);
		List<Object[]> listRequestAddFriend= friendService.getListRequestAddFriend(LoginController.idUserLogin);
		List<Object[]> listInRequestAddFriend= friendService.getListInRequestAddFriend(LoginController.idUserLogin);
		List<Object[]> listFriend= friendService.getListFriend(LoginController.idUserLogin);
		model.addAttribute("listInRequestAddFriend", listInRequestAddFriend);
		model.addAttribute("listCanAddFriend", listCanAddFriend);
		model.addAttribute("listRequestAddFriend", listRequestAddFriend);
		model.addAttribute("listFriend", listFriend);
		model.addAttribute("countListFriend", listFriend.size());
		model.addAttribute("countRequestAddFriend", listRequestAddFriend.size());
		return "friendpage";
	}
	
	@PostMapping("/addfriend")
	public ResponseEntity<String> addFriend(@RequestParam String userId,@RequestParam String friendId,@RequestParam String status) {
		COMMON result=friendService.requestAddFriend(userId,friendId,status);
		if(result.equals(COMMON.CREATE_REQUEST_ADD_FRIEND)) {
			return ResponseEntity.ok("create request add friend success");
		}
		else return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("error create request add friend");
	}
	
	@PostMapping("/canceladdfriend")
	public ResponseEntity<String> cancelAddFriend(@RequestParam String userId,@RequestParam String friendId,@RequestParam String status) {
		COMMON result=friendService.cancelAddFriend(userId,friendId,status);
		if(result.equals(COMMON.CANCEL_REQUEST_ADD_FRIEND)) {
			return ResponseEntity.ok("cancel request add friend success");
		}
		else return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("error cancel request add friend");
	}
	
	@PostMapping("/aceptrequestaddfriend")
	public ResponseEntity<String> aceptRequestAddFriend(@RequestParam String userId,@RequestParam String friendId,@RequestParam String status) {
		COMMON result=friendService.aceptRequestAddFriend(userId,friendId,status);
		if(result.equals(COMMON.ACEPT_REQUEST_ADD_FRIEND)) {
			return ResponseEntity.ok("acept request add friend success");
		}
		else return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("acept request add friend error");
	}
}
