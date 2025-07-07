package com.doan.MyWechat.com.Services;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doan.MyWechat.com.Entities.Comment;
import com.doan.MyWechat.com.Repositories.CommentRepository;
import com.doan.MyWechat.com.untils.Until;

@Service
public class CommentService {
	@Autowired
	CommentRepository commentRepo;
	@PersistenceContext
	private EntityManager entityManager;
	public List<Map<String, String>> createComment(String postId, String userId, String content) {
		Comment newComment= new Comment();
		newComment.setPostId(Integer.parseInt(postId));
		newComment.setUserId(Integer.parseInt(userId));
		newComment.setContent(content);
		newComment.setCreatedAt(Until.format(Until.getDateTimeNow()));
		commentRepo.save(newComment);
		return getDetailComment(postId,userId,newComment.getCreatedAt());
	}
	
	public List<Map<String, String>> createComment2(String cmtId,String postId, String userId, String content) {
		Comment newComment= new Comment();
		newComment.setParentCommentId(Integer.parseInt(cmtId));
		newComment.setUserId(Integer.parseInt(userId));
		newComment.setPostId(Integer.parseInt(postId));
		newComment.setContent(content);
		newComment.setCreatedAt(Until.format(Until.getDateTimeNow()));
		commentRepo.save(newComment);
		return getDetailComment2(cmtId,userId,newComment.getCreatedAt());
	}
	
	@SuppressWarnings("deprecation")
	public List<Map<String, String>> getDetailComment2(String cmtId, String userId, Timestamp createdAt) {
		String sql="SELECT tbl_user.username AS userName,\r\n"
				+ "tbl_user.avatar AS avatar,\r\n"
				+ "tbl_cmt.content AS content,\r\n"
				+ "tbl_cmt.created_at AS createdAt  \r\n"
				+ "FROM comments AS tbl_cmt\r\n"
				+ "INNER JOIN users AS tbl_user ON tbl_cmt.user_id=tbl_user.id\r\n"
				+ "WHERE tbl_cmt.parent_comment_id="+cmtId+" AND tbl_cmt.user_id="+userId+" AND tbl_cmt.created_at='"+createdAt+"'";
		List<Map<String, String>> listCmts=entityManager.createNativeQuery(sql).getResultList();
		for (Object obj : listCmts) {
			Object[] arrObj= (Object[]) obj;
			if(arrObj[3]!=null) {
				Timestamp timeCreateCmt=(Timestamp) arrObj[3];
				LocalDateTime lcCreateCmt=timeCreateCmt.toLocalDateTime();
				arrObj[3]=Until.compareTimeWithNow(lcCreateCmt);
			}
		}
			return  listCmts;
	}

	@SuppressWarnings("deprecation")
	public List<Map<String, String>> getDetailComment(String postId, String userId, Timestamp createdAt) {
		String sql="SELECT tbl_user.username AS userName,\r\n"
				+ "tbl_user.avatar AS avatar,\r\n"
				+ "tbl_cmt.content AS content,\r\n"
				+ "tbl_cmt.created_at AS createdAt  \r\n"
				+ "FROM comments AS tbl_cmt\r\n"
				+ "INNER JOIN users AS tbl_user ON tbl_cmt.user_id=tbl_user.id\r\n"
				+ "WHERE tbl_cmt.post_id="+postId+" AND tbl_cmt.user_id="+userId+" AND tbl_cmt.created_at='"+createdAt+"'";
		List<Map<String, String>> listCmts=entityManager.createNativeQuery(sql).getResultList();
		for (Object obj : listCmts) {
			Object[] arrObj= (Object[]) obj;
			if(arrObj[3]!=null) {
				Timestamp timeCreateCmt=(Timestamp) arrObj[3];
				LocalDateTime lcCreateCmt=timeCreateCmt.toLocalDateTime();
				arrObj[3]=Until.compareTimeWithNow(lcCreateCmt);
			}
		}
			return  listCmts;
	}

	public List<Map<String, String>> getListComment(String postId, String filterType) {
		if(Integer.parseInt(filterType)==1) {
			String sql="SELECT\r\n"
					+ "tbl_user.username AS userName,\r\n"
					+ "tbl_user.avatar AS avatar,\r\n"
					+ "tbl_cmt.content AS content,\r\n"
					+ "tbl_cmt.created_at AS createdAt, \r\n"
					+ "tbl_cmt.id AS id, \r\n"
					+ "(SELECT COUNT(*) FROM likes AS tbl_like WHERE  tbl_like.cmt_id=tbl_cmt.id) AS countLikeCmt, \r\n"
					+ "tbl_cmt.parent_comment_id AS parent_cmt_id \r\n"
					+ "FROM comments AS tbl_cmt\r\n"
					+ "INNER JOIN users AS tbl_user ON tbl_cmt.user_id=tbl_user.id\r\n"
					+ "WHERE tbl_cmt.post_id="+postId+"\r\n"
					+ "ORDER BY tbl_cmt.created_at DESC";
		//System.out.println(sql);
		List<Map<String, String>> listCmts=entityManager.createNativeQuery(sql).getResultList();
			for (Object obj : listCmts) {
				Object[] arrObj= (Object[]) obj;
				if(arrObj[3]!=null) {
					Timestamp timeCreateCmt=(Timestamp) arrObj[3];
					LocalDateTime lcCreateCmt=timeCreateCmt.toLocalDateTime();
					arrObj[3]=Until.compareTimeWithNow(lcCreateCmt);
					}
			}
			return  listCmts;	
		}else if(Integer.parseInt(filterType)==2){
			return	null;
		}else if(Integer.parseInt(filterType)==3){
			String sql="SELECT\r\n"
					+ "tbl_user.username AS userName,\r\n"
					+ "tbl_user.avatar AS avatar,\r\n"
					+ "tbl_cmt.content AS content,\r\n"
					+ "tbl_cmt.created_at AS createdAt, \r\n"
					+ "tbl_cmt.id AS id, \r\n"
					+ "(SELECT COUNT(*) FROM likes AS tbl_like WHERE  tbl_like.cmt_id=tbl_cmt.id) AS countLikeCmt,\r\n"
					+ "tbl_cmt.parent_comment_id AS parent_cmt_id \r\n"
					+ "FROM comments AS tbl_cmt\r\n"
					+ "INNER JOIN users AS tbl_user ON tbl_cmt.user_id=tbl_user.id\r\n"
					+ "WHERE tbl_cmt.post_id="+postId;
			
		List<Map<String, String>> listCmts=entityManager.createNativeQuery(sql).getResultList();
			for (Object obj : listCmts) {
				Object[] arrObj= (Object[]) obj;
				if(arrObj[3]!=null) {
					Timestamp timeCreateCmt=(Timestamp) arrObj[3];
					LocalDateTime lcCreateCmt=timeCreateCmt.toLocalDateTime();
					arrObj[3]=Until.compareTimeWithNow(lcCreateCmt);
					}
			}
			return	listCmts;	
		}
		return null;
	}

}
