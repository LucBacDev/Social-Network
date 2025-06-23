package com.doan.MyWechat.com.Repositories;

import java.util.List;

import com.doan.MyWechat.com.Entities.User;

public interface UserRepository extends BaseRepository<User, Integer> {
	public User findTopUserByEmail(String email);

	public List<User> findAllByStatus(String status);
	
}
