package com.doan.MyWechat.com.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doan.MyWechat.com.Entities.ChatMessage;
import com.doan.MyWechat.com.Repositories.ChatmessageRepository;

@Service
public class ChatmessageService {
	@Autowired
	ChatmessageRepository chatmessageRepo;
	
	@Autowired
	ChatroomService chatroomService;
	
	public ChatMessage save(ChatMessage chatMessage) {
		Optional<String> optional=chatroomService.getChatroomId(chatMessage.getSenderId(), chatMessage.getRecipientId(), true);
		
			String chatId=optional.get();
			chatMessage.setChatId(chatId);
			chatmessageRepo.save(chatMessage);
			return chatMessage;
		
	}
	
	public List<ChatMessage> findChatMessages(int senderId,int recipientId){
		Optional<String> optional=chatroomService.getChatroomId(senderId, recipientId, false);
		if(optional.isPresent()) {
			List<ChatMessage> listChatmessages= chatmessageRepo.findChatMessageByChatId(optional.get());
			return listChatmessages;
		} 
		else return (new ArrayList<>());
	}
}
