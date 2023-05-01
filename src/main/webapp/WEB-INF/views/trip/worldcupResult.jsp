<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">

<head>
<%@ include file="/include/head.jsp"%>
</head>

<body>
	<!-- 상단 navbar start -->
	<%@ include file="/include/nav.jsp"%>
	<!-- 상단 navbar end -->
	
	<div class="col-12 justify-content-center" style="text-align: center;">
		<h2 class="align-middle col-12 border border-primary bg-primary-subtle p-3" style="text-align: center;" id="title">🛫나의 마음 속 1등 관광지는 바로 ${selectedWorldcup.title }입니다!!!🛫</h2>

	</div>
	<div class="row justify-content-center">
		<div class="align-middle col-4 p-5 m-5" style="text-align: center;" id="s1" >
			<img src="${root }/assets/img/${selectedWorldcup.img }" class="align-middle img-fluid rounded-3" id="img1" />
			<h2 style="text-align: center;" id="text1">${selectedWorldcup.content }</h2>
		</div>
		<div class="col-6 p-3" style="text-align: center;" id="s1" >
			<h2 style="text-align: center;">다른 사람들의 선택은...</h2>
			<table class="table table-striped">
			<thead>
				<tr>
					<th>이미지</th>
					<th>관광지명</th>
					<th>설명</th>
					<th>1등 횟수</th>
					<th>비율</th>
				</tr>
			</thead>
			<tbody id="trip-list">
					<c:forEach var = "worldcup" items="${worldcups }">
					<tr>
					<td class="align-middle"><img src="${root }/assets/img/${worldcup.img }" class="img-fluid rounded-3 img-thumbnail"  style="width:100px;" /></td>
		            <td class="align-middle">${worldcup.title}</td>
		            <td class="align-middle">${worldcup.content}</td>
		            <td class="align-middle">${worldcup.wins}</td>
		            <td class="align-middle"><fmt:formatNumber value="${(worldcup.wins/totalWin)}" type="percent"/></td>
		            </tr>
					</c:forEach>
			</tbody>
		</table>
		</div>
	</div>

	<%@ include file="/include/footer.jsp"%>