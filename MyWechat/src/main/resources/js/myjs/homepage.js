var isFirstImg=true;
var backgroundId;
var emoteId;
var indexImg=1;
var listImage=[];


    const modalCreateStatus=document.getElementById("modal-createstatus");
    const inputCreateStatus=document.getElementById("inputCreateStatus");
    modalCreateStatus.addEventListener('show.bs.modal', ()=>{
		
        //inputCreateStatus.focus();
    })
    const btnCloseStatusForm=document.getElementById("btn-close-createstatus");
    modalCreateStatus.addEventListener('hide.bs.modal', ()=>{
        //btnCloseStatusForm.click();
    })

   // modalCreateStatus.onclick(function(){
    //    modalCreateStatus.hide();
    //})
    function clickHeart(btnId,iId){
        const btnLightHeart=document.getElementById(btnId);
        const iLightHeart=document.getElementById(iId);
        if(iLightHeart.classList.contains("bi-heart")){
            iLightHeart.classList.add('bi-heart-fill');
            iLightHeart.classList.remove('bi-heart');
            iLightHeart.style.color="red";
        }else{
            iLightHeart.classList.add('bi-heart');
            iLightHeart.classList.remove('bi-heart-fill');
            iLightHeart.style.color="black";
        }
        var idPost=btnId.substring(9);
        if(iLightHeart.classList.contains("bi-heart-fill")&&iLightHeart.style.color=="red"){
			ajaxLikePost(true,idPost,btnId,iId);
		}else{
			ajaxLikePost(false,idPost,btnId,iId);
		}
    }
	function ajaxLikePost(isLike,idPost,btnId,iId){
		var  formData = new FormData();
		const inputUserId=document.getElementById("userId");
		formData.append("postId",idPost);
		formData.append("userId",inputUserId.value);
		isLike ? formData.append("isLike",1) : formData.append("isLike",0);
		$.ajax({
			type:"POST",
			url:"/likepost",
			data:formData,
			processData:false,
			contentType:false,
			success:function(result){
				const pCountLike=document.getElementById('p-countlike'+idPost);
				if(result=="userloginlike"){
					if(pCountLike.textContent.length>0){
					pCountLike.textContent="Bạn và "+pCountLike.textContent+" người khác";	
					}
					else{
						pCountLike.textContent="Bạn";
					}
					
				}else{
					var index=pCountLike.textContent.search(/\d+/);
					pCountLike.textContent=pCountLike.textContent.charAt(index);
				}
			},
			error:function(result){
				console.table(result);
				
			}
		})
		
	}
	function ajaxLikePost2(event){
		var cmtId=event.target.getAttribute("cmtId");
		var postId=event.target.getAttribute("postId");
		var  formData = new FormData();
		const inputUserId=document.getElementById("userId");
		formData.append("userId",inputUserId.value);
		formData.append("cmtId",cmtId);
		formData.append("postId",postId);
		$.ajax({
			type:"POST",
			url:"/likecmt",
			data:formData,
			processData:false,
			contentType:false,
			success:function(result){
				const pCountLike=document.getElementById("pCountLike"+cmtId);
				if(result=="userloginlike"){
					pCountLike.textContent=Number(pCountLike.textContent)+1;
				}else{
					if(Number(pCountLike.textContent)-1==0){
						pCountLike.textContent="";
					}
					else pCountLike.textContent=Number(pCountLike.textContent)-1;
				}
			},
			error:function(result){
				console.table(result);
				
			}
		})
	}
    function inputComment(inputId,iSendId){
        const inputComment=document.getElementById(inputId);
        const iSend=document.getElementById(iSendId);
        if(inputComment.value!=''){
            //iSend.classList.add('bi-send-fill');
            //iSend.classList.remove('bi-send');
            iSend.style.color="blue";
        }else{
            //iSend.classList.add('bi-send');
            //iSend.classList.remove('bi-send-fill');
            iSend.style.color="black";
        }
    }
    function clickIComment(btnId,iId,divCmtsId,postId){
		const divCmts=document.getElementById(divCmtsId);
		if(divCmts.style.display=="none"||divCmts.style.display==""){
			divCmts.style.display="block";	
			divCmts.style.opacity="1";
			const divlistCmt=document.getElementById("list-cmt"+postId);
			divlistCmt.textContent="";
			getComment(postId,1);
		}else{
			divCmts.style.display="none";
			divCmts.style.opacity="0";	
		}
		
		const btnCreateComment=document.getElementById(btnId);
    	const inputComment2=document.getElementById(iId);
        	inputComment2.focus();
 
	}
	function getComment(postId,filterType){
		var formData=new FormData();
		formData.append("postId",postId);
		formData.append("filterType",filterType);
		
		$.ajax({
			type:"POST",
			url:"/getlistcomment",
			data:formData,
			processData:false,
			contentType:false,
			success:function(listArray){
				console.log(listArray);

				var arrayParentCmt=[];
				var arrayChildCmt=[];
				//Init list parenCmt and list childcmt
				for(var i=0; i<listArray.length;i++){
					if(listArray[i][6] == null){
						arrayParentCmt.push(listArray[i]);
						createFormCmt(postId,listArray[i]);
					}else{
						arrayChildCmt.push(listArray[i]);
					}
				}
				if(arrayChildCmt.length!=0){
					insertChildCmt(postId,arrayParentCmt,arrayChildCmt);
				}
				
				
				
				//for(let i = 0; i < listArray.length; i++){
				//	createFormCmt(postId,listArray[i]);
				//}
			},error:function(result){
				console.log(result);
			}
		})
	}
	function insertChildCmt(postId,arrayParentCmt,arrayChildCmt){
		var arrayParentCmtNew=[];
		var arrayChildCmtNew=[];
		for(var i=0; i<arrayParentCmt.length;i++){
			for(var j=0;j<arrayChildCmt.length;j++){
				if(arrayParentCmt[i][4]==arrayChildCmt[j][6]){
					arrayParentCmtNew.push(arrayChildCmt[j]);
					createFormCmt2(arrayChildCmt[j][6],arrayChildCmt[j],postId);
					arrayChildCmt.splice(j,1);
				}
			}
		}
		if(arrayChildCmt.length!=0){
			for(var j=0;j<arrayChildCmt.length;j++){
				arrayChildCmtNew.push(arrayChildCmt[j]);
			}
			insertChildCmt(postId,arrayParentCmtNew,arrayChildCmtNew);
		}
		
	}
	function comment(postId,inputCmtId){
		const inputComment=document.getElementById(inputCmtId);
		const inputUserId=document.getElementById("userId");
		if(inputComment.value!=""){
			var formData = new FormData();
			formData.append("postId",postId);
			formData.append("userId",inputUserId.value);
			formData.append("content",inputComment.value);
			$.ajax({
				type:"POST",
				url:"/createcomment",
				data:formData,
				processData:false,
				contentType:false,
				success:function(result){
					inputComment.value="";
					createFormCmt(postId,result);
				},
				error:function(result){
					console.table(result);
				}
			})
		}
}
function comment2(event){
	console.log(event.target);
	var parentCmtId=event.target.getAttribute("parentCmtId");
	var postId=event.target.getAttribute("postId");
	const inputComment=document.getElementById("input"+event.target.id);
	const inputUserId=document.getElementById("userId");
	const divActionResult=document.getElementById("actionResult"+parentCmtId);
		if(inputComment.value!=""){
			var formData = new FormData();
			formData.append("cmtId",parentCmtId);
			formData.append("postId",postId);
			formData.append("userId",inputUserId.value);
			formData.append("content",inputComment.value);
			$.ajax({
				type:"POST",
				url:"/createcomment2",
				data:formData,
				processData:false,
				contentType:false,
				success:function(result){
					divActionResult.textContent="";
					createFormCmt2(parentCmtId,result,postId);
				},
				error:function(result){
					console.table(result);
				}
			})
		}
}
function createFormCmt2(parentCmtId,result,postId){
	const divParentCmt=document.getElementById("actionResult"+parentCmtId);
	const divParent=document.createElement("div");
	divParent.classList.add("d-flex");
	divParent.classList.add("flex-row");
	
	const imgUser=document.createElement("img");
	imgUser.src=result[1];
	imgUser.width="32";
	imgUser.height="32";
	imgUser.classList.add("rounded-circle");
	divParent.appendChild(imgUser);
	const divDetailCmt=document.createElement("div");
	divDetailCmt.classList.add("detail-comment");
	const h6nameUser=document.createElement("h6");
	h6nameUser.textContent=result[0];
	const pContent=document.createElement("p");
	pContent.textContent=result[2];
	divDetailCmt.appendChild(h6nameUser);
	divDetailCmt.appendChild(pContent);
	divParent.appendChild(divDetailCmt);
	const divActionCmt=document.createElement("div");
	divActionCmt.classList.add("title-actioncomment");
	divActionCmt.classList.add("d-flex");
	divActionCmt.classList.add("flex-row");
	const pTime=document.createElement("a");
	const pLike=document.createElement("a");
	const pCmt=document.createElement("a");
	pTime.style.cursor="pointer";
	pTime.textContent=result[3];
	pLike.style.cursor="pointer";
	pLike.textContent="Yêu thích";
	pLike.setAttribute("postId",postId);
	pLike.setAttribute("cmtId",result[4]);
	pLike.addEventListener("click",ajaxLikePost2);
	pCmt.style.cursor="pointer";
	pCmt.textContent="Trả lời";
	pCmt.classList.add("actionResult");
	if(result[4]!= null){
		pCmt.id='actionResult'+result[4];
	}
	pCmt.setAttribute("postId",postId);
	divActionCmt.appendChild(pTime);
	divActionCmt.appendChild(pLike);
	divActionCmt.appendChild(pCmt);
	const iHeart =document.createElement("i");
	iHeart.classList.add("bi");
	iHeart.classList.add("bi-heart-fill");
	iHeart.style.color="red";
	iHeart.style.marginLeft="4px";
	iHeart.style.marginTop= "-7px";
	const pCountLike =document.createElement("p");
	if(result[5]!=0){
	pCountLike.textContent=result[5];	
	}else{
	pCountLike.textContent="";
	}
	pCountLike.id="pCountLike"+result[4];
	pCountLike.style.marginLeft="20px";
	pCountLike.style.marginTop= "-7px";
	divActionCmt.appendChild(pCountLike);
	divActionCmt.appendChild(iHeart);
	const divCmt=document.createElement("div");
	if(result[4]!= null){
		divCmt.id='cmt'+result[4];
	}
	divCmt.style.marginLeft="-170px";
	divCmt.style.marginTop="20px";
	divCmt.appendChild(divParent);
	divCmt.appendChild(divActionCmt);
	divParentCmt.appendChild(divCmt);
	//pCmt.addEventListener("click",createFormResultCmt);
}
function createFormCmt(postId,result){
	const divlistCmt=document.getElementById("list-cmt"+postId);
	const divParent=document.createElement("div");
	divParent.classList.add("d-flex");
	divParent.classList.add("flex-row");
	const imgUser=document.createElement("img");
	imgUser.src=result[1];
	imgUser.width="32";
	imgUser.height="32";
	imgUser.classList.add("rounded-circle");
	divParent.appendChild(imgUser);
	const divDetailCmt=document.createElement("div");
	divDetailCmt.classList.add("detail-comment");
	const h6nameUser=document.createElement("h6");
	h6nameUser.textContent=result[0];
	const pContent=document.createElement("p");
	pContent.textContent=result[2];
	divDetailCmt.appendChild(h6nameUser);
	divDetailCmt.appendChild(pContent);
	divParent.appendChild(divDetailCmt);
	const divActionCmt=document.createElement("div");
	divActionCmt.classList.add("title-actioncomment");
	divActionCmt.classList.add("d-flex");
	divActionCmt.classList.add("flex-row");
	const pTime=document.createElement("a");
	const pLike=document.createElement("a");
	const pCmt=document.createElement("a");
	pTime.style.cursor="pointer";
	pTime.textContent=result[3];
	pLike.style.cursor="pointer";
	pLike.textContent="Yêu thích";
	pLike.setAttribute("postId",postId);
	pLike.setAttribute("cmtId",result[4]);
	pLike.addEventListener("click",ajaxLikePost2);
	pCmt.style.cursor="pointer";
	pCmt.textContent="Trả lời";
	pCmt.classList.add("actionResult");
	if(result[4]!= null){
		pCmt.id='actionResult'+result[4];
	}
	pCmt.setAttribute("postId",postId);
	divActionCmt.appendChild(pTime);
	divActionCmt.appendChild(pLike);
	divActionCmt.appendChild(pCmt);
	const iHeart =document.createElement("i");
	iHeart.classList.add("bi");
	iHeart.classList.add("bi-heart-fill");
	iHeart.style.color="red";
	iHeart.style.marginLeft="4px";
	iHeart.style.marginTop= "-7px";
	const pCountLike =document.createElement("p");
	if(result[5]!=0){
	pCountLike.textContent=result[5];	
	}else{
	pCountLike.textContent="";
	}
	pCountLike.id="pCountLike"+result[4];
	pCountLike.style.marginLeft="20px";
	pCountLike.style.marginTop= "-7px";
	divActionCmt.appendChild(pCountLike);
	divActionCmt.appendChild(iHeart);
	const divCmt=document.createElement("div");
	if(result[4]!= null){
		divCmt.id='cmt'+result[4];
	}
	divCmt.appendChild(divParent);
	divCmt.appendChild(divActionCmt);
	divlistCmt.appendChild(divCmt);
	pCmt.addEventListener("click",createFormResultCmt);
}


