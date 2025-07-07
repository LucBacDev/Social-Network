var locationPathName=location.pathname;

function ajaxPostForm(){
	var avatarInput = document.getElementById('avatar');
	var txtGuild= document.getElementById('text-guildline');
	var btnAddinfor= document.getElementById('btn-addinfor');
	var btnLogin= document.getElementById('btn-login');
	var formData = new FormData();
	
    formData.append('username', document.getElementById('username').value);
    formData.append('gender', document.getElementById('gender').value);
    formData.append('birthday', document.getElementById('birthday').value);
    formData.append('address', document.getElementById('address').value);
    formData.append('address2', document.getElementById('address2').value);
    formData.append('avatar', avatarInput.files[0]);
	
	
	$.ajax({
		type:"POST",
		contentType: false,
		url:location.pathname,
		contentType: false,
        processData: false,
		data:formData,
		success:function (result){
			console.log(result);
			if(result=="success"){
				txtGuild.textContent="Bạn đã thêm thông tin thành công,hãy đăng nhập lần đầu tiên nhé !";
				btnLogin.style.display="block";
				btnAddinfor.style.display="none";
			}else if(result=="error_forbidden"){
				hrefOld=window.location.href;
				hrefNew=hrefOld.substring(0, 21)+"/erorrforbidden";
				window.location.href=hrefNew;
			}else{
				hrefOld=window.location.href;
				hrefNew=hrefOld.substring(0, 21)+"/erorr";
				window.location.href=hrefNew;
			}
			
		} 

	})
}
function loginNow(){
	hrefOld=window.location.href;
	id=hrefOld.substring(31, 32);
	$.ajax({
		method:"GET",
		contentType: false,
		url:"/home"
	})
}
