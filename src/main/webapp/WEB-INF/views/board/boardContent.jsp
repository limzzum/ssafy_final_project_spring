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

	<div class="container">
		<table class="table table-bordered" id="board"
			style="text-align: center;">
			<thead>
				<tr>
					<th>제목</th>
					<td colspan="5">제목입니다.</td>
				</tr>
				<tr>
					<th>번호</th>
					<td>1</td>
					<th>작성자</th>
					<td>김석주</td>
					<th>조회수</th>
					<td>1</td>
				</tr>
			</thead>
			<tbody>
				<tr style="height: 600px; text-align: left;">
					<td colspan="6">내용입니다 내용</td>
				</tr>
			</tbody>
		</table>
		<div style="text-align: right;">
			<button type="button" class="btn btn-primary">수정</button>
			<button type="button" class="btn btn-danger">삭제</button>
		</div>

	</div>
	<!-- 게시글 내용 -->

	<%@ include file="/include/footer.jsp"%>