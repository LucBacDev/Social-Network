package com.doan.MyWechat.com.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CommonAbstractCriteria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.doan.MyWechat.com.Common.COMMON;
import com.doan.MyWechat.com.Entities.Comment;
import com.doan.MyWechat.com.Entities.Post;
import com.doan.MyWechat.com.Services.CommentService;
import com.doan.MyWechat.com.Services.LikeService;
import com.doan.MyWechat.com.Services.PostService;



@Controller
public class HomeController {
	@Autowired
	PostService postService;
	@Autowired
	LikeService likeService;
	@Autowired
	CommentService commentService;
	@RequestMapping(value ="/home")
	public String index(Model model) {
		List<Map<String,String>> listPosts= postService.getListPost(LoginController.idUserLogin);
		model.addAttribute("listPost", listPosts);
		
		 return "homepage";
	}
	
	@PostMapping(value ="/createstatus")
	public ResponseEntity<String> creatStatus(@RequestParam String userId,
								@RequestParam MultipartFile[] postImages,
								@RequestParam String content,
								@RequestParam String backgroundId,
								@RequestParam String emoteId,
								@RequestParam int scope) {  
		COMMON status=postService.createPost(userId,postImages,content,backgroundId,emoteId,scope);
		if(status.equals(COMMON.CREATE_POST_SUCESS)) {
			return ResponseEntity.ok("success");
		}else return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("error");
	}
	
	@PostMapping(value ="/likepost")
	public ResponseEntity<String> likePost(@RequestParam String postId,@RequestParam String userId,@RequestParam String isLike) {  
		int userLoginLike =likeService.likePost(postId,userId,isLike);
		if(userLoginLike==1) {
			return ResponseEntity.ok("userloginlike");
		}
		return ResponseEntity.ok("userlogindontlike");

	}
	
	@PostMapping(value ="/likecmt")
	public ResponseEntity<String> likeCmt(@RequestParam String cmtId,@RequestParam String userId,@RequestParam String postId) {  
		Boolean isLike=likeService.likeCmt(cmtId,userId,postId);
		if(isLike) {
			return ResponseEntity.ok("userloginlike");
		}
		return ResponseEntity.ok("userlogindontlike");

	}
	
	@PostMapping(value ="/createcomment")
	public ResponseEntity<Object[]> createComment(@RequestParam String postId,@RequestParam String userId,@RequestParam String content) {  
		List<Map<String, String>> detailComment =commentService.createComment(postId,userId,content);
		Object [] arrObj = null;
		for (Object object : detailComment) {
			arrObj=(Object[]) object;
			break;
		}
		if (arrObj != null) {
			return ResponseEntity.ok(arrObj);
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);

	}
	
	@PostMapping(value ="/createcomment2")
	public ResponseEntity<Object[]> createComment2(@RequestParam String cmtId,@RequestParam String postId,@RequestParam String userId,@RequestParam String content) {  
		List<Map<String, String>> detailComment =commentService.createComment2(cmtId,postId,userId,content);
		Object [] arrObj = null;
		for (Object object : detailComment) {
			arrObj=(Object[]) object;
			break;
		}
		if (arrObj != null) {
			return ResponseEntity.ok(arrObj);
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);

	}
	
	@PostMapping(value ="/getlistcomment")
	public ResponseEntity<List<Object[]>> getListComment(Model model,@RequestParam String postId,@RequestParam String filterType) {
		List<Map<String, String>> listComments =commentService.getListComment(postId,filterType);
		if(listComments==null) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}else {
			List<Object[]> listCmtsReturn= new ArrayList<>();
			for (Object object : listComments) {
				Object[] arrObj=(Object[]) object;
				listCmtsReturn.add(arrObj);
			}
			return ResponseEntity.ok(listCmtsReturn);
		}
		

	}

	
}
