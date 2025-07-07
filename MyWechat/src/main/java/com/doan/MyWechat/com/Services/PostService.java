package com.doan.MyWechat.com.Services;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.ColumnResult;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.SqlResultSetMapping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.doan.MyWechat.com.Common.COMMON;
import com.doan.MyWechat.com.Entities.OldImage;
import com.doan.MyWechat.com.Entities.Post;
import com.doan.MyWechat.com.Entities.User;
import com.doan.MyWechat.com.Repositories.OldImageReporsitory;
import com.doan.MyWechat.com.Repositories.PostRepository;
import com.doan.MyWechat.com.Repositories.UserRepository;
import com.doan.MyWechat.com.untils.Until;



@Service
public class PostService {
	@Autowired
	ImageServices imgServices;
	@Autowired
	PostRepository postRepo;
	
	@Autowired
	OldImageReporsitory oldImgRepo;
	
	@Autowired UserRepository userRepo;
	@PersistenceContext
	private EntityManager entityManager;
	
	
	
	public COMMON createPost(String userid, MultipartFile[] postImages, String content, String backgroundId,
			String emoteId, int scope) {
		//System.out.println(userid+"_"+postImages+" "+content+" "+backgroundId+" "+emoteId+" "+scope);
		Post newPost = new Post();
		newPost.setUserId(Integer.parseInt(userid));
		newPost.setContent(content);
		newPost.setPostBackground(backgroundId);
		newPost.setPostEmote(emoteId);
		newPost.setScope(scope);
		newPost.setCreatedAt(Until.getDateTimeNow());
		postRepo.save(newPost);
		try {
			imgServices.addListImagesToPost(newPost, postImages);
		} catch (IllegalStateException e) {
			return COMMON.CREATE_POST_ERROR;
		} catch (IOException e) {
			return COMMON.CREATE_POST_ERROR;
		}
		return COMMON.CREATE_POST_SUCESS;
	}
	
	public COMMON changeCoverPhoto(String userid, MultipartFile postImages, String content, String backgroundId,
			String emoteId, int scope) {
		//System.out.println(userid+"_"+postImages+" "+content+" "+backgroundId+" "+emoteId+" "+scope);
		Post newPost = new Post();
		newPost.setUserId(Integer.parseInt(userid));
		newPost.setContent(content);
		newPost.setPostBackground(backgroundId);
		newPost.setPostEmote(emoteId);
		newPost.setScope(scope);
		newPost.setCreatedAt(Until.getDateTimeNow());
		
		try {
			User userInDB=userRepo.findObjectById(Integer.parseInt(userid));
			if(userInDB.getCoverPhoto()!=null) {
				OldImage oldImg= new OldImage();
				oldImg.setUserId(Integer.parseInt(userid));
				oldImg.setLinkImage(userInDB.getCoverPhoto());
				oldImg.setType(2);
				oldImg.setCreatedAt(Until.getDateTimeNow());
				oldImgRepo.save(oldImg);
			}
			userRepo.save(imgServices.addNewCoverPhotoForUser(userInDB, postImages));
			newPost.setPostImages(userInDB.getCoverPhoto());
			postRepo.save(newPost);
		} catch (IllegalStateException e) {
			return COMMON.CREATE_POST_ERROR;
		} catch (IOException e) {
			return COMMON.CREATE_POST_ERROR;
		}
		return COMMON.CREATE_POST_SUCESS;
	}
	
