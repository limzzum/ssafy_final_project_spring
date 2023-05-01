<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container text-center">
	<form class="d-flex my-3" action="${root}/main">
		<input type="hidden" class="form-control" name="action"
			value="searchPlace" /> <select id="search-area"
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