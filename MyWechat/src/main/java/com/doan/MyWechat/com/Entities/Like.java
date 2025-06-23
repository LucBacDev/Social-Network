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
@Table(name = "Likes")
public class Like {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="post_id")
	private int postId;
	
	@Column(name="user_id")
	private int userId;
	
	@Column(name="cmt_id")
	private int cmtId;
	
	@Column(name="created_at")
	private Timestamp createdAt;
	
	@Column(name="is_like")
	private int isLike;
	
	
	
}
