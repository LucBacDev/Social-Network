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
	 <link rel='stylesheet' type='text/css' media='screen' href='../css/mycss/personalpage.css'>
    <link rel='stylesheet' type='text/css' media='screen' href='../css/mycss/adminpage.css'>
	

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
	<nav class="nav nav-underline justify-content-between">
      <a class="nav-item nav-link link-body-emphasis active" href="#">Quản lý tài khoản</a>
      <a class="nav-item nav-link link-body-emphasis" href="#">Quản lý bài viết</a>
      <a class="nav-item nav-link link-body-emphasis" href="#">Quản lý bình luận</a>
    </nav>
    <div class="col-md-3 text-end btn-header">
            <a href="/login" type="button" class="btn btn-outline-primary btn-logout  ">Đăng xuất</a>
    </div>
    </div>
    <div class="divBody">
	<h2>Danh sách người dùng bị tố cáo</h2>
		<div class="table-responsive small">
        <table class="table table-striped table-sm">
          <thead>
            <tr>
              <th scope="col">User bị tố cáo</th>
              <th scope="col">Lý do</th>
              <th scope="col">User tố cáo </th>
              <th scope="col">Hành động của admin</th>
            </tr>
          </thead>
			
						
					
          <tbody>
			<c:forEach var="report" items="${listReport}">
            <tr>
              <td><a href="/another/${report.userId}">Xem trang cá nhân bị tố cáo<a></td>
              <td>${report.reason}</td>
              <td><a href="/another/${report.reporterId}">Xem trang cá nhân tố cáo<a></td>
              <td class="d-flex"><button id="btnLockAccount${report.userId}" onclick="blockAccout(${report.userId})" type="button" class="btn btn-outline-danger" fdprocessedid="g8z3ra">
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-file-lock2" viewBox="0 0 16 16">
  <path d="M8 5a1 1 0 0 1 1 1v1H7V6a1 1 0 0 1 1-1m2 2.076V6a2 2 0 1 0-4 0v1.076c-.54.166-1 .597-1 1.224v2.4c0 .816.781 1.3 1.5 1.3h3c.719 0 1.5-.484 1.5-1.3V8.3c0-.627-.46-1.058-1-1.224"></path>
  <path d="M4 0a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h8a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2zm0 1h8a1 1 0 0 1 1 1v12a1 1 0 0 1-1 1H4a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1"></path>
</svg>
                Khóa tài khoản
              </button>
			<div class="fs-4 mb-3 d-none" id="textBlock${report.userId}">
              <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-person-lock" viewBox="0 0 16 16">
  				<path d="M11 5a3 3 0 1 1-6 0 3 3 0 0 1 6 0M8 7a2 2 0 1 0 0-4 2 2 0 0 0 0 4m0 5.996V14H3s-1 0-1-1 1-4 6-4q.845.002 1.544.107a4.5 4.5 0 0 0-.803.918A11 11 0 0 0 8 10c-2.29 0-3.516.68-4.168 1.332-.678.678-.83 1.418-.832 1.664zM9 13a1 1 0 0 1 1-1v-1a2 2 0 1 1 4 0v1a1 1 0 0 1 1 1v2a1 1 0 0 1-1 1h-4a1 1 0 0 1-1-1zm3-3a1 1 0 0 0-1 1v1h2v-1a1 1 0 0 0-1-1"></path>
			</svg>
              Đã khóa tài khoản này
            </div>
			</td>

            </tr>
            </c:forEach>
          </tbody>
        </table>
      </div>
	</div>

        <script src="../js/myjs/adminpage.js"></script>
</body>

</html>