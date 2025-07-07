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
@Table(name="users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column
	private String username;
	
	@Column
	private String email;
	
	@Column 
	private String password;
	
	@Column 
	private String avatar;
	
	@Column(name="cover_photo") 
	private String coverPhoto; 

	
	@Column(length = 10) 
	private String gender; 
	
	@Column(name="birth_day") 
	private Date birthDay;
	
	@Column(name="created_at")
	private Timestamp createdAt;
	
	@Column(name="updated_at") 
	private Timestamp updatedAt;
	
	@Column 
	private String address1;
	
	@Column
	private String address2;
	
	@Column(name="status") 
	private String status;
	
	

	
	
}
