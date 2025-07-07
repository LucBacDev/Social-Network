package com.doan.MyWechat.com.Controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.doan.MyWechat.com.Entities.ChatMessage;
import com.doan.MyWechat.com.Entities.ChatNotification;
import com.doan.MyWechat.com.Services.ChatmessageService;


@Controller
public class ChatmessageController {
	
	@Autowired
	SimpMessagingTemplate simpMessagingTemplate;
	
	@Autowired
	ChatmessageService chatmessageService;
	
	@MessageMapping("/chat")
	public void processMessage(@Payload ChatMessage chatMessage) {
		ChatMessage savedMessage=chatmessageService.save(chatMessage);
		simpMessagingTemplate.convertAndSendToUser(
				chatMessage.getRecipientId()+"",
				"/queue/messages",
				ChatNotification.builder()
				.id(savedMessage.getId())
				.senderId(savedMessage.getSenderId())
				.recipientId(savedMessage.getRecipientId())
				.content(savedMessage.getContent())
				.build());
	}
	
	@GetMapping("messages/{senderId}/{recipientId}")
	public ResponseEntity<List<ChatMessage>> findChatMessages(@PathVariable String senderId,@PathVariable String recipientId){
		return ResponseEntity.ok(chatmessageService.findChatMessages(Integer.parseInt(senderId), Integer.parseInt(recipientId)));
	}

}
