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
@Table(name="posts")
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	 private int id ;
	
	@Column(name="user_id")
	private int userId;

	@Column(length = 2000)
	private String content;



	@Column(name="created_at")
	private Timestamp createdAt;
	
	@Column(name="updated_at")
	private Timestamp updatedAt;
	
	@Column
	private int scope;
	
	@Column(name="post_images")
	private String postImages;
	
	@Column(name="post_emote")
	private String postEmote;
	
	@Column(name="post_background")
	private String postBackground;
	
	
	
	
}
