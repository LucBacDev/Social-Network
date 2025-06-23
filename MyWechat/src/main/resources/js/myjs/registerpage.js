function showOrHidePass(){
    const inputPassword = document.getElementById('exampleInputPassword1');
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
function showOrHidePass2(){
    const inputPassword = document.getElementById('exampleInputPassword2');
    const ishowOrHidePass=document.getElementById('btn-showpass2');
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
	const email = document.getElementById('exampleInputEmail').value;
	const btnRegister= document.getElementById('btn-register');
	const textError= document.getElementById('text-error');
	var validateEmail=false;
	
	if(email.toLowerCase().match(/^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|.(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/))
	{
		validateEmail=true;
	}
	if(email!=""){
	if(validateEmail){
		btnRegister.disabled=false;
		textError.innerText="";
		textError.style.display="none";
	}else{
		textError.innerText="Email bạn nhập đang sai định dạng !";
		textError.style.display="block";
		btnRegister.disabled=true;
	}
	}else{
		btnRegister.disabled=false;
		textError.innerText="";
		textError.style.display="none";
	}
	
}
function checkRepeatPassword(){
	const inputPassword = document.getElementById('exampleInputPassword1').value;
	const repeatPassword = document.getElementById('exampleInputPassword2').value;
	const btnRegister= document.getElementById('btn-register');
	const textError= document.getElementById('text-error');
	if(repeatPassword!=""&&repeatPassword!=null){
		if(inputPassword==repeatPassword){
		btnRegister.disabled=false;
		textError.innerText="";
		textError.style.display="none";
	}else{
		btnRegister.disabled=true;
		textError.innerText="Mật khẩu nhập lại không khớp với mật khẩu !";
		textError.style.display="block";
		
	}
	}
	
}
function checkValueInputs(){
	const textError= document.getElementById('text-error');
	const inputPassword = document.getElementById('exampleInputPassword1').value;
	const email = document.getElementById('exampleInputEmail').value;
	if(inputPassword==""||email==""){
		textError.innerText="Bạn chưa nhập email,mật khẩu hoặc chưa nhập lại mật khẩu !";
		textError.style.display="block";
		return false;
	}
	return true;
}
function submitFormregister(){
	if(checkValueInputs()){
		ajaxPost();
	}
}

function ajaxPost(){
	const inputPassword = document.getElementById('exampleInputPassword1');
	const repeatPassword = document.getElementById('exampleInputPassword2');
	const email = document.getElementById('exampleInputEmail');
	const textSuccces= document.getElementById('text-success');
	const textError= document.getElementById('text-error');
	const btnAddInfo=document.getElementById("btn-addinfo");
	const btnRegister=document.getElementById("btn-register");
	const textSuggest= document.getElementById('text-error');
	
	var formData= {
		email: document.getElementById('exampleInputEmail').value,
		password: document.getElementById('exampleInputPassword1').value
	}
	$.ajax({
		type:"POST",
		contentType:"application/json",
		url:"/register",
		data:JSON.stringify(formData),
		dataType:"json",
		success:function (result){
			console.table(result);
			if(result!=null){
			textSuccces.innerText="Bạn đã đăng ký thành công hãy thêm thông tin để kết nối với mọi người !";
			textSuccces.style.display="block";
			//inputPassword.value="";
			//repeatPassword.value="";
			//email.value="";
			btnAddInfo.style.display="block";
			btnRegister.style.display="none";
			textSuggest.style.display="none";
			btnAddInfo.href=btnAddInfo.href+result;
			} 
			
		},
		error:function(result){
			if(result.status==500){
			if(result.responseText="error by dupplicate mail")
			{
			textError.innerText="Email bạn đăng ký đã tồn tại !";
			textError.style.display="block";	
			}else{
			textError.innerText="Đã xảy ra lỗi trong quá trình đăng ký,hãy thử đăng ký lại nhé !";
			textError.style.display="block";	
			}
		}	
		}
		
	})
}