	public COMMON changeAvatar(String userid, MultipartFile postImages, String content, String backgroundId,
			String emoteId, int scope) {
		//System.out.println(userid+"_"+postImages+" "+content+" "+backgroundId+" "+emoteId+" "+scope);
		Post newPost = new Post();
		newPost.setUserId(Integer.parseInt(userid));
		newPost.setContent(content);
		newPost.setPostBackground(backgroundId);
		newPost.setPostEmote(emoteId);
		newPost.setScope(scope);
		newPost.setCreatedAt(Until.getDateTimeNow());
		
		try {
			User userInDB=userRepo.findObjectById(Integer.parseInt(userid));
			if(userInDB.getCoverPhoto()!=null) {
				OldImage oldImg= new OldImage();
				oldImg.setUserId(Integer.parseInt(userid));
				oldImg.setLinkImage(userInDB.getAvatar());
				oldImg.setType(1);
				oldImg.setCreatedAt(Until.getDateTimeNow());
				oldImgRepo.save(oldImg);
			}
			userRepo.save(imgServices.addNewAvatarPhotoForUser(userInDB, postImages));
			newPost.setPostImages(userInDB.getAvatar());
			postRepo.save(newPost);
		} catch (IllegalStateException e) {
			return COMMON.CREATE_POST_ERROR;
		} catch (IOException e) {
			return COMMON.CREATE_POST_ERROR;
		}
		return COMMON.CREATE_POST_SUCESS;
	}
	
	public List<Map<String,String>> getListPost(int idUserLogin) {
		String sql="SELECT \r\n"
				+ "tbl_user.username AS username,\r\n"
				+ "tbl_user.avatar AS avatar,\r\n"
				+ "tbl_post.content AS content,\r\n"
				+ "tbl_post.post_images AS post_images,\r\n"
				+ "(SELECT tbl_common.value FROM commons AS tbl_common WHERE tbl_common.key=tbl_post.post_emote AND tbl_common.name='emote_post') AS post_emote,\r\n"
				+ "(SELECT tbl_common.value FROM commons AS tbl_common WHERE tbl_common.key=tbl_post.post_background AND tbl_common.name='background_post') AS post_background,\r\n"
				+ "tbl_post.created_at AS created_at,\r\n"
				+ "tbl_post.scope AS scope,\r\n"
				+ "tbl_post.id AS post_id,\r\n"
				+ "(SELECT COUNT(*) FROM likes AS tbl_likes WHERE tbl_likes.post_id=tbl_post.id AND tbl_likes.cmt_id = 0  AND tbl_likes.is_like='1') AS count_like,\r\n"
				+ "(SELECT COUNT(*) FROM likes AS tbl_likes WHERE tbl_likes.post_id=tbl_post.id AND tbl_likes.user_id='"+idUserLogin+"' AND tbl_likes.cmt_id = '0' AND tbl_likes.is_like='1') AS user_login_like,\r\n"
				+ "(SELECT COUNT(*) FROM comments AS tbl_cmts WHERE tbl_cmts.post_id=tbl_post.id AND tbl_cmts.delete_flg IS NULL OR tbl_cmts.delete_flg=0  AND tbl_cmts.parent_comment_id IS NULL) AS count_cmt,\r\n"
				+ "tbl_user.id AS iduser, \r\n"
				+ "tbl_user.cover_photo AS coverPhoto \r\n"
				+ "FROM Posts AS tbl_post\r\n"
				+ "INNER JOIN  users AS tbl_user\r\n"
				+ "ON tbl_post.user_id=tbl_user.id\r\n"
				+ "ORDER BY tbl_post.created_at DESC ";
		System.out.println(sql);
		List<Map<String,String>> listPosts=entityManager.createNativeQuery(sql).getResultList();
		for (Object object : listPosts) {
			Object[] objArr = (Object[]) object;
			if(objArr[3]!=null) {
				String[] arrayUrlImages=objArr[3].toString().split(";");
				List<String> listUrlImage = new ArrayList<>();
				if(arrayUrlImages!=null) {
					for (int i = 0; i < arrayUrlImages.length; i++) {
						listUrlImage.add(arrayUrlImages[i]);
					}
				}
				if(listUrlImage.size()>0) {
					objArr[3]=listUrlImage;
				}else {
					objArr[3]=null;
				}
			}
			if(objArr[6]!=null) {
				Timestamp timeCreatePost=(Timestamp) objArr[6];
				LocalDateTime lcCreatePost=timeCreatePost.toLocalDateTime();
				objArr[6]=Until.compareTimeWithNow(lcCreatePost);
				
				
			}
		}
		
		
		return listPosts;
		}
	
		
	}

