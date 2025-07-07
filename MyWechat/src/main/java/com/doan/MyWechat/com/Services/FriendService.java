package com.doan.MyWechat.com.Services;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doan.MyWechat.com.Common.COMMON;
import com.doan.MyWechat.com.Entities.Friend;
import com.doan.MyWechat.com.Repositories.FriendRepository;
import com.doan.MyWechat.com.untils.Until;

@Service
public class FriendService {
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	FriendRepository friendRepo;
	public List<Object[]> getListCanAddFriend(int idUserLogin) {
		String sql="SELECT \r\n"
				+ "tbl_user.id AS id,\r\n"
				+ "tbl_user.username AS username,\r\n"
				+ "tbl_user.avatar AS avatar,\r\n"
				+ "(SELECT COUNT(*) FROM friends AS f1 INNER JOIN friends AS f2 ON f1.friend_id=f2.friend_id\r\n"
				+ "WHERE f1.user_id="+idUserLogin+" AND f2.user_id=tbl_user.id AND f1.status= '1' AND f1.status=f2.status) AS count_mutual_friend\r\n"
				+ "FROM users AS tbl_user\r\n"
				+ "WHERE tbl_user.id NOT IN (SELECT tbl_friend.friend_id FROM friends AS tbl_friend WHERE tbl_friend.user_id = "+idUserLogin+" AND  tbl_friend.status IN ('3','5','2','1'))\r\n"
				+ "AND tbl_user.id NOT IN (SELECT tbl_friend.user_id FROM friends AS tbl_friend WHERE tbl_friend.friend_id = "+idUserLogin+" AND  tbl_friend.status IN ('3','5','2','1'))\r\n"
				+ "AND tbl_user.id !="+idUserLogin+"\r\n"
				+ "LIMIT 10";
		
		System.out.println(sql);
		List<Object[]> listResultQuery = entityManager.createNativeQuery(sql).getResultList();
		
		return listResultQuery;
	}
	public COMMON requestAddFriend(String userId, String friendId, String status) {
		try {
			Friend friendInDb = friendRepo.findFriendByUserIdAndFriendId(Integer.parseInt(userId), Integer.parseInt(friendId));
			if(friendInDb!=null) {
				friendInDb.setStatus("2");
				friendRepo.save(friendInDb);
			}else {
				Friend newFriendInRequest=new Friend();
				newFriendInRequest.setUserId(Integer.parseInt(userId));
				newFriendInRequest.setFriendId(Integer.parseInt(friendId));
				newFriendInRequest.setStatus(status);
				newFriendInRequest.setCreatedAt(Until.getDateTimeNow());
				friendRepo.save(newFriendInRequest);
			}
			return COMMON.CREATE_REQUEST_ADD_FRIEND;
		} catch (Exception e) {
			return COMMON.CREATE_REQUEST_ADD_FRIEND_BE_ERROR;
		}
		
	}
	public List<Object[]> getListRequestAddFriend(int idUserLogin) {
		String sql="SELECT \r\n"
				+ "tbl_user.id AS id,\r\n"
				+ "tbl_user.username AS username,\r\n"
				+ "tbl_user.avatar AS avatar\r\n"
				+ "FROM users AS tbl_user\r\n"
				+ "INNER JOIN friends AS tbl_friend\r\n"
				+ "ON tbl_user.id=tbl_friend.user_id\r\n"
				+ "WHERE \r\n"
				+ "tbl_friend.status=\"2\"\r\n"
				+ "AND tbl_friend.friend_id="+idUserLogin;
		List<Object[]> listRequestAddFriend = entityManager.createNativeQuery(sql).getResultList();
		return listRequestAddFriend;
	}
	public List<Object[]> getListInRequestAddFriend(int idUserLogin) {
		String sql="SELECT \r\n"
				+ "tbl_user.id AS id,\r\n"
				+ "tbl_user.username AS username,\r\n"
				+ "tbl_user.avatar AS avatar,\r\n"
				+ "(SELECT COUNT(*) FROM friends AS f1 INNER JOIN friends AS f2 ON f1.friend_id=f2.friend_id\r\n"
				+ "WHERE f1.user_id=2 AND f2.user_id=tbl_user.id AND f1.status= '1' AND f1.status=f2.status) AS count_mutual_friend\r\n"
				+ "FROM users AS tbl_user\r\n"
				+ "WHERE tbl_user.id IN (SELECT tbl_friend.friend_id FROM friends AS tbl_friend WHERE tbl_friend.user_id = "+idUserLogin+" AND  tbl_friend.status ='2')\r\n"
				+ "AND tbl_user.id !=2\r\n"
				+ "LIMIT 10";
		List<Object[]> listInRequestAddFriend=entityManager.createNativeQuery(sql).getResultList();
		return listInRequestAddFriend;
	}
	public COMMON cancelAddFriend(String userId, String friendId, String status) {
		
			Friend requestAddFriendInDB = friendRepo.findFriendByUserIdAndFriendIdAndStatus(Integer.parseInt(userId), Integer.parseInt(friendId), "2");
			requestAddFriendInDB.setStatus(status);
			friendRepo.save(requestAddFriendInDB);
			return COMMON.CANCEL_REQUEST_ADD_FRIEND;
		
	}
	public COMMON aceptRequestAddFriend(String userId, String friendId, String status) {
		Friend requestAddFriendInDB = friendRepo.findFriendByUserIdAndFriendIdAndStatus(Integer.parseInt(userId), Integer.parseInt(friendId), "2");
		requestAddFriendInDB.setStatus(status);
		friendRepo.save(requestAddFriendInDB);
		return COMMON.ACEPT_REQUEST_ADD_FRIEND;
		
	}
	public List<Object[]> getListFriend(int idUserLogin) {
		String sql="SELECT \r\n"
				+ "tbl_users.id AS id,\r\n"
				+ "tbl_users.username AS username,\r\n"
				+ "tbl_users.avatar AS avatar\r\n"
				+ "FROM users AS tbl_users\r\n"
				+ "WHERE tbl_users.id IN (SELECT user_id FROM friends AS tbl_friends WHERE friend_id='4' AND status='1')\r\n"
				+ "OR tbl_users.id IN (SELECT friend_id FROM friends AS tbl_friends WHERE user_id='4' AND status='1')\r\n"
				+ "AND tbl_users.id != '4'\r\n"
				+ "LIMIT 10";
		List<Object[]> listFriend=entityManager.createNativeQuery(sql).getResultList();
		return listFriend;
	}

}
