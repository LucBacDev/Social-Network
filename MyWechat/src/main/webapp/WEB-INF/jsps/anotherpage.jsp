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
    <link rel='stylesheet' type='text/css' media='screen' href='../css/mycss/personalpage.css'>
	

    <script src="../js/jquery/jQuery.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js"
        integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy"
        crossorigin="anonymous"></script>

</head>

<body>
    <div
        class="header d-flex flex-wrap align-items-center justify-content-center justify-content-md-between  border-bottom">
        <div class="col-md-3 mb-2 mb-md-0">
            <a href="/" class="d-inline-flex link-body-emphasis text-decoration-none">
                <div class="div-logo">
                    <i class="bi bi-chat icon-logo "></i>
                    <h1 class="">Wechat</h1>
                </div>
            </a>

        </div>

        <ul class="nav col-12 col-md-auto mb-2 justify-content-center mb-md-0">
            <li><a href="/home" class="nav-link px-2 "><i class="bi bi-house-heart icons-header  "></i></a>
            </li>
            <li><a href="/friend" class="nav-link px-2 "><i class="bi bi-people  icons-header "></i></a></li>
            <li><a href="#" class="nav-link px-2 "><i class="bi bi-chat icons-header  "></i></a></li>
            <li><a href="#" class="nav-link px-2 "><i class="bi bi-play-btn icons-header  "></i></a></li>
            <li><a href="#" class="nav-link px-2 "><i class="bi bi-bell icons-header "></i></a></li>
            <li><a href="#" class="nav-link px-2 "><i class="bi bi-gear icons-header  "></i></a></li>
            <li><a href="#" id="a-mode" class="nav-link px-2 ">



                </a></li>
        </ul>

        <div class="col-md-3 text-end btn-header">
            <a href="/login" type="button" class="btn btn-outline-primary btn-logout  ">Đăng xuất</a>
        </div>
    </div>
    <div class="" style="background-color: #ffffff;">
        <div class="div-background">
            <img src="${userAnother.coverPhoto}" width="70%" height="405px">
            <button type="button" onclick="openPopup('popupAction','popupReason')" class="btn btn-change-background btn-light" fdprocessedid="jqqeqo">
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-three-dots" viewBox="0 0 16 16">
  					<path d="M3 9.5a1.5 1.5 0 1 1 0-3 1.5 1.5 0 0 1 0 3m5 0a1.5 1.5 0 1 1 0-3 1.5 1.5 0 0 1 0 3m5 0a1.5 1.5 0 1 1 0-3 1.5 1.5 0 0 1 0 3"/>
				</svg>
            </button>
			<ul class="dropdown-menu d-none div-action col-3 d-grid gap-1 p-2 rounded-3 mx-0 shadow w-220px" id="popupAction"  data-bs-theme="light">
    			<li class="dropdown-item rounded-2 " >Bỏ theo dõi</li>
    			<li class="dropdown-item rounded-2" >Yêu thích</li>
    			<li class="dropdown-item rounded-2" >Hủy kết bạn</li>
    			<li onclick="openPopup2('popupReason')"class="dropdown-item rounded-2" >Báo cáo người dùng</li>
  			</ul>
			
			<ul class="dropdown-menu d-none div-action col-3 d-grid gap-1 p-2 rounded-3 mx-0 shadow w-220px" id="popupReason"  data-bs-theme="light">
				<h6>Hãy chọn lý do báo cáo</h6>
    			<li class="dropdown-item rounded-2 " onclick="report(event)"> Tài khoản giả mạo</li>
    			<li class="dropdown-item rounded-2 " onclick="report(event)">Nội dung đăng tiêu cực</li>
    			<li class="dropdown-item rounded-2 " onclick="report(event)">Lừa đảo</li>
    			<li class="dropdown-item rounded-2 " onclick="report(event)">Vi phạm các quy định pháp luật</li>
  			</ul>
			<div class="col-5 p-5 d-none rounded div-action2 mt-3" id="popupConfirm" style="background-color:#FFFFFF">
    			<h5>Xác nhận tố cáo</h5>
    			<p class="lead" id="pReason">Bạn chắc chắn muốn tố cáo tài khoản này vì</p>
    			<div class="d-flex">
					
					<input type="button" value="Tố cáo" class="btn btn-success" onclick="reportPost()">
					<input type="button" value="Hủy" class="btn btn-danger" onclick="">
				</div>
  			</div>
        </div>

        <div class="basic-info d-flex flex-row">
            <div class="user-avatar">
                <img src="${userAnother.avatar}" alt="mdo" width="168" height="168" class="rounded-circle">
                <button type="button" class="btn btn-change-avatar btn-light" fdprocessedid="3d0eao">
                    <i class="bi bi-camera"></i>

                </button>
            </div>
            <div class="user-name">
                <h1 style="color: black; font-weight: bold;">${userAnother.username}</h1>
                <a href="#" class="text-decoration-none text-body-secondary">10K người theo dõi </a>
            </div>
            
        </div>
    </div>
	
    <div class="div-body-personalpage d-flex flex-row">
        <div class="left-navigation">
            <div class="detail-info">
                <h2>Giới thiệu</h2>
            </div>
            <div class="story">
                <p class="">A developer</p>
                
            </div>
            <div class="sub-detail-info d-flex flex-column flex-md-row p-4   align-items-center justify-content-center">
                <div class="list-group">
                    <div class="list-group-item  d-flex gap-3 py-3" aria-current="true">
                        <i class="bi bi-briefcase-fill"></i>
                        <div class="d-flex flex-row gap-2 w-100 justify-content-between">
                            <p class="mb-0">Làm việc tại </p>
                            <a href="#" class="mb-0 text-decoration-none opacity-75">
                                <h6 class="h6-subinfo" style="color: black;">FPT Software</h6>
                            </a>
                        </div>
                    </div>
                    <div class="list-group-item  d-flex gap-3 py-3" aria-current="true">
                        <i class="bi bi-mortarboard-fill"></i>
                        <div class="d-flex flex-row gap-2 w-100 justify-content-between">
                            <p class="mb-0">Học tại</p>
                            <a href="#" class="mb-0 text-decoration-none opacity-75">
                                <h6 class="h6-subinfo" style="color: black;">Đại học công nghiệp Hà Nội</h6>
                            </a>
                        </div>
                    </div>
                    <div class="list-group-item d-flex gap-3 py-3" aria-current="true">
                        <i class="bi bi-house-door-fill"></i>
                        <div class="d-flex flex-row gap-2 w-100 justify-content-between">
                            <p class="mb-0">Đến từ</p>
                            <a href="#" class="mb-0 text-decoration-none opacity-75">
                                <h6 class="h6-subinfo" style="color: black;">${userAnother.address1}</h6>
                            </a>
                        </div>
                    </div>
                    <div class="list-group-item d-flex gap-3 py-3" aria-current="true">
                        <i class="bi bi-geo-alt-fill"></i>
                        <div class="d-flex flex-row gap-2 w-100 justify-content-between">
                            <p class="mb-0">Sống tại</p>
                            <a href="#" class="mb-0 text-decoration-none opacity-75">
                                <h6 class="h6-subinfo" style="color: black;">${userAnother.address2}</h6>
                            </a>
                        </div>
                    </div>
                    <div class="list-group-item d-flex gap-3 py-3" aria-current="true">
                        <i class="bi bi-heart-fill"></i>
                        <div class="d-flex flex-row gap-2 w-100 justify-content-between">
                            <p class="mb-0">Độc thân</p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="list-img">
                <div class="title-listimg">
                    <h2>Ảnh</h2>
                </div>
                <div class="user-imgs">
                    <a href="#"><img src="../images/userImages/anh1.jfif" class="" alt="..."></a>
                    <a href="#"><img src="../images/userImages/anh1.jfif" class="" alt="..."></a>
                    <a href="#"><img src="../images/userImages/anh1.jfif" class="" alt="..."></a>
                    <a href="#"><img src="../images/userImages/anh1.jfif" class="" alt="..."></a>
                    <a href="#"><img src="../images/userImages/anh1.jfif" class="" alt="..."></a>
                    <a href="#"><img src="../images/userImages/anh1.jfif" class="" alt="..."></a>
                    <a href="#"><img src="../images/userImages/anh1.jfif" class="" alt="..."></a>
                    <a href="#"><img src="../images/userImages/anh1.jfif" class="" alt="..."></a>
                    <a href="#"><img src="../images/userImages/anh1.jfif" class="" alt="..."></a>
                </div>

            </div>
        </div>
        <div class="right-navigation">
            <div class="list-card">
                <div class="card">
                    <div class="d-flex flex-row">
                        <img src="https://github.com/mdo.png" alt="mdo" width="40" height="40"
                            class=" img-normal  rounded-circle">
                        <div class="d-flex flex-column">
                            <h5 class="card-title">Card title</h5>
                            <p class="card-text"><small class="text-body-secondary">3 mins ago</small></p>
                        </div>
                    </div>

                    <div class="card-body">

                        <p class="card-text">This is a wider card with supporting text below as a natural lead-in to
                            additional content. This content is a little bit longer.</p>

                    </div>
                    <img src="..." class="card-img-bottom" alt="...">
                    <div class="card-footer">
                        <div class="d-flex flex-row">
                            <a class="a-footer-card" href="#"><i class="bi bi-heart-fill" style="color: red;"></i>18</a>
                            <a class="a-footer-card" href="#">26 Bình luận</a>
                            <a class="a-footer-card" href="#">5 Chia sẻ</a>
                        </div>
                        <div class="btn-group">
                            <button type="button" id="btn-heart" class="btn btn-footer-card btn-outline-secondary"
                                onclick="clickHeart()" fdprocessedid="9g79xr">
                                <i class="bi bi-heart" id="i-lightheart"> Yêu thích</i>
                                <span class="visually-hidden">Button</span>
                            </button>
                            <button type="button" id="btn-create-comment"
                                class="btn btn-footer-card btn-outline-secondary" fdprocessedid="ggkv13">
                                <i class="bi bi-chat"> Bình luận</i>
                                <span class="visually-hidden">Button</span>
                                <div class="list-comments">

                                </div>
                            </button>

                            <button type="button" class="btn btn-footer-card btn-outline-secondary"
                                fdprocessedid="f8z0xn">
                                <i class="bi bi-share"> Chia sẻ</i>
                                <span class="visually-hidden">Button</span>
                            </button>
                        </div>
                        <div class="list-comments">
                            <div class="user-comment comment">
                                <div class="d-flex flex-row">
                                    <img src="https://github.com/mdo.png" alt="mdo" width="32" height="32"
                                        class="rounded-circle">
                                    <div class="detail-comment">
                                        <h6>Nguyễn Nam</h6>
                                        <p class="card-text"><small class="text-body-secondary">3 mins ago</small>
                                        </p>
                                        <p>Ảnh rất đẹp !</p>
                                    </div>

                                </div>
                                <div class="title-actioncomment d-flex flex-row">
                                    <a href="#">
                                        <p>3 phút</p>
                                    </a>
                                    <a href="#">
                                        <p>Yêu thích</p>
                                    </a>
                                    <a href="#">
                                        <p>Phản hồi</p>
                                    </a>
                                </div>
                            </div>
                            <div class="my-comment d-flex flex-row comment">
                                <img src="https://github.com/mdo.png" alt="mdo" width="32" height="32"
                                    class="rounded-circle">
                                <input type="text" class="form-control" onchange="inputComment()"
                                    onkeydown="inputComment()" placeholder="Viết bình luận..." id="my-inputcomment"
                                    aria-describedby="basic-addon1">
                                <a href="#">
                                    <i id="i-send" class="bi  bi-send"></i>
                                </a>
                            </div>

                        </div>

                    </div>
                </div>
            </div>
        </div>
		<div class="d-none">
				<input type="hidden" id="userId" value="${user.id}" />
				<input type="hidden" id="anotherId" value="${userAnother.id}" />
		</div>
        <script src="../js/myjs/personalpage.js"></script>
</body>

</html>