package com.doan.MyWechat.com.Entities;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "chatmessages")
public class ChatMessage {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="sender_id")
	private int senderId;
	
	@Column(name="recipient_id")
	private int recipientId;
	
	@Column(name = "chat_id")
	private String chatId;
	
	@Column(length = 2000)
	private String content;
	
	@Column(name="created_at")
	private Timestamp createdAt;


	
}
