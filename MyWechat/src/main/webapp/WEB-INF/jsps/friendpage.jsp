<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- import JSTL -->
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <link rel='stylesheet' type='text/css' media='screen' href='../css/mycss/friendpage.css'>

    <script src="jquery-3.7.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js"
        integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy"
        crossorigin="anonymous"></script>
	<script src="../js/jquery/jQuery.js"></script>

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
            <li><a href="" class="nav-link px-2 "><i class="bi bi-chat icons-header  "></i></a></li>
            <li><a href="" class="nav-link px-2 "><i class="bi bi-play-btn icons-header  "></i></a></li>
            <li><a href="" class="nav-link px-2 "><i class="bi bi-bell icons-header "></i></a></li>
            <li><a href="" class="nav-link px-2 "><i class="bi bi-gear icons-header  "></i></a></li>
            <li><a href="" id="a-mode" class="nav-link px-2 "></a></li>
        </ul>
        <div class="col-md-3 text-end btn-header">
            <a href="/login" type="button" class="btn btn-outline-primary btn-logout  ">Đăng xuất</a>
        </div>
    </div>

	<div class="leftnavigation">

			<div class="div-flex">
                <form class="col-12 col-lg-auto mb-3 mb-lg-0 me-lg-3" role="search">
                    <input type="search" class="form-control " placeholder="Tìm bạn" aria-label="Search">
                </form>
                <div class="dropdown text-end">
                    <a class="d-block link-body-emphasis text-decoration-none" >
                       <i class="bi iSearch bi-search"></i>
                    </a>
                </div>
            </div>
            <ul class="nav ul-leftnavi ul-margin nav-pills flex-column ">

                <li>
                    <a href="#requestaddfriend" class="nav-link link-body-emphasis a-flex">
                        <i class="bi  icon-nav bi-person-fill-up"></i>
                        <p class="p-functionnav">Lời mời kết bạn</p>
                    </a>
                </li>
                <li>
                    <a href="#suggest" class="nav-link   link-body-emphasis a-flex">
                        <i class="bi  icon-nav bi-person-plus-fill"></i>
                        <p class="p-functionnav">Gợi ý</p>
                    </a>
                </li>
                <li>
                    <a href="#" class="nav-link  link-body-emphasis a-flex">
                        <i class="bi   icon-nav bi-people-fill"></i>
                        <p class="p-functionnav">Tất cả bạn bè</p>
                    </a>
                </li>
                
                
            </ul>
        </div>
		<c:if test="${countRequestAddFriend != 0}">
		<div class="div-listaddfriends">
			<h3 id="requestaddfriend" >Lời mời kết bạn</h3>
			<div class="list-card d-flex">
			<c:forEach var="requestAF" items="${listRequestAddFriend}">
			<div class="card" >
  				<img src="${requestAF[2]}" width="200" height="232" class="card-img-top" alt="...">
  				<div class="card-body">
    				<h5 class="card-title">${requestAF[1]}</h5>
    				<p class="card-text"></p>
    				<div class="d-flex flex-column">
						<a  class="btn btn-primary" id="btnAcept${requestAF[0]}" onclick="aceptRequestAddFriend(event)">Đồng ý</a>
						<a  style="margin-top:10px" id="btnDeleteRequest${requestAF[0]}" onclick="cancelAddFriend2(event)" class="btn btn-light">Xóa</a>
						<a  class="btn d-none btn-light" id="btnInfoAcept${requestAF[0]}" >Đã chấp nhận lời mời ...</a>
						<a  class="btn d-none btn-light" id="btnInfoDelete${requestAF[0]}" >Đã xóa yêu cầu</a>
					</div>
  				</div>
			</div>
			</c:forEach>
			</div>
		</div>
		</c:if>

		<c:choose>
		<c:when test="${countRequestAddFriend != 0}">
		<div class="div-listaddfriends-3"  >
			<h3 id="suggest">Những người bạn có thể biết</h3>
			<div class="list-card d-flex">
			<c:forEach var="userInfo" items="${listInRequestAddFriend}">
			<div class="card rounded" id="card${userInfo[0]}" >
  				<img src="${userInfo[2]}" width="200" height="232" class="card-img-top" alt="...">
  				<div class="card-body">
    				<h5 class="card-title">${userInfo[1]}</h5>
					<c:if test="${userInfo[3]} gt 0">
    					<p class="card-text">${userInfo[3]} bạn chung </p>
					</c:if>
    				<div class="d-flex flex-column">
						<a  id="btnAddFriend${userInfo[0]}" onclick="addFriendRequest(event)" class="btn d-none btn-outline-primary">Thêm bạn bè</a>
						<a  id="btnDeleteAddFriend${userInfo[0]}" style="margin-top:10px" class="btn d-none btn-light">Xóa</a>
						<a  id="btnCancelAddFriend${userInfo[0]}" onclick="cancelAddFriend(event)" style="margin-top:10px" class="btn  btn-light">Hủy</a>
					</div>
  				</div>
			</div>
			</c:forEach>
			<c:forEach var="userInfo" items="${listCanAddFriend}">
			<div class="card rounded" id="card${userInfo[0]}" >
  				<img src="${userInfo[2]}" width="200" height="232" class="card-img-top" alt="...">
  				<div class="card-body">
    				<h5 class="card-title">${userInfo[1]}</h5>
					<c:if test="${userInfo[3]} gt 0">
    					<p class="card-text">${userInfo[3]} bạn chung </p>
					</c:if>
    				<div class="d-flex flex-column">
						<a  id="btnAddFriend${userInfo[0]}" onclick="addFriendRequest(event)" class="btn btn-outline-primary">Thêm bạn bè</a>
						<a  id="btnDeleteAddFriend${userInfo[0]}" style="margin-top:10px" class="btn btn-light">Xóa</a>
						<a  id="btnCancelAddFriend${userInfo[0]}" onclick="cancelAddFriend(event)" style="margin-top:10px" class="btn d-none btn-light">Hủy</a>
					</div>
  				</div>
			</div>
			</c:forEach>
		</div>
		</c:when>
		<c:when test="${countRequestAddFriend == 0}">
		<div class="div-listaddfriends-2"  >
			<h3 id="suggest">Những người bạn có thể biết</h3>
			<div class="list-card d-flex">
			<c:forEach var="userInfo" items="${listInRequestAddFriend}">
			<div class="card rounded" id="card${userInfo[0]}" >
  				<img src="${userInfo[2]}" width="200" height="232" class="card-img-top" alt="...">
  				<div class="card-body">
    				<h5 class="card-title">${userInfo[1]}</h5>
					<c:if test="${userInfo[3]} gt 0">
    					<p class="card-text">${userInfo[3]} bạn chung </p>
					</c:if>
    				<div class="d-flex flex-column">
						<a  id="btnAddFriend${userInfo[0]}" onclick="addFriendRequest(event)" class="btn d-none btn-outline-primary">Thêm bạn bè</a>
						<a  id="btnDeleteAddFriend${userInfo[0]}" style="margin-top:10px" class="btn d-none btn-light">Xóa</a>
						<a  id="btnCancelAddFriend${userInfo[0]}" onclick="cancelAddFriend(event)" style="margin-top:10px" class="btn  btn-light">Hủy</a>
					</div>
  				</div>
			</div>
			</c:forEach>
			<c:forEach var="userInfo" items="${listCanAddFriend}">
			<div class="card rounded" id="card${userInfo[0]}" >
  				<img src="${userInfo[2]}" width="200" height="232" class="card-img-top" alt="...">
  				<div class="card-body">
    				<h5 class="card-title">${userInfo[1]}</h5>
					<c:if test="${userInfo[3]} gt 0">
    					<p class="card-text">${userInfo[3]} bạn chung </p>
					</c:if>
    				<div class="d-flex flex-column">
						<a  id="btnAddFriend${userInfo[0]}" onclick="addFriendRequest(event)" class="btn btn-outline-primary">Thêm bạn bè</a>
						<a  id="btnDeleteAddFriend${userInfo[0]}" style="margin-top:10px" class="btn btn-light">Xóa</a>
						<a  id="btnCancelAddFriend${userInfo[0]}" onclick="cancelAddFriend(event)" style="margin-top:10px" class="btn d-none btn-light">Hủy</a>
					</div>
  				</div>
			</div>
			</c:forEach>
		</div>
		</c:when>
		</c:choose>
		</div>
		<c:if test="${countListFriend != 0}">
			<div class="div-listaddfriends-3">
			<h3 id="listfriend" >Danh sách bạn bè</h3>
			<div class="list-card d-flex">
			<c:forEach var="friendInfo" items="${listFriend}">
			<div class="card" >
  				<img src="${friendInfo[2]}" width="200" height="232" class="card-img-top" alt="...">
  				<div class="card-body">
    				<h5 class="card-title">${friendInfo[1]}</h5>
    				<p class="card-text"></p>
    				<div class="d-flex flex-column">
						<a  id="btnDeleteAddFriend${userInfo[0]}" style="margin-top:10px" class="btn btn-light"><i class="bi bi-chat"></i>Nhắn tin</a>
						<a  id="btnDeleteAddFriend${userInfo[0]}" style="margin-top:10px" class="btn btn-light"><i class="bi bi-person-heart"></i>Theo dõi</a>
						<a  id="btnDeleteAddFriend${userInfo[0]}" style="margin-top:10px" class="btn btn-light"><i class="bi bi-person-fill-lock">Chặn</i></a>
					</div>
  				</div>
			</div>
			</c:forEach>
			</div>
		</c:if>
		<div class="d-none">
				<input type="hidden" id="userId" value="${user.id}" />
		</div>
		
		<script src="../js/myjs/friendpage.js"></script>
</body>

</html>