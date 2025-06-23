package com.doan.MyWechat.com.Repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.doan.MyWechat.com.Entities.Like;
@Repository
public interface LikeRepository extends BaseRepository<Like, Integer>{
	//@Query("SELECT COUNT(*) FROM Like e WHERE e.postId = ?1 AND e.userId= ?2")
	  //int findCountByPostIdAndUserId(int postId,int userId);
		@Query("SELECT COUNT(*) FROM Like e WHERE e.postId = ?1 AND e.userId= ?2 AND e.cmtId='0'")
		int findCountByPostIdAndUserIdAndCmtId(int postId,int userId,int cmtId);
		
	  Like findLikeByPostIdAndUserIdAndCmtId(int postId,int userId,int cmtId);
	  
	  Like findLikeByCmtIdAndUserId(int cmtId,int userId);
	  
	  @Query("SELECT COUNT(*) FROM Like e WHERE e.postId = ?1 AND e.userId= ?2 AND e.isLike='1'")
	  int findCountByPostIdAndUserIdAndIsLike(int postId,int userId);
	  
	  @Query("SELECT COUNT(*) FROM Like e WHERE e.cmtId = ?1 AND e.userId= ?2 AND e.isLike='1'")
	  int findCountByCmtIdAndUserIdAndIsLike(int cmtId,int userId);
	  
	  @Query("SELECT COUNT(*) FROM Like e WHERE e.cmtId = ?1 AND e.userId= ?2 AND e.isLike='0'")
	  int findCountByCmtIdAndUserIdAndIsDontLike(int cmtId,int userId);
}
