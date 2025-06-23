package com.doan.MyWechat.com.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doan.MyWechat.com.Entities.Chatroom;
import com.doan.MyWechat.com.Repositories.ChatroomRepository;

@Service
public class ChatroomService {
	
	@Autowired
	ChatroomRepository chatroomRepo;
	public Optional<String> getChatroomId(int senderId,int recipientId,boolean createNewRoom){
		
		 Optional OptionalChatroomInDB =chatroomRepo.findBySenderIdAndRecipientId(senderId,recipientId);
		 if(OptionalChatroomInDB!=null) {
			 Chatroom chatroomInDb=chatroomRepo.findChatroomBySenderIdAndRecipientId(senderId,recipientId);
			 Optional<String> chatroomId=Optional.of(chatroomInDb.getChatId());
			 return chatroomId;
		 }else {
			 if(createNewRoom) {
				 String chatId=String.format("%s_%s", senderId,recipientId);
				 
				 Chatroom senderRecipient = new Chatroom();
				 senderRecipient.setChatId(chatId);
				 senderRecipient.setSenderId(senderId);
				 senderRecipient.setRecipientId(recipientId);
				 chatroomRepo.save(senderRecipient);
				 
				 Chatroom recipientSender = new Chatroom();
				 recipientSender.setChatId(chatId);
				 recipientSender.setSenderId(recipientId);
				 recipientSender.setRecipientId(senderId);
				 chatroomRepo.save(recipientSender);
				 
				 return Optional.of(chatId);
			 }
		 }
		return Optional.empty();
	}
}
