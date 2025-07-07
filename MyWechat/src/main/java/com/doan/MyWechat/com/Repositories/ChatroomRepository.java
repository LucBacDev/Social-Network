package com.doan.MyWechat.com.Repositories;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.doan.MyWechat.com.Entities.Chatroom;
 

@Repository
public interface ChatroomRepository extends BaseRepository<Chatroom, Integer> {

	Optional<Chatroom> findBySenderIdAndRecipientId(int senderId, int recipientId);

	Chatroom findChatroomBySenderIdAndRecipientId(int senderId, int recipientId);
	
}
