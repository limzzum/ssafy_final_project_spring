<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
<%@ include file="/WEB-INF/views/include/head.jsp"%>
</head>

<body>
	<!-- 상단 navbar start -->
	<%@ include file="/WEB-INF/views/include/nav.jsp"%>
	<!-- 상단 navbar end -->


	<!-- 검색창 start -->
	<%@ include file="/WEB-INF/views/include/searchbar.jsp"%>
	<!-- 검색창 end -->

	<!-- 메인 캐러셀 start-->
	<div class="container text-center">
		<div id="demo" class="carousel slide" data-bs-ride="carousel">
			<!-- Indicators/dots -->
			<div class="carousel-indicators">
				<button type="button" data-bs-target="#demo" data-bs-slide-to="0" class="active"></button>
				<button type="button" data-bs-target="#demo" data-bs-slide-to="1"></button>
				<button type="button" data-bs-target="#demo" data-bs-slide-to="2"></button>
				<button type="button" data-bs-target="#demo" data-bs-slide-to="3"></button>
				<button type="button" data-bs-target="#demo" data-bs-slide-to="4"></button>
			</div>
			<!-- The slideshow/carousel -->
			<div class="carousel-inner">
				<div class="carousel-item active">
					<a href="/main?action=mvtrip" class="stretched-link"> <img
						src="/img/001.jpg" alt="테마 여행" class="d-block"
						style="min-width: 100%; min-height: 200px" />
						<div class="bg-opacity-50 carousel-caption bg-light p-2 rounded-3 text-dark">
							<h3>테마 여행</h3>
							<p>맛집 여행, 관광 여행 등 테마별로 관광지를 확인해보세요</p>
						</div>
					</a>
				</div>
				<div class="carousel-item">
					<a href="/main?action=mvplan" class="stretched-link"> <img
						src="/img/002.jpg" alt="나의 여행 계획" class="d-block"
						style="min-width: 100%; min-height: 200px" />
						<div class="bg-opacity-50 carousel-caption bg-light p-2 rounded-3 text-dark">
							<h3>나의 여행 계획</h3>
							<p>나만의 여행 계획을 세우고 저장해보세요</p>
						</div>
					</a>
				</div>
				<div class="carousel-item">
					<a href="/main?action=mvworldcup" class="stretched-link">
						<img src="/img/003.jpg" alt="관광지 월드컵"
						class="d-block" style="min-width: 100%; min-height: 200px" />
						<div class="bg-opacity-50 carousel-caption bg-light p-2 rounded-3 text-dark">
							<h3>관광지 월드컵</h3>
							<p>우리나라의 베스트 관광지는 어디일까요?</p>
						</div>
					</a>
				</div>
				<div class="carousel-item">
					<a href="/main?action=mvrecommend" class="stretched-link">
						<img src="/img/004.jpg" alt="맛집 뽑기" class="d-block"
						style="min-width: 100%; min-height: 200px" />
						<div class="bg-opacity-50 carousel-caption bg-light p-2 rounded-3 text-dark">
							<h3>맛집 뽑기</h3>
							<p>랜덤으로 맛집을 추천해드려요!</p>
						</div>
					</a>
				</div>
				<div class="carousel-item">
					<a href="/main?action=mvboard" class="stretched-link">
						<img src="/img/005.jpg" alt="게시판" class="d-block"
						style="min-width: 100%; min-height: 200px" />
						<div class="opacity-75 carousel-caption bg-light p-2 rounded-3 text-dark">
							<h3 class="opacity-100">게시판</h3>
							<p class="opacity-100">다른 사람들의 여행 꿀팁을 확인해보세요</p>
						</div>
					</a>
				</div>
			</div>
			<!-- Left and right controls/icons -->
			<button class="carousel-control-prev" type="button"
				data-bs-target="#demo" data-bs-slide="prev">
				<span class="carousel-control-prev-icon"></span>
			</button>
			<button class="carousel-control-next" type="button"
				data-bs-target="#demo" data-bs-slide="next">
				<span class="carousel-control-next-icon"></span>
			</button>
		</div>
	</div>
	<!-- 메인 캐러셀 end-->

	<%@ include file="/WEB-INF/views/include/footer.jsp"%>