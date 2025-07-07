function showOrHidePass(){
    const inputPassword = document.getElementById('password');
    const ishowOrHidePass=document.getElementById('btn-showpass');
    if(ishowOrHidePass.classList.contains('bi-eye-slash-fill')){
        inputPassword.setAttribute("type","text");
        ishowOrHidePass.classList.remove("bi-eye-slash-fill");
        ishowOrHidePass.classList.add("bi-eye-fill");
    }else{
        inputPassword.setAttribute("type","password");
        ishowOrHidePass.classList.remove("bi-eye-fill");
        ishowOrHidePass.classList.add("bi-eye-slash-fill");
    }
}
function checkVaidateEmail(){
	const email = document.getElementById('email').value;
	const btnLogin= document.getElementById('btn-login');
	const textError= document.getElementById('text-error');
	var validateEmail=false;
	
	if(email.toLowerCase().match(/^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|.(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/))
	{
		validateEmail=true;
	}
	if(email!=""){
	if(validateEmail){
		btnLogin.disabled=false;
		textError.innerText="";
		textError.style.display="none";
	}else{
		textError.innerText="Email bạn nhập đang sai định dạng !";
		textError.style.display="block";
		btnLogin.disabled=true;
	}
	}else{
		btnLogin.disabled=false;
		textError.innerText="";
		textError.style.display="none";
	}
	
}
function login(){
	var formData = new FormData();
	formData.append("email",document.getElementById("email").value);
	formData.append("password",document.getElementById("password").value);
	const textError= document.getElementById('text-error');
	$.ajax({
		type:"POST",
		url:"/login",
		data:formData,
		processData:false,
		typeData:false,
		contentType:false,
		success:function(result){
			if(result=="success"){
				hrefOld=window.location.href;
				hrefNew=hrefOld.substring(0, 21)+"/home";
				window.location.href=hrefNew;
			}else if(result=="admin login"){
				hrefOld=window.location.href;
				hrefNew=hrefOld.substring(0, 21)+"/admin";
				window.location.href=hrefNew;
			}
			else if(result=="blockuser login"){
				hrefOld=window.location.href;
				hrefNew=hrefOld.substring(0, 21)+"/block";
				window.location.href=hrefNew;
			}
		},
		error:function(result){
			console.table(result);
			if(result.responseText=="login by new email"){
				textError.innerText="Email bạn nhập không tồn tại !";
				textError.style.display="block";
			}else if(result.responseText=="wrong password"){
				textError.innerText="Sai mật khẩu";
				textError.style.display="block";
			}else{
				textError.innerText="Có lỗi hệ thống hãy thử lại sau nhé !";
				textError.style.display="block";
			}
			
			
		}
		
	})
}