function createFormResultCmt(event){
	console.log(event.target);
	const d = new Date();
	let now = d.getTime();
	const divParentCmt=document.getElementById(event.target.id);
	const userAvatar=document.getElementById("userAvatar");
	const userName=document.getElementById("userName");
	const divResultCmt=document.createElement("div");
	divResultCmt.classList.add("d-flex"); 
	divResultCmt.classList.add("flex-row");
	divResultCmt.style.marginLeft="-170px";
	const imgUser=document.createElement("img");
	imgUser.src=userAvatar.value;
	imgUser.width="32";
	imgUser.height="32";
	imgUser.classList.add("rounded-circle");
	
	const divDetailCmt=document.createElement("div");
	divDetailCmt.classList.add("detail-comment");
	const h6nameUser=document.createElement("h6");
	h6nameUser.textContent=userName.value;
	const divContaintInputAndI=document.createElement("div");
	divContaintInputAndI.classList.add("d-flex");
	const inputContent=document.createElement("input");
	inputContent.type="text";
	//inputContent.style.width="500px";
	inputContent.classList.add("form-control");
	inputContent.id="input"+now;
	divDetailCmt.appendChild(h6nameUser);
	divContaintInputAndI.appendChild(inputContent);
	const isent=document.createElement("i");
	isent.classList.add("bi");
	isent.classList.add("bi-send");
	isent.classList.add("i-send");
	divContaintInputAndI.appendChild(isent);
	divDetailCmt.appendChild(divContaintInputAndI);
	divResultCmt.appendChild(imgUser);
	divResultCmt.appendChild(divDetailCmt);
	divParentCmt.appendChild(divResultCmt);
	isent.id=now;
	var parentIdCmt=event.target.id;
	parentIdCmt=parentIdCmt.substring(12);divParentCmt
	isent.setAttribute('postId',divParentCmt.getAttribute("postId"));
	isent.setAttribute('parentCmtId',parentIdCmt);
	isent.addEventListener("click",comment2)
	inputContent.focus();
	
}
	
    
   
    function showListMessages(){
        
        const aChatIcon=document.getElementById("li-chat");
        const divListMessages=document.getElementById("div-listmessages");
        const eleDisplay=divListMessages.style.display;
        var firstClick=1;
        if(firstClick==1){
            divListMessages.style.display="block"; 
            firstClick=0;
        }else{
            if(eleDisplay=="none" || eleDisplay=="null"||eleDisplay==null){
                divListMessages.style.display="block";
            }
            else{
                divListMessages.style.display="none";
            }
        }
            
    }
    function closeLissMessages(){
        const aChatIcon=document.getElementById("li-chat");
        const divListMessages=document.getElementById("div-listmessages");
        const eleDisplay=divListMessages.style.display;
        //console.log(eleDisplay);
            if(eleDisplay=="block"){
                divListMessages.style.display="none";
            }
    }
    function closeCreateNewMessage(){
        const divCreateNewMessage=document.getElementById("div-createNewMessage");
        var eleDisplay=divCreateNewMessage.style.display;
        console.log(eleDisplay);
            if(eleDisplay=="block"||eleDisplay==""){
                divCreateNewMessage.style.display="none";
            }
    }
    function showCreateNewMessage(){
        const divCreateNewMessage=document.getElementById("div-createNewMessage");
        const divListMessages=document.getElementById("div-listmessages");
        //const inputMessage=document.getElementById("my-inputmessage");
        //inputMessage.focus();
        divCreateNewMessage.style.display="block";
        divListMessages.style.display="none";

        
    }
    function inputMessage(){
        const inputMessage=document.getElementById("my-inputmessage");
        const iSend=document.getElementById("i-send-mess");
        if(inputMessage.value!=''){
            iSend.classList.add('bi-send-fill');
            iSend.classList.remove('bi-send');
            iSend.style.color="blue";
        }else{
            iSend.classList.add('bi-send');
            iSend.classList.remove('bi-send-fill');
            iSend.style.color="black";
        }
    }

 	function focusImageInput(){
		 const iTemplatebacground=document.getElementById("i-templatebackground");
		 iTemplatebacground.style.pointerEvents="none";
		 // Tạo element input type file
  		const inputFile = document.createElement("input");
  		inputFile.type = "file";
  		inputFile.id = "listImgs";
  		inputFile.accept = "image/*";
  		inputFile.style.display = "none";

  		// Thêm event listener cho inputFile để xử lý khi chọn ảnh
  		inputFile.addEventListener("change", function() {
		
    	const file = inputFile.files[0];
    	listImage.push(inputFile.files[0]);
    	// Xử lý file được chọn (upload, hiển thị ảnh, ...)
    	const reader=new FileReader();
    	reader.readAsDataURL(file);
    	reader.onload = function(){
		const divshowImage=document.getElementById("div-showimage");
		
		var now=new Date();
		var id=now.getTime();
			const img=document.createElement("img");
    		img.width="80";
    		img.height="82";
    		img.src =reader.result;
    		img.id=id;
    		img.classList="images";
    		divshowImage.appendChild(img);
    		indexImg++;
    		isFirstImg=false;
    		const iconX=document.createElement("i");
    		iconX.classList="bi bi-x-lg";
    		iconX.style.position="relative";
    		iconX.style.top="-28px";
    		iconX.style.right="18px";
    		iconX.style.color="white";
    		iconX.style.fontWeight="bold";
    		iconX.style.cursor="pointer";
    		iconX.id=id;
    		divshowImage.appendChild(iconX);
    		iconX.addEventListener("click",function(){
				var listImages = document.getElementsByClassName("images");
				for(var img of listImages){
					if(img.id==iconX.id){
						divshowImage.removeChild(img);
						img.style.display="none";
						iconX.style.display="none";
						break;
					}
					
				}
			})
		
		}
    	

  		});

  		// Gắn inputFile vào body và click để mở trình chọn ảnh
  		document.body.appendChild(inputFile);
  		inputFile.click();
  		document.body.removeChild(inputFile);
	 }
	 
