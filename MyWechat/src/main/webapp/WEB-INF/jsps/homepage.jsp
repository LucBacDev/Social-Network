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
    <meta name='viewport' content='wid=device-wid, initial-scale=1'>
    <link rel='stylesheet' type='text/css' media='screen' href='../css/bootstrap-5.3.2-dist/css/bootstrap.min.css'>
    <link rel="stylesheet" type="text/css"
        href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
    <link rel='stylesheet' type='text/css' media='screen' href='../css/mycss/homepage.css'>
    <script src="../js/jquery/jQuery.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>

</head>

<body>
    <div class="firstchild-body">

        <div
            class="header d-flex flex-wrap align-items-center justify-content-center justify-content-md-between py-3 mb-4 border-bottom">
            <div class="col-md-3 mb-2 mb-md-0">
                <a href="/" class="d-inline-flex link-body-emphasis text-decoration-none">
                    <div class="div-logo">
                        <i class="bi bi-chat icon-logo "></i>
                        <h1 class="">Wechat</h1>
                    </div>
                </a>

            </div>

            <ul class="nav col-12 col-md-auto mb-2 justify-content-center mb-md-0">
                <li><a href="/home" class="nav-link px-2 "><i
                            class="bi bi-house-heart icons-header  "></i></a></li>
                <li><a href="/friend" class="nav-link px-2 "><i class="bi bi-people  icons-header "></i></a></li>
                <li id="li-chat" onclick="showListMessages()"><a href="#" class="nav-link px-2 "><i class="bi bi-chat icons-header  "></i></a></li>
                <li><a href=""   class="nav-link px-2 "><i class="bi bi-play-btn icons-header  "></i></a></li>
                <li><a href="#" class="nav-link px-2 "><i class="bi bi-bell icons-header "></i></a></li>
                <li><a href="#" class="nav-link px-2 "><i class="bi bi-gear icons-header  "></i></a></li>
                <li></li>
            </ul>

            <div class="col-md-3 text-end">
                <a href="/login" type="button" class="btn btn-outline-primary btn-logout  ">Đăng xuất</a>
               
            </div>
        </div>

        <div class="leftnavigation">
            <div class="div-flex">
                <form class="col-12 col-lg-auto mb-3 mb-lg-0 me-lg-3" role="search">
                    <input type="search" class="form-control " placeholder="Tìm..." aria-label="Search">
                </form>
                <div class="dropdown text-end">
                    <a class="d-block link-body-emphasis text-decoration-none" >
                       <i class="bi iSearch bi-search"></i>
                    </a>
                </div>
            </div>
            <ul class="nav ul-leftnavi ul-margin nav-pills flex-column ">

                <li>
                    <a href="/friend" class="nav-link link-body-emphasis a-flex">
                        <i class="bi  icon-nav bi-person"></i>
                        <p class="p-functionnav">Bạn bè</p>
                    </a>
                </li>
                <li>
                    <a href="#" class="nav-link   link-body-emphasis a-flex">
                        <i class="bi  icon-nav bi-people"></i>
                        <p class="p-functionnav">Nhóm</p>
                    </a>
                </li>
                <li>
                    <a href="#" class="nav-link  link-body-emphasis a-flex">
                        <i class="bi   icon-nav bi-clock-history"></i>
                        <p class="p-functionnav">Kỉ niệm</p>
                    </a>
                </li>
                <li>
                    <a href="#" class="nav-link   link-body-emphasis a-flex">
                        <i class="bi   icon-nav bi-bookmark"></i>
                        <p class="p-functionnav">Đã lưu</p>
                    </a>
                </li>
                <li>
                    <a href="#" class="nav-link  link-body-emphasis a-flex">
                        <i class="bi  icon-nav bi-play-btn"></i>
                        <p class="p-functionnav">Video</p>
                    </a>
                </li>
            </ul>

        </div>
        <div class="rightnavigation d-flex flex-column flex-md-row align-items-center justify-content-center">
            <div class="list-group">
                <h3 class="title-rightnavi">Lời mời kết bạn</h3>
                <div>
                    <a href="#" class="list-group-item list-group-item-action d-flex gap-3 py-3" aria-current="true">
                        <img src="https://github.com/mdo.png" alt="mdo" wid="60" height="60" class="rounded-circle">
                        <h4 class="mb-0">Thảo</h4>
                        <small class="opacity-50 text-nowrap">now</small>
                    </a>
                    <div class="d-flex flex-row">
                        <button type="button" id="btn-accept" class="btn btn-primary btn-friend">Đồng ý</button>
                        <button type="button" class="btn btn-light btn-friend">Xóa</button>
                    </div>
                </div>

                <h3 class="title-rightnavi">Sinh nhật</h3>
                <div class="list-group-item">
                    <i class="bi bi-gift" style="font-size: 40px;"></i>
                    <p>Hôm nay là sinh nhật của <a class="a-birtday" href="#">Tiểu Hồng</a> và <a class="a-birtday"
                            href="#">5 người khác </a>hãy chúc mừng họ</p>
                </div>


            </div>
        </div>
		
        <div class="statusForm">
            <div class="modal  modal-sheet  bg-body-secondary p-4 py-md-5" id="modal-createstatus" tabindex="-1"
                style="" role="dialog"  >
                <div class="modal-dialog">
                    <div class="modal-content rounded-4 shadow">
						<div class="modal-header d-flex flex-row">
							<h2>Tạo bài viết </h2>
							<button type="button" data-bs-dismiss="modal" class="btn btn-light"><i class="bi bi-x-lg"></i></button>
						</div> 
                        <div class="modal-body">
							<div class="d-flex flex-row mb-2" >
                    			<img src="../images/${user.avatar}" alt="mdo" wid="40" height="40" class=" img-normal  rounded-circle">
                    			<div class="d-flex flex-column scope">
                        		<h5 class="card-title" id="card-title">${user.username}</h5>
                        			<select id="select-scope" class="form-select select-scope" >
  										<option selected value="1">Công khai</option>
  										<option value="2">Bạn bè</option>
  										<option value="3">Chỉ mình tôi</option>
									</select>
                   				 </div>
                			</div>
                            <div class="form-floating mb-3">
                                <input type="text" style="height: 160px;" class="form-control rounded-3"
                                    id="inputStatus" placeholder="" fdprocessedid="h0hky">
                                <label for="inputStatus">Bạn đang nghĩ gì thế ?</label>
                            </div>
							<div id="div-showimage" class="show-image">
							</div>
							<div id="div-tagfriend">
							<nav class="navbar bg-body-tertiary">
  								<div class="container-fluid">
    							<form class="d-flex" role="search">
      							<input class="form-control me-2" type="search" placeholder="Tìm kiếm bạn bè" aria-label="Search">
      							<button class="btn btn-primary" type="submit">Search</button>
   	 							</form>
  								</div>
							</nav>
							<div id="list-taged">

							</div>
							<h6>Gợi ý</h6>
							<div class="list-group list-resultsearch">
  								<div  class="list-group-item list-group-item-action"> <img src="https://giub.com/mdo.png" alt="mdo" wid="40" height="40"
                        class="img-normal rounded-circle"> A second link item</div>
  								<div  class="list-group-item list-group-item-action"> <img src="https://giub.com/mdo.png" alt="mdo" wid="40" height="40"
                        class="img-normal rounded-circle"> A second link item</div>
  								<div  class="list-group-item list-group-item-action"> <img src="https://giub.com/mdo.png" alt="mdo" wid="40" height="40"
                        class="img-normal rounded-circle"> A second link item</div>
  								<div  class="list-group-item list-group-item-action"> <img src="https://giub.com/mdo.png" alt="mdo" wid="40" height="40"
                        class="img-normal rounded-circle"> A second link item</div>
							</div>
							</div>
							<div class="" id="div-templatebackground">
							<img src="../images/background/background1.avif" id="background1" onclick="setBackgroundStatus('background1')" class="img-template" wid="40">
							<img src="../images/background/background2.avif" id="background2" onclick="setBackgroundStatus('background2')" class="img-template" wid="40">
							<img src="../images/background/background3.avif" id="background3" onclick="setBackgroundStatus('background3')" class="img-template" wid="40">
							<img src="../images/background/background4.avif" id="background4" onclick="setBackgroundStatus('background4')" class="img-template" wid="40">
							<img src="../images/background/background5.avif" id="background5" onclick="setBackgroundStatus('background5')" class="img-template" wid="40">
							<img src="../images/background/background6.avif" id="background6" onclick="setBackgroundStatus('background6')" class="img-template" wid="40">
							<img src="../images/background/background7.avif" id="background7" onclick="setBackgroundStatus('background7')" class="img-template" wid="40">
							</div>
							<div id="div-emoteaction" style="height: 180px;" >
							<ul class="nav nav-tabs" id="myTab" role="tablist">
  								<li class="nav-item" role="presentation">
    								<button class="nav-link active" id="home-tab" onclick="openListEmotesOrListAction('true')" data-bs-toggle="tab" data-bs-target="#home-tab-pane" type="button" role="tab" aria-controls="home-tab-pane" aria-selected="true">Cảm xúc</button>
  								</li>
  								<li class="nav-item" role="presentation">
    								<button class="nav-link" id="profile-tab" onclick="openListEmotesOrListAction('false')" data-bs-toggle="tab" data-bs-target="#profile-tab-pane" type="button" role="tab" aria-controls="profile-tab-pane" aria-selected="false">Hành động</button>
  								</li>
							</ul>
								<div class="d-flex" id="div-listEmotes">
  								 
								<ul class="list-group ul-emotes" >
  									<li class="li-emotes" id="emote1" onclick="clickEmote('emote1')" ><i class="bi bi-emoji-laughing" style="color:#f0ec0e"></i> Hạnh phúc</li>
  									<li class="li-emotes" id="emote2" onclick="clickEmote('emote2')"  ><i class="bi bi-emoji-heart-eyes" style="color:#f0ec0e"></i> Yêu ích</li>
  									<li class="li-emotes" id="emote3" onclick="clickEmote('emote3')"  ><i class="bi bi-emoji-kiss" style="color:#f0ec0e" ></i> ích</li>
  									<li class="li-emotes" id="emote4" onclick="clickEmote('emote4')"  ><i class="bi bi-emoji-sunglasses" style="color:#f0ec0e" ></i> Hãnh diện</li>
  									<li class="li-emotes" id="emote5" onclick="clickEmote('emote5')"  ><i class="bi bi-emoji-smile" style="color:#f0ec0e"></i> Vui vẻ</li>
								</ul>
								<ul class="list-group ul-emotes" >
  									<li class="li-emotes" id="emote6" onclick="clickEmote('emote6')"  ><i class="bi bi-emoji-angry" style="color:#f0ec0e"></i> Tức giận</li>
  									<li class="li-emotes" id="emote7" onclick="clickEmote('emote7')"  ><i class="bi bi-emoji-expressionless" style="color:#f0ec0e"></i> Không hài lòng</li>
  									<li class="li-emotes" id="emote8" onclick="clickEmote('emote8')"  ><i class="bi bi-arrow-rough-heart" style="color:#f0ec0e"></i> Muốn yêu</li>
  									<li class="li-emotes" id="emote9" onclick="clickEmote('emote9')"  ><i class="bi bi-emoji-dizzy" style="color:#f0ec0e"></i> Ngỡ ngàng</li>
  									<li class="li-emotes" id="emote10" onclick="clickEmote('emote10')"  ><i class="bi bi-emoji-frown" style="color:#f0ec0e"></i> Buồn</li>
								</ul>
								</div>
								
								<div class="d-none" id="div-listActions">
  								 
								<ul class="list-group ul-emotes" >
  									<li class="li-emotes" id="emote11" onclick="clickEmote('emote11')"  ><i class="bi bi-sunglasses" style="color:#f0ec0e"></i> Xem phim</li>
  									<li class="li-emotes" id="emote12" onclick="clickEmote('emote12')"  ><i class="bi bi-cup-straw" style="color:#f0ec0e"></i> Uống</li>
  									<li class="li-emotes" id="emote13" onclick="clickEmote('emote13')"  ><i class="bi bi-controller" style="color:#f0ec0e" ></i> Chơi game</li>
  									<li class="li-emotes" id="emote14" onclick="clickEmote('emote14')"  ><i class="bi bi-camera-reels" style="color:#f0ec0e" ></i> Quay phim</li>
  									<li class="li-emotes" id="emote15" onclick="clickEmote('emote15')"  ><i class="bi bi-palette" style="color:#f0ec0e"></i> Vẽ tranh</li>
								</ul>
								<ul class="list-group ul-emotes" >
  									<li class="li-emotes" id="emote16" onclick="clickEmote('emote16')"  ><i class="bi bi-mortarboard" style="color:#f0ec0e"></i> Dự lễ tốt nghiệp</li>
  									<li class="li-emotes" id="emote17" onclick="clickEmote('emote17')"  ><i class="bi bi-music-note-beamed" style="color:#f0ec0e"></i> Nghe nhạc</li>
  									<li class="li-emotes" id="emote18" onclick="clickEmote('emote18')"  ><i class="bi bi-cart" style="color:#f0ec0e"></i> Mua sắm</li>
  									<li class="li-emotes" id="emote19" onclick="clickEmote('emote19')"  ><i class="bi bi-book" style="color:#f0ec0e"></i> Đọc sách</li>
  									<li class="li-emotes" id="emote20" onclick="clickEmote('emote20')"  ><i class="bi bi-binoculars" style="color:#f0ec0e"></i> Khám phá</li>
								</ul>
								</div>					
								</div>
							
                            <ul class="d-grid d-flex flex-row gap-4 my-5 list-unstyled small">
								<div class="form-control d-flex flex-row">
                                <li class=" status-icons">
                                    <i style="color:#41B35D" id="i-images" onclick="focusImageInput()" class="bi bi-image"></i>
									<input type="file" style="display:none"  id="inputImages">
                                </li>
                                <li class="status-icons">
                                    <i style="color:#1771E6" onclick="openOrCloseTagFriend()" class="bi bi-person-fill-add"></i>
                                </li>
                                <li class="status-icons">
                                    <i onclick="openListEmoteAction()" style="color:#EAB026" id="i-emotes" class="bi bi-emoji-smile"></i>
                                </li>
								<!--
                                <li class="status-icons">
                                    <i style="color:#E94F3A" class="bi bi-geo-alt"></i>
                                </li>
								-->
								<li class="status-icons">
                                    <img onclick="openSetBackgroundTemplate()" id="i-templatebackground" src="../images/icon-tab/Aa_square-2x.png">
                                </li>
								<!--
								<li class="status-icons">
                                    <i style="" class="bi bi-ree-dots"></i>
                                </li>
								-->
                            </ul>
							</div>
						<div class="modal-footer">
                            <button type="button" onclick="clickPostStatus()" class="btn btn-lg btn-primary  w-100" id="btn-close-createstatus"
                                 >Đăng</button>
                        </div>
                        </div>
						
                        
                    </div>
                </div>
            </div>
			
        </div>
		
        <div class="list-card">
            <div class="card card-status d-flex flex-row">
                <div class=" d-flex flex-row card-body card-body-avatar">
                    <a href="/personal">
                        <img src="/images/${user.avatar}" alt="mdo" wid="40" height="40"
                            class="img-miniavatar rounded-circle">
                    </a>

                    <input type="text" class="form-control inputstatus" data-bs-toggle="modal"
                        data-bs-target="#modal-createstatus" id="inputCreateStatus"
                        placeholder="Chia sẻ cảm xúc của bạn nhé, ${user.username} !">

                </div>
            </div>
            
			<c:forEach var="post" items="${listPost}"> 
            <div class="card">
                <div class="d-flex flex-row">
                    <a class="text-decoration-none" style="color:black" href="/another/${post[12]}" ><img  src="${post[1]}" alt="mdo" wid="40" height="40"
                        class=" img-normal  rounded-circle"> </a>
                    <div class="d-flex flex-column">
                        <h5 class="card-title d-flex"><a class="text-decoration-none" href="/another/${post[12]}" style="color:black" >${post[0]}</a>
						<c:if test="${!empty post[4]}">
							${post[4]}
						</c:if>
						</h5>
						
                        <p class="card-text" ><small class="text-body-secondary">${post[6]}</small></p>
                    </div>
                </div>

                <div class="card-body">
				<c:choose>
					<c:when test="${!empty  post[5]}">
						<div class="div-content-post backgroundimage-post" style="background-image:url(${post[5]})">
                    	<p id="card-content" class="card-text">${post[2]}</p>
						</div>
					</c:when>
					<c:when test="${empty  post[5]}">
						<div class="div-content-post">
                    	<p id="card-content" class="card-text">${post[2]}</p>
						</div>
					</c:when>
				</c:choose>
                </div>
				<c:choose>  
    				<c:when test="${post[3] != null &&  post[3].size() gt 1}">  
					<div class="div-postImgs d-flex">
					<c:forEach var="urlImg" items="${post[3]}">
						<img src="${urlImg}"  class="rounded img-posts  " alt="">
					</c:forEach>
					</div>
					</c:when>
					<c:when test="${post[3] != null &&  post[3].size() gt 0}">  
					<div class="div-postImgs d-flex">
					<c:forEach var="urlImg" items="${post[3]}">
						<img src="${urlImg}"  class="rounded  img-post" alt="">
					</c:forEach>
					</div>
					</c:when>
				</c:choose>
                <div class="card-footer">
                    <div class="d-flex flex-row">
						<c:choose>
							<c:when test="${post[10] lt 1 && post[9] gt 0 }">
								<a class="a-footer-card d-flex" href="#"><i class="bi bi-heart-fill" style="color: red;"></i><p id="p-countlike${post[8]}">${post[9]}</p></a>
							</c:when>
							<c:when test="${post[10] == 1 && post[9] gt 1}">
								<a class="a-footer-card d-flex" href="#"><i class="bi bi-heart-fill" style="color: red;"></i><p id="p-countlike${post[8]}">Bạn và ${post[9] - 1} người khác</p></a>
							</c:when>
							<c:when test="${post[10] == 1 && post[9] == 1 }">
								<a class="a-footer-card d-flex" href="#"><i class="bi bi-heart-fill" style="color: red;"></i><p id="p-countlike${post[8]}">Bạn </p></a>
							</c:when>
							<c:when test="${post[9] == 0 }">
								<a class="a-footer-card d-flex" href="#"><i class="bi bi-heart-fill" style="color: red;"></i><p id="p-countlike${post[8]}"></p></a>
							</c:when>
                        </c:choose>
						<c:choose>
							<c:when test="${post[11] gt 0 }">
                        	<a class="a-footer-card d-flex" href="#">${post[11]} Bình luận</a>
							</c:when>
							<c:otherwise>
							<a class="a-footer-card d-flex" href="#"> Bình luận</a>
							</c:otherwise>
						</c:choose>
                        <a class="a-footer-card d-flex" href="#">5 Chia sẻ</a>
                    </div>
                    <div class="btn-group">
                        <button type="button"  id="btn-heart${post[8]}" class="btn btn-footer-card btn-outline-secondary"
                            onclick="clickHeart('btn-heart'+${post[8]},'i-ligheart'+${post[8]})" fdprocessedid="9g79xr">
						<c:choose>
							<c:when test="${post[10] lt 1}">
							<i class="bi bi-heart" id="i-ligheart${post[8]}"> Yêu thích</i>
							</c:when>
							<c:when test="${post[10] == 1}">
							<i class="bi bi-heart-fill" style="color:red" id="i-ligheart${post[8]}"> Yêu thích</i>
							</c:when>
                        </c:choose>

                        </button>
                        <button type="button" id="btn-create-comment${post[8]}" onclick="clickIComment('btn-create-comment'+${post[8]},'my-inputcomment'+${post[8]},'list-comments'+${post[8]},${post[8]})" class="btn btn-footer-card btn-outline-secondary"
                            fdprocessedid="ggkv13">
                            <i class="bi bi-chat"> Bình luận</i>
                            <span class="visually-hidden">Button</span>
                            <div class="list-comments">

                            </div>
                        </button>

                        <button type="button" class="btn btn-footer-card btn-outline-secondary" fdprocessedid="f8z0xn">
                            <i class="bi bi-share"> Chia sẻ</i>
                            <span class="visually-hidden">Button</span>
                        </button>
                    </div>
                    <div class="list-comments" id="list-comments${post[8]}">
						<select id="filter-listcomment" class="form-select select-scope" >
  										<option selected value="1">Mới nhất</option>
  										<option value="2">Đáng chú ý</option>
  										<option value="3">Tất cả</option>
						</select>
                        <div id="list-cmt${post[8]}" class="user-comment comment">
							<c:if test="${listcmts[0]} != 0">
                            <div class="d-flex flex-row">
                                <img src="${listcmts[2]}" alt="mdo" wid="32" height="32"
                                    class="rounded-circle">
                                <div class="detail-comment">
                                    <h6>${listcmts[1]}</h6>
                                    <p>${listcmts[3]}</p>
                                </div>
							</div>
							<div class="title-actioncomment d-flex flex-row">
                                <a href="#">
                                    <p>${listcmts[4]}</p>
                                </a>
                                <a href="#">
                                    <p>Yêu ích</p>
                                </a>
                                <a href="#">
                                    <p>Phản hồi</p>
                                </a>
                            </div>
							</c:if>
                            
                            
                        </div>
						
                        <div class="my-comment d-flex flex-row comment">
                            <img src="${user.avatar}" alt="mdo" wid="32" height="32"
                                class="rounded-circle">
                            <input type="text" class="form-control" onchange="inputComment('my-inputcomment'+${post[8]},'i-send'+${post[8]})" onkeydown="inputComment('my-inputcomment'+${post[8]},'i-send'+${post[8]})" onblur="inputComment('my-inputcomment'+${post[8]},'i-send'+${post[8]})"
                                placeholder="Viết bình luận..." id="my-inputcomment${post[8]}" aria-describedby="basic-addon1">
                            
							<div  class="a-bi-send" >
                                <i id="i-send${post[8]}" onclick="comment(${post[8]},'my-inputcomment'+${post[8]})"  class="bi i-send  bi-send"></i>
                            </div>
                        </div>

                    </div>

                </div>

            </div>
			</c:forEach> 
            </div>
            <div  class=" list-messages flex-column flex-md-row p-4 gap-4 py-md-5 align-items-center justify-content-center">
                <div id="div-listmessages" class="list-group  group-messages" >
                    <div class="div-tilte-button d-flex flex-row">
                    <h2>Đoạn chat</h2>
                    <button type="button" class="btn btn-listchats btn-light btn-friend " onclick="showCreateNewMessage()"><i class="bi bi-pencil-square"></i></button>
                    <button type="button" class="btn btn-listchats btn-light btn-friend " onclick="closeLissMessages()" ><i class="bi bi-x-lg"></i></i></button>
                    </div>
                    <a href="#" class="list-group-item list-group-item-action d-flex gap-3 py-3" aria-current="true">
                        <img src="https://giub.com/mdo.png" alt="mdo" wid="40" height="40"
                            class=" img-normal  rounded-circle">
                        <div class="d-flex gap-2 w-100 justify-content-between">
                            <div>
                                <h6 class="mb-0">List group item heading</h6>
                                <p class="mb-0 opacity-75">Some placeholder content in a paragraph.</p>
                            </div>
                            <small class="opacity-50 text-nowrap">now</small>
                        </div>
                    </a>
                    <a href="#" class="list-group-item list-group-item-action d-flex gap-3 py-3" aria-current="true">
                        <img src="https://giub.com/mdo.png" alt="mdo" wid="40" height="40"
                            class=" img-normal  rounded-circle">
                        <div class="d-flex gap-2 w-100 justify-content-between">
                            <div>
                                <h6 class="mb-0">Anoer title here</h6>
                                <p class="mb-0 opacity-75">Some placeholder content in a paragraph at goes a little
                                    longer so it wraps to a new line.</p>
                            </div>
                            <small class="opacity-50 text-nowrap">3d</small>
                        </div>
                    </a>
                    <a href="#" class="list-group-item list-group-item-action d-flex gap-3 py-3" aria-current="true">
                        <img src="https://giub.com/mdo.png" alt="mdo" wid="40" height="40"
                            class=" img-normal  rounded-circle">
                        <div class="d-flex gap-2 w-100 justify-content-between">
                            <div>
                                <h6 class="mb-0">ird heading</h6>
                                <p class="mb-0 opacity-75">Some placeholder content in a paragraph.</p>
                            </div>
                            <small class="opacity-50 text-nowrap">1w</small>
                        </div>
                    </a>
                </div>
            </div>
            <div class="div-createmessage" id="div-createNewMessage">
                <div  class="list-group  group-messages" >
                    <div class="div-tilte-button d-flex flex-row">
                    <h5>Tin nhắn mới</h5>
                    <button type="button" id="btn-closenewmessage" class="btn btn-listchats btn-light " onclick="closeCreateNewMessage()" ><i class="bi bi-x-lg"></i></i></button>
                    </div>
                    <div class="input-group ">
                        <span class="input-group-text" id="basic-addon1">Đến</span>
                        <input type="text" class="form-control"  aria-describedby="basic-addon1">
                    </div>
                    <a href="#" id="a-input-mess" style="wid: 100%;" class="list-group-item list-group-item-action d-flex gap-3 py-3" aria-current="true">
                        <img src="https://giub.com/mdo.png" style="text-align: center;" alt="mdo" wid="40" height="40"
                            class=" img-normal  rounded-circle">
                            <h6 class="mb-0">Văn Quang</h6>
                    </a>   
                        <div  class="d-flex input-contentmess">
                            <input type="text" class="form-control" onchange="inputMessage()" onkeydown="inputMessage()"
                                placeholder="Nội dung tin nhắn ..." id="my-inputmessage" >
                            <a href="#">
                                <i id="i-send-mess" class="bi  bi-send"></i>
                            </a>
                        </div>
                    
                   
                    
                </div>
            </div>
			<div class="d-none">
				<input type="hidden" id="userId" value="${user.id}" />
				<input type="hidden" id="userName" value="${user.username}" />
				<input type="hidden" id="userAvatar" value="${user.avatar}" />
			</div>
			
            <script src="../js/myjs/homepage.js"></script>
</body>

</html>