<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
<%@ include file="/include/head.jsp"%>
</head>

<body>
	<!-- 상단 navbar start -->
	<%@ include file="/include/nav.jsp"%>
	<!-- 상단 navbar end -->

	<div>
		<br>
	</div>
	<div class="container">
			<form method="post" action="${root}/main">
			<input type="hidden" name="action" value="boardNoticeWrite">
			<input type="text" style="width: 100%; margin-bottom: 10px"
				placeholder="제목을 입력해주세요" name="title">
			<textarea style="width: 100%; height: 600px;" name="content"></textarea>
			<button class="btn btn-primary">작성 완료</button>
		</form>
	</div>
	<%@ include file="/include/footer.jsp"%>