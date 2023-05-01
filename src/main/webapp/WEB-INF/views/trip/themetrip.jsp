<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" 
	 %>
<!DOCTYPE html>
<html lang="en">


<head>
<%@ include file="/include/head.jsp"%>
</head>

<body>

	<!-- 상단 navbar start -->
	<%@ include file="/include/nav.jsp"%>
	<!-- 상단 navbar end -->


	<!-- 검색창 start -->
	<%@ include file="/include/searchbar.jsp"%>
	<!-- 검색창 end -->


	<!-- Map start -->
	<div class="container text-center" style="text-align: center">
		<center>
			<div id="map" style="width: 100%; height: 500px"></div>
		</center>
		<script type="text/javascript"
			src="//dapi.kakao.com/v2/maps/sdk.js?appkey=00a97d244434a3292c2f2c25d767ace3"></script>
	</div>
	<script type="text/javascript">var container = document.getElementById('map'); //지도를 담을 영역의 DOM 레퍼런스
	var options = { //지도를 생성할 때 필요한 기본 옵션
			center: new kakao.maps.LatLng(37.55998551,126.9752993), //지도의 중심좌표.
			level: 3 //지도의 레벨(확대, 축소 정도)
		};

		var map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴
		var markers = [];</script>
	<!-- Map end -->

	<!-- 검색 결과 start -->
	<div class="container text-center">
		<table class="table table-striped">
			<thead>
				<tr>
					<th>대표이미지</th>
					<th>관광지명</th>
					<th>주소</th>
					<th>위도</th>
					<th>경도</th>
				</tr>
			</thead>
			<tbody id="trip-list">
					<c:forEach var = "place" items="${places }">
					<tr>
					<td><c:if test="${!empty place.image}">
					<img src="${place.image}" alt="" height="100px"/>
					</c:if>
					<c:if test="${empty place.image}">
					<img src="${root}/assets/img/김민섭.PNG" alt="" height="100px"/>
					</c:if></td>
            <td>${place.title}</td>
            <td>${place.addr1}</td>
            <td>${place.lat}</td>
            <td>${place.lng}</td>
            </tr>
					</c:forEach>
			</tbody>
		</table>
	</div>
		<c:if test="${!empty places}">
	<script type="text/javascript">
	for (let marker of markers) {
	    marker.setMap(null);
	  }
	  markers = [];
	  var bounds = new kakao.maps.LatLngBounds();
		<c:forEach var = "place" items="${places }">
	    var pos = new kakao.maps.LatLng(${place.lat}, ${place.lng});
	    var marker = new kakao.maps.Marker({ position: pos });
	    var iwContent = `<div class="toast-body">${place.title}</div>`;
	    var infowindow = new kakao.maps.InfoWindow({
	        position : pos, 
	        content : iwContent 
	    });
	    markers.push(marker);
	    marker.setMap(map);
	    infowindow.open(map, marker); 
	    bounds.extend(pos);
		</c:forEach>
	  map.setBounds(bounds);
	</script>


		</c:if>
	<%@ include file="/include/footer.jsp"%>