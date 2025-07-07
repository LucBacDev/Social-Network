function addFriendRequest(event){
	var friendId=event.target.id;
	friendId=friendId.substring(12);
	var userIdInput=document.getElementById("userId");
	var userId=userIdInput.value;
	var formData=new FormData();
	formData.append("userId",userId);
	formData.append("friendId",friendId);
	formData.append("status",2);
	$.ajax({
			type:"POST",
			url:"/addfriend",
			data:formData,
			processData:false,
			contentType:false,
			success:function(result){
				console.table(result);
				var btnAddFriend =document.getElementById("btnAddFriend"+friendId);
				var btnDeleteAddFriend = document.getElementById("btnDeleteAddFriend"+friendId);
				btnAddFriend.classList.add("d-none");
				btnDeleteAddFriend.classList.add("d-none");
				var btnCancel=document.getElementById("btnCancelAddFriend"+friendId);
				btnCancel.classList.remove("d-none");
				event.preventDefault();
			},
			error:function(result){
				console.table(result);
				
			}
		})
}

function cancelAddFriend(event){
	var friendId=event.target.id;
	friendId=friendId.substring(18);
	var userIdInput=document.getElementById("userId");
	var userId=userIdInput.value;
	var formData=new FormData();
	formData.append("userId",userId);
	formData.append("friendId",friendId);
	formData.append("status",6);
	$.ajax({
			type:"POST",
			url:"/canceladdfriend",
			data:formData,
			processData:false,
			contentType:false,
			success:function(result){
				console.table(result);
				var btnAddFriend =document.getElementById("btnAddFriend"+friendId);
				var btnDeleteAddFriend = document.getElementById("btnDeleteAddFriend"+friendId);
				btnAddFriend.classList.remove("d-none");
				btnDeleteAddFriend.classList.remove("d-none");
				var btnCancel=document.getElementById("btnCancelAddFriend"+friendId);
				btnCancel.classList.add("d-none");
				
			},
			error:function(result){
				console.table(result);
				
			}
		})
}
function cancelAddFriend2(event){
	var friendId=event.target.id;
	friendId=friendId.substring(16);
	var userIdInput=document.getElementById("userId");
	var userId=userIdInput.value;
	var formData=new FormData();
	formData.append("userId",friendId);
	formData.append("friendId",userId);
	formData.append("status",6);
	$.ajax({
			type:"POST",
			url:"/canceladdfriend",
			data:formData,
			processData:false,
			contentType:false,
			success:function(result){
				console.table(result);
				var btnInfo=document.getElementById("btnInfoDelete"+friendId);
				var btnAcept=document.getElementById("btnAcept"+friendId);
				var btnDeleteRequest=document.getElementById("btnDeleteRequest"+friendId);
				btnInfo.classList.remove("d-none");
				btnAcept.classList.add("d-none");
				btnDeleteRequest.classList.add("d-none");
				btnInfo.style.pointerEvents="none";
				
			},
			error:function(result){
				console.table(result);
				
			}
		})
}

function aceptRequestAddFriend(event){
	var friendId=event.target.id;
	friendId=friendId.substring(8);
	var userIdInput=document.getElementById("userId");
	var userId=userIdInput.value;
	var formData=new FormData();
	formData.append("userId",friendId);
	formData.append("friendId",userId);
	formData.append("status",1);
	$.ajax({
			type:"POST",
			url:"/aceptrequestaddfriend",
			data:formData,
			processData:false,
			contentType:false,
			success:function(result){
				var btnInfo=document.getElementById("btnInfoAcept"+friendId);
				var btnAcept=document.getElementById("btnAcept"+friendId);
				var btnDeleteRequest=document.getElementById("btnDeleteRequest"+friendId);
				btnInfo.classList.remove("d-none");
				btnAcept.classList.add("d-none");
				btnDeleteRequest.classList.add("d-none");
				btnInfo.style.pointerEvents="none";
			},
			error:function(result){
				console.table(result);
				
			}
		})
}
