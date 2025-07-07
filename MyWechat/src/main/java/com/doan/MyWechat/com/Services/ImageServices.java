package com.doan.MyWechat.com.Services;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.doan.MyWechat.com.Entities.Post;
import com.doan.MyWechat.com.Entities.User;

import antlr.StringUtils;

@Service
public class ImageServices {
	private boolean isEmptyUploadFile(MultipartFile[] images) {
		if (images == null || images.length <= 0)
			return true;

		if (images.length == 1 && images[0].getOriginalFilename().isEmpty())
			return true;

		return false;
	}

	/**
	 * dùng để kiểm tra xem admin có upload ảnh product hay không
	 * @param image
	 * @return
	 */
	private boolean isEmptyUploadFile(MultipartFile image) {
		return image == null || image.getOriginalFilename().isEmpty();
	}
	
	private String getUniqueUploadFileName(String fileName) {
		String[] splitFileName = fileName.split("\\.");
		return splitFileName[0] + System.currentTimeMillis() + "." + splitFileName[1];
	}
	
	@Transactional
	public User addImage(User a, MultipartFile avatar)
			throws IllegalStateException, IOException {

		//kiểm tra có upload ảnh
		if (!isEmptyUploadFile(avatar)) { 
			
			String fileName = getUniqueUploadFileName(avatar.getOriginalFilename());
			
			
			String pathToAvatar = "E:/ProjectDoAn/DoAn/images/userImages/avatar/" + fileName;

			// lưu avatar vào đường dẫn trên
			avatar.transferTo(new File(pathToAvatar));

			a.setAvatar("../images/userImages/avatar/" + fileName);
		}
		return a;

		
	}
	
	@Transactional
	public Post addListImagesToPost(Post a,MultipartFile[] listImages) throws IllegalStateException, IOException {
		StringBuilder listUrlImages=new StringBuilder();
		if(!isEmptyUploadFile(listImages)) {
			for (MultipartFile file : listImages) {
				String fileName = getUniqueUploadFileName(file.getOriginalFilename());
				String pathStore = "E:/ProjectDoAn/DoAn/images/postImages/" + fileName;
				file.transferTo(new File(pathStore));
				listUrlImages.append("../images/postImages/");
				listUrlImages.append(fileName);
				listUrlImages.append(";");
			}
		}
		System.out.println(listUrlImages);
		a.setPostImages(listUrlImages.toString());
		return a;
	}
	
	@Transactional
	public User addNewCoverPhotoForUser(User a,MultipartFile image) throws IllegalStateException, IOException {
		//kiểm tra có upload ảnh
				if (!isEmptyUploadFile(image)) { 
					
					String fileName = getUniqueUploadFileName(image.getOriginalFilename());
					
					
					String pathToAvatar = "E:/ProjectDoAn/DoAn/images/userImages/coverPhoto/" + fileName;

					// lưu image vào đường dẫn trên
					image.transferTo(new File(pathToAvatar));

					a.setCoverPhoto("../images/userImages/coverPhoto/" + fileName);
				}
				return a;
	}
	
	@Transactional
	public User addNewAvatarPhotoForUser(User a,MultipartFile image) throws IllegalStateException, IOException {
		//kiểm tra có upload ảnh
				if (!isEmptyUploadFile(image)) { 
					
					String fileName = getUniqueUploadFileName(image.getOriginalFilename());
					
					
					String pathToAvatar = "E:/ProjectDoAn/DoAn/images/userImages/avatar/" + fileName;

					// lưu image vào đường dẫn trên
					image.transferTo(new File(pathToAvatar));

					a.setAvatar("../images/userImages/avatar/" + fileName);
				}
				return a;
	}
}
