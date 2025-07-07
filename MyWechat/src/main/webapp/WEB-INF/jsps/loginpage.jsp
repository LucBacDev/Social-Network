<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset='utf-8'>
    <meta http-equiv='X-UA-Compatible' content='IE=edge'>
    <title>Wechat</title>
	<link rel="icon" href="..\images\icon-tab\favicon2.png" type="image/png" >
    <meta name='viewport' content='width=device-width, initial-scale=1'>
    <link rel='stylesheet' type='text/css' media='screen' href='../css/bootstrap-5.3.2-dist/css/bootstrap.min.css'>
    <link rel="stylesheet" type="text/css"
        href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
    <link rel='stylesheet' type='text/css' media='screen' href='../css/mycss/loginpage.css'>
    <script src="../js/jquery/jQuery.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js"
        integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy"
        crossorigin="anonymous"></script>

</head>
<body>
    <form class="form-login">
        <div class="div-logo d-flex">
            <i class="bi bi-chat icon-logo "></i>
            <h1 class="">Wechat</h1>
        </div>
        <h1 class="h3 mb-3 fw-normal">Hãy đăng nhập !</h1>
    
        <div class="form-floating mb-3">
          <input type="email" class="form-control" id="email" onkeyup="checkVaidateEmail()" placeholder="Email" fdprocessedid="ifcdng">
          <label for="email">Email</label>
        </div>
        <div class="form-floating">
          <input type="password" class="form-control" id="password" onchange="" maxlength="12" placeholder="Password" fdprocessedid="0rh6mg">
          <label for="password">Mật khẩu</label>
          <i id="btn-showpass" onclick="showOrHidePass()" class="bi bi-eye-slash-fill"></i>
        </div>
		<p class="mb-2 " id="text-error" ></p>
        <button class="btn btn-primary w-100 py-2" id="btn-login" onclick="login()" type="button" fdprocessedid="ced5sd">Đăng nhập</button>
        <p class="mt-5 mb-3 text-body-secondary">Bạn chưa có tài khoản hãy <a href="/register">đăng ký</a> nhé !</p>
    </form>
    <script src="../js/myjs/loginpage.js"></script>
</body>
</html>