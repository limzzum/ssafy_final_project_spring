<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<head>
<%@ include file="/include/head.jsp"%>
</head>

<body>
	<!-- 상단 navbar start -->
	<%@ include file="/include/nav.jsp"%>
	<!-- 상단 navbar end -->
	<!-- start -->
	<div class="row">
		<div class="col-6">
			<!-- 검색창 start -->
			<div class="container text-center">
	<form class="d-flex my-3" action="${root}/main">
		<input type="hidden" class="form-control" name="action"
			value="searchPlan" /> <select id="search-area"
			class="form-select me-2" name="sido">
			<option value="0" selected="">검색 할 지역 선택</option>
			<option value="1">서울</option>
			<option value="2">인천</option>
			<option value="3">대전</option>
			<option value="4">대구</option>
			<option value="5">광주</option>
			<option value="6">부산</option>
			<option value="7">울산</option>
			<option value="8">세종특별자치시</option>
			<option value="31">경기도</option>
			<option value="32">강원도</option>
			<option value="33">충청북도</option>
			<option value="34">충청남도</option>
			<option value="35">경상북도</option>
			<option value="36">경상남도</option>
			<option value="37">전라북도</option>
			<option value="38">전라남도</option>
			<option value="39">제주도</option>
		</select> <select id="search-content-id" class="form-select me-2" name="type">
			<option value="0" selected>관광지 유형</option>
			<option value="12">관광지</option>
			<option value="14">문화시설</option>
			<option value="15">축제공연행사</option>
			<option value="25">여행코스</option>
			<option value="28">레포츠</option>
			<option value="32">숙박</option>
			<option value="38">쇼핑</option>
			<option value="39">음식점</option>
		</select>
		<input id="search-keyword" class="form-control me-2" type="search"
			placeholder="검색어" aria-label="검색어" name="keyword" />
		<input class="btn btn-outline-success" type="submit" value="검색" />
	</form>
</div>
			<!-- 검색창 end -->

			<!-- Map start -->
			<div class="container text-center" style="text-align: center">
				<center>
					<div id="map" style="width: 100%; height: 200px"></div>
				</center>
				<script type="text/javascript"
					src="//dapi.kakao.com/v2/maps/sdk.js?appkey=00a97d244434a3292c2f2c25d767ace3"></script>
			</div><script type="text/javascript">var container = document.getElementById('map'); //지도를 담을 영역의 DOM 레퍼런스
	var options = { //지도를 생성할 때 필요한 기본 옵션
			center: new kakao.maps.LatLng(37.55998551,126.9752993), //지도의 중심좌표.
			level: 3 //지도의 레벨(확대, 축소 정도)
		};

		var map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴
		var markers = [];</script>
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
			<!-- Map end -->

			<!-- 검색 결과 start -->
			<div class="container text-center">
				<table class="table table-striped">
					<thead>
						<tr>
							<th>대표이미지</th>
							<th>관광지명</th>
							<th>주소</th>
						</tr>
					</thead>
					<tbody id="trip-list">
					<c:forEach var = "place" items="${places }">
					<tr class="item">
					<td>
					<c:if test="${!empty place.image}">
					<img src="${place.image}" alt="" height="100px"/>
					</c:if>
					<c:if test="${empty place.image}">
					<img src="${root}/assets/img/김민섭.PNG" alt="" height="100px"/>
					</c:if>
					</td>
            <td>${place.title}</td>
            <td>${place.addr1}</td>
            </tr>
					</c:forEach>
					</tbody>
				</table>
			</div>
			<script type="text/javascript">
			document.querySelectorAll(".item").forEach(function (item) {
			    item.addEventListener("click", function () {
			      document.getElementById("my-list").innerHTML +=
			        item.innerHTML.substring(0, item.innerHTML.length - 6) +
			        `<td></td></tr>`;
			    });
			  });
			function delItem(item){
				item.outerHtml = "";
			}
			</script>
			<!-- 검색 결과 end -->
		</div>

		<form class="col-6" action="${root }/main">
			<div class="container text-center">
				<h3>나의 여행계획</h3>
				<table class="table table-striped">
					<thead>
						<tr>
							<th>대표이미지</th>
							<th>관광지명</th>
							<th>주소</th>
							<th></th>
						</tr>
					</thead>
					<tbody id="my-list"></tbody>
				</table>
			</div>
			<input type="hidden" class="form-control" name="action"
			value="savePlan" /> 
			<button class="btn btn-primary btn-lg btn-block" id="save-list">
				저장</button>
		</form>
	</div>

	<!-- end -->
	<%@ include file="/include/footer.jsp"%>