function blockAccout(userId){
	var formData = new FormData();
	formData.append("userIdBeLocked",userId);
	$.ajax({
		type:"POST",
		url:"/blockuser",
		data:formData,
		processData:false,
		typeData:false,
		contentType:false,
		success:function(result){
			const btnLock =document.getElementById("btnLockAccount"+userId);
			btnLock.classList.add("d-none");
			const textBlock=document.getElementById("textBlock"+userId);
			textBlock.classList.remove("d-none");
			textBlock.classList.add("d-block");
		}
	})
}