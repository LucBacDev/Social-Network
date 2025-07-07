package com.doan.MyWechat.com.Repositories;

import com.doan.MyWechat.com.Entities.Friend;

public interface FriendRepository extends BaseRepository<Friend, Integer> {
	Friend findFriendByUserIdAndFriendIdAndStatus(int userId,int friendId,String status);
	Friend findFriendByUserIdAndFriendId(int userId,int friendId);
}