function setBackgroundStatus(id){
	backgroundId=id;
	var imgTemplateBackground=document.getElementById(id);
	var srcImg=imgTemplateBackground.src;
	var inputStatus=document.getElementById('inputStatus');
	inputStatus.style.width="430px";
	inputStatus.style.height="620px";
	inputStatus.style.backgroundImage= `url(${srcImg})`;
	inputStatus.style.textAlign= "center";
	inputStatus.style.display="flex";
	inputStatus.style.alignItems="center";
	inputStatus.style.fontWeight="bold";
	inputStatus.style.fontSize="28px";
	inputStatus.focus();
}
function openSetBackgroundTemplate(){
	const iImage=document.getElementById('i-images');
	const divImgTemplate=document.getElementById('div-templatebackground');
	if(divImgTemplate.classList.contains("d-flex")){
		divImgTemplate.classList.remove("d-flex");
		divImgTemplate.classList.remove("flex-row");
		divImgTemplate.style.display="none";
		iImage.style.pointerEvents="auto";
		iImage.style.color="#41B35D";
	}
	else if(divImgTemplate.style.display=="none"||divImgTemplate.style.display==""){
		divImgTemplate.classList.add("d-flex");
		divImgTemplate.classList.add("flex-row");
		iImage.style.pointerEvents="none";
		iImage.style.color="#D9D9D9";

		
	}
}
function openListEmotesOrListAction(isListRemote){
	var divEmotes=document.getElementById('div-listEmotes');
	var divActions=document.getElementById('div-listActions');
	console.log("hello");
	if(isListRemote=="false"){
		divActions.classList.remove("d-none");
		divActions.classList.add("d-flex");
		divEmotes.classList.remove("d-flex");
		divEmotes.classList.add("d-none");
	}else{
		divEmotes.classList.remove("d-none");
		divEmotes.classList.add("d-flex");
		divActions.classList.remove("d-flex");
		divActions.classList.add("d-none");
	}
}
function openListEmoteAction(){

	var divEmoteAction=document.getElementById('div-emoteaction');
	if(divEmoteAction.style.display=="none"||divEmoteAction.style.display==""){
		divEmoteAction.style.display="block";
	}else{
		divEmoteAction.style.display="none";
	}
}

