package com.doan.MyWechat.com.Repositories;

import java.util.List;

import com.doan.MyWechat.com.Entities.ChatMessage;


public interface ChatmessageRepository extends BaseRepository<ChatMessage, Integer> {

	List<ChatMessage> findChatMessageByChatId(String chatId);

}
