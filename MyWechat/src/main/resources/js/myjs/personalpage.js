var reason;
var imageCoverPhoto;
var imageCoverAvatar;
const reader=new FileReader();
function clickHeart(){
    const btnLightHeart=document.getElementById("btn-heart");
    const iLightHeart=document.getElementById("i-lightheart");
    if(iLightHeart.classList.contains("bi-heart")){
        iLightHeart.classList.add('bi-heart-fill');
        iLightHeart.classList.remove('bi-heart');
        iLightHeart.style.color="red";
    }else{
        iLightHeart.classList.add('bi-heart');
        iLightHeart.classList.remove('bi-heart-fill');
        iLightHeart.style.color="black";
    }
    
}

function inputComment(){
    const inputComment=document.getElementById("my-inputcomment");
    const iSend=document.getElementById("i-send");
    if(inputComment.value!=''){
        iSend.classList.add('bi-send-fill');
        iSend.classList.remove('bi-send');
        iSend.style.color="blue";
    }else{
        iSend.classList.add('bi-send');
        iSend.classList.remove('bi-send-fill');
        iSend.style.color="black";
    }
}
const btnCreateComment=document.getElementById("btn-create-comment");
const inputComment2=document.getElementById("my-inputcomment");
btnCreateComment.addEventListener('click',()=>{
    inputComment2.focus();
})
function openPopup(id,id2){
	const popupAction=document.getElementById(id);
	const popupAction2=document.getElementById(id2);
	if(popupAction2.classList.contains("d-none")){
		if(popupAction.classList.contains("d-none")){
		popupAction.classList.remove("d-none");
		popupAction.classList.add("d-block");
	}	else{
			popupAction.classList.remove("d-block");
			popupAction.classList.add("d-none");
	}
	}else{
		if(popupAction2.classList.contains("d-none")){
			popupAction2.classList.remove("d-none");
			popupAction2.classList.add("d-block");
	}	else{
			popupAction2.classList.remove("d-block");
			popupAction2.classList.add("d-none");
	}
	
	
	
}
}
function openPopup2(id){
	const popupParent=document.getElementById("popupAction");
	popupParent.classList.remove("d-block");
	popupParent.classList.add("d-none");
	const popupAction=document.getElementById(id);
	if(popupAction.classList.contains("d-none")){
		popupAction.classList.remove("d-none");
		popupAction.classList.add("d-block");
	}else{
		popupAction.classList.remove("d-block");
		popupAction.classList.add("d-none");
	}
	
	
}

function report(event){
	reason=event.target.innerText;
	var popupConfirm=document.getElementById("popupConfirm");
	var popupReason=document.getElementById("popupReason");
	popupReason.classList.remove("d-block");
	popupReason.classList.add("d-none");
	popupConfirm.classList.remove("d-none");
	popupConfirm.classList.add("d-block");
	var pReason=document.getElementById("pReason");
	pReason.innerText="Bạn chắc chắn muốn tố cáo tài khoản này vì "+reason;
}
function reportPost(){
	var  formData = new FormData();
	formData.append("reason",reason);
	var userId=document.getElementById("userId").value;
	var anotherId=document.getElementById("anotherId").value;
	formData.append("userId",anotherId);
	formData.append("reporterId",userId);
	$.ajax({
			type:"POST",
			url:"/reportuser",
			data:formData,
			processData:false,
			contentType:false,
			success:function(result){
				if(result=="report success"){
					const popupConfirm=document.getElementById('popupConfirm');
					popupConfirm.classList.remove("d-block");
					popupConfirm.classList.add("d-none");
				}
			},
			error:function(result){
				console.table(result);
				
			}
		})
}
function changeCoverPhoto(){
	const inputCoverPhoto=document.getElementById("inputCoverPhoto");
	inputCoverPhoto.click();
	
	console.table(inputCoverPhoto);
	inputCoverPhoto.addEventListener("change", function() {
		if (inputCoverPhoto.files.length > 0) {
    	const file = inputCoverPhoto.files[0];
    	// Xử lý file được chọn (upload, hiển thị ảnh, ...)
    	
    	reader.readAsDataURL(file);
    	reader.onload=function(){
		console.table(reader);
    	const previewImg=document.getElementById("previewImg");
    	previewImg.src=reader.result;
    	const card=document.getElementById("cardChangeCoverPhoto");
    	card.classList.remove("d-none");
    	card.classList.add("d-block");
    	imageCoverPhoto=file;
		}
    	
    	}
		})
	}
	
function clickPostStatus(){
	var formData=new FormData();
	const inputContent=document.getElementById("inputStatus");
	var userId=document.getElementById("userId").value;
	const coverPhoto=document.getElementById("coverPhoto");
	coverPhoto.src=reader.result;
	coverPhoto.style.width="70%";
	coverPhoto.style.height="405px";
	formData.append("userId",userId);
	formData.append("postImages",imageCoverPhoto);
	//console.table(listImage);
	inputContent.value!="" ? formData.append("content",inputContent.value) : formData.append("content","");
	formData.append("backgroundId","");
	formData.append("emoteId","emote21");
	formData.append("scope",1);
	//console.log(formData);
	$.ajax({
		type:"POST",
		url:"/changecoverphoto",
		data:formData,
		processData:false,
		contentType:false,
		success:function(result){
			// Cài đặt URL mới
			window.location.href = "/home";

			// Tải lại trang
			window.location.reload();

			
		},
		error:function(result){
			console.table(result);
		}
	})
	
}

function changeAvatar(){
	const inputAvatar=document.getElementById("inputAvatar");
	inputAvatar.click();
	
	console.table(inputAvatar);
	inputAvatar.addEventListener("change", function() {
		if (inputAvatar.files.length > 0) {
    	const file = inputAvatar.files[0];
    	// Xử lý file được chọn (upload, hiển thị ảnh, ...)
    	
    	reader.readAsDataURL(file);
    	reader.onload=function(){
		console.table(reader);
    	const previewImg=document.getElementById("previewImgAvatar");
    	previewImg.src=reader.result;
    	const card=document.getElementById("cardChangeAvatar");
    	card.classList.remove("d-none");
    	card.classList.add("d-block");
    	imageCoverAvatar=file;
		}
    	
    	}})
	
}

function clickPostStatus2(){
	var formData=new FormData();
	const inputContent=document.getElementById("inputStatus");
	var userId=document.getElementById("userId").value;
	const avatar=document.getElementById("imgAvatar");
	avatar.src=reader.result;
	//coverPhoto.style.width="70%";
	//coverPhoto.style.height="405px";
	formData.append("userId",userId);
	formData.append("postImages",imageCoverAvatar);
	//console.table(listImage);
	inputContent.value!="" ? formData.append("content",inputContent.value) : formData.append("content","");
	formData.append("backgroundId","");
	formData.append("emoteId","emote22");
	formData.append("scope",1);
	//console.log(formData);
	$.ajax({
		type:"POST",
		url:"/changeavatar",
		data:formData,
		processData:false,
		contentType:false,
		success:function(result){
			// Cài đặt URL mới
			window.location.href = "/home";

			// Tải lại trang
			window.location.reload();

			
		},
		error:function(result){
			console.table(result);
		}
	})
	
}