function clickEmote(id){
	const cardTiltle=document.getElementById('card-title');
	var emote=document.getElementById(id);
	emoteId=id;
	if(cardTiltle.textContent.includes(" đang ")){
		var username=cardTiltle.textContent.substring(0, cardTiltle.textContent.indexOf("đang"));
		cardTiltle.innerHTML=username+" đang "+emote.innerHTML;
	}
	else{
		cardTiltle.innerHTML=cardTiltle.innerHTML+" đang "+emote.innerHTML;
	}
	
}
function openOrCloseTagFriend(){
	const divTagFriend=document.getElementById('div-tagfriend');
	if(divTagFriend.style.display=="none"||divTagFriend.style.display==""){
		divTagFriend.style.	display="block";
	}else{
		divTagFriend.style.	display="none";
	}
}
function clickPostStatus(){
	var formData=new FormData();
	const inputContent=document.getElementById("inputStatus");
	const inputUserId=document.getElementById("userId");
	const selectScope=document.getElementById("select-scope");
	formData.append("userId",inputUserId.value);
	if(listImage.length!=0){
		for(var img of listImage){
			formData.append("postImages",img);
		}
		
		
	}else{
		formData.append("postImages",null);
	}
	//console.table(listImage);
	inputContent.value!="" ? formData.append("content",inputContent.value) : formData.append("content","");
	backgroundId!=undefined ? formData.append("backgroundId",backgroundId) : formData.append("backgroundId","");
	emoteId!=undefined ? formData.append("emoteId",emoteId) : formData.append("emoteId","");
	formData.append("scope",selectScope.value);
	//console.log(formData);
	$.ajax({
		type:"POST",
		url:"/createstatus",
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
