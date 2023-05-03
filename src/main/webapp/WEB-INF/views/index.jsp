<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %><%-- jstl 사용하기 위한 코드 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- 프로젝트의 context 경로를 편하게 사용하기 위한 코드 --%>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8"/>
    <meta http-equiv="X-UA-Compatible"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>여행어때</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous"/>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css"/>
    <%-- 부트스트랩 사용을 위한 코드 --%>
    <!-- CSS only -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT" crossorigin="anonymous">
</head>

<body>
<!-- 상단 navbar start -->
<nav class="navbar navbar-expand-lg navbar-light bg-light shadow fixed-top">
    <div class="container">
        <a class="navbar-brand text-primary fw-bold" onclick="return load([`themesearch`,`maincarousel`]);"> <img
                src="/img/trip_logo.png" alt="여행어때" width="100"/> </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                data-bs-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false"
                aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">

            <%--main contents link start--%>
            <ul class="navbar-nav me-auto mb-lg-0">
                <li class="nav-item"><a class="nav-link" aria-current="page"
                                        onclick="return load([`themesearch`,`themetrip`]);">테마 여행</a></li>
                <li class="nav-item"><a class="nav-link" aria-current="page"
                                        onclick="return load([`myplan`]);">나의 여행 계획</a></li>
                <li class="nav-item"><a class="nav-link" aria-current="page"
                                        onclick="return load([`worldcup`]);">관광지 월드컵</a></li>
                <li class="nav-item"><a class="nav-link" aria-current="page"
                                        onclick="return load([`recommend`]);">맛집 뽑기</a></li>
                <li class="nav-item"><a class="nav-link" aria-current="page"
                                        onclick="return board(`notice`,1);">게시판</a></li>
            </ul>
            <%--main contents link end--%>

            <%--user 회원가입, 로그인, 로그아웃, 마이페이지 링크 start--%>
            <c:if test="${empty user }">
                <ul class="navbar-nav mb-2 me-2 mb-lg-0" id="navbar-logout"
                    style="display: flex; white-space: nowrap">
                    <li class="nav-item"><a class="nav-link" aria-current="page"
                                            href="#" data-bs-toggle="modal" data-bs-target="#registerModal">회원가입</a>
                    </li>
                    <li class="nav-item"><a class="nav-link" aria-current="page"
                                            href="#" data-bs-toggle="modal" data-bs-target="#loginModal">로그인</a>
                    </li>
                </ul>
            </c:if>
            <c:if test="${!empty user }">
                <ul class="navbar-nav mb-2 me-2 mb-lg-0" id="navbar-login"
                    style="display: flex; white-space: nowrap">
                    <li class="nav-item"><a class="nav-link" aria-current="page"
                                            href="/user/logout" id="logout">로그아웃</a></li>
                    <li class="nav-item"><a class="nav-link" aria-current="page"
                                            onclick="return load([`mypage`]);">
                        마이페이지</a></li>
                </ul>
            </c:if>
            <%--user 회원가입, 로그인, 로그아웃, 마이페이지 링크 start--%>

        </div>
    </div>
</nav>
<!-- 상단 navbar end -->

<!-- 회원가입 Modal start -->
<div class="modal fade" id="registerModal" data-bs-backdrop="static"
     data-bs-keyboard="false" tabindex="-1"
     aria-labelledby="staticBackdropLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">회원가입</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal"
                        aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <div class="input-form-backgroud row">
                    <form class="input-form col-md-12 mx-auto" method="post" action="/user/regist">
                        <div class="row">
                            <div class="col-md-6 mb-3">
                                <label for="regId">아이디</label>
                                <input type="text" class="form-control" id="regId" name="userId"
                                       placeholder="" value="" required/>
                                <div class="invalid-feedback">아이디를 입력해주세요.</div>
                            </div>
                            <div class="col-md-6 mb-3">
                                <label for="regName">이름</label>
                                <input type="text" class="form-control" id="regName"
                                       name="userName" placeholder="" value="" required/>
                                <div class="invalid-feedback">이름을 입력해주세요.</div>
                            </div>
                        </div>
                        <div class="mb-3">
                            <label for="regpw">비밀번호</label>
                            <input type="password" class="form-control" id="regpw"
                                   name="userPwd" placeholder="" required/>
                            <div class="invalid-feedback">비밀번호를 입력해주세요.</div>
                        </div>

                        <div class="mb-3">
                            <label for="regEmail">이메일</label>
                            <input type="email" class="form-control" id="regEmail" name="email"
                                   placeholder="you@example.com" required/>
                            <div class="invalid-feedback">이메일을 입력해주세요.</div>
                        </div>
                        <hr class="mb-4"/>
                        <div class="custom-control custom-checkbox">
                            <input type="checkbox" class="custom-control-input"
                                   id="aggrement" required/>
                            <label class="custom-control-label" for="aggrement">개인정보
                                수집 및 이용에 동의합니다.</label>
                        </div>
                        <div class="mb-4"></div>
                        <button class="btn btn-primary btn-lg btn-block" id="register">
                            가입 완료
                        </button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- 회원가입 Modal end -->

<!-- 로그인 Modal start -->
<div class="modal fade" id="loginModal" data-bs-backdrop="static"
     data-bs-keyboard="false" tabindex="-1"
     aria-labelledby="staticBackdropLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">로그인</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal"
                        aria-label="Close"></button>
            </div>
            <form action="/user/login" method="post">
                <div class="modal-body">
                    <div class="mb-3">
                        <label for="loginId">아이디</label>
                        <input type="text" class="form-control" id="loginId" name="userId"
                               placeholder="" required/>
                        <div class="invalid-feedback">아이디를 입력해주세요.</div>
                    </div>
                    <div class="mb-3">
                        <label for="loginPwd">비밀번호</label>
                        <input type="password" class="form-control" id="loginPwd"
                               name="userPwd" placeholder="" required/>
                        <div class="invalid-feedback">비밀번호를 입력해주세요.</div>
                    </div>
                </div>
                <div class="modal-footer flex">
                    <button class="btn btn-primary btn-lg btn-block" id="login">
                        로그인
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>
<!-- 로그인 Modal end -->

<!-- 상단 navbar 와 밑의 내용 spacing start -->
<div style="height: 130px"></div>
<!-- 상단 navbar 와 밑의 내용 spacing end -->

<!-- main div -->
<div id="maindiv" class="container">

    <!-- 검색창 start -->
    <div id="themesearch" class="container text-center">
        <form class="d-flex my-3">
            <select id="search-area" class="form-select me-2" name="sido">
            <option value="0" selected="">검색 할 지역 선택</option>

        </select> <select id="search-content-id" class="form-select me-2" name="type">
            <option value="0" selected>관광지 유형</option>

        </select>
            <input id="search-keyword" class="form-control me-2" type="search"
                   placeholder="검색어" aria-label="검색어" name="keyword"/>
            <input class="btn btn-outline-success" onclick="searchPlace()" value="검색"/>
        </form>
    </div>
    <!-- 검색창 end -->

    <!-- 캐러셀 start-->
    <div id="maincarousel" class="carousel slide" data-bs-ride="carousel">
        <!-- Indicators/dots -->
        <div class="carousel-indicators">
            <button type="button" data-bs-target="#maincarousel" data-bs-slide-to="0" class="active"></button>
            <button type="button" data-bs-target="#maincarousel" data-bs-slide-to="1"></button>
            <button type="button" data-bs-target="#maincarousel" data-bs-slide-to="2"></button>
            <button type="button" data-bs-target="#maincarousel" data-bs-slide-to="3"></button>
            <button type="button" data-bs-target="#maincarousel" data-bs-slide-to="4"></button>
        </div>

        <!-- The slideshow/carousel -->
        <div class="carousel-inner">
            <div class="carousel-item active">
                <a onclick="return load([`themesearch`,`themetrip`]);" class="stretched-link"> <img
                        src="/img/001.jpg" alt="테마 여행" class="d-block"
                        style="min-width: 100%; min-height: 200px"/>
                    <div class="bg-opacity-50 carousel-caption bg-light p-2 rounded-3 text-dark">
                        <h3>테마 여행</h3>
                        <p>맛집 여행, 관광 여행 등 테마별로 관광지를 확인해보세요</p>
                    </div>
                </a>
            </div>
            <div class="carousel-item">
                <a onclick="return load([`myplan`]);" class="stretched-link"> <img
                        src="/img/002.jpg" alt="나의 여행 계획" class="d-block"
                        style="min-width: 100%; min-height: 200px"/>
                    <div class="bg-opacity-50 carousel-caption bg-light p-2 rounded-3 text-dark">
                        <h3>나의 여행 계획</h3>
                        <p>나만의 여행 계획을 세우고 저장해보세요</p>
                    </div>
                </a>
            </div>
            <div class="carousel-item">
                <a onclick="return load([`worldcup`]);"  class="stretched-link">
                    <img src="/img/003.jpg" alt="관광지 월드컵"
                         class="d-block" style="min-width: 100%; min-height: 200px"/>
                    <div class="bg-opacity-50 carousel-caption bg-light p-2 rounded-3 text-dark">
                        <h3>관광지 월드컵</h3>
                        <p>우리나라의 베스트 관광지는 어디일까요?</p>
                    </div>
                </a>
            </div>
            <div class="carousel-item">
                <a onclick="return load([`recommend`]);"  class="stretched-link">
                    <img src="/img/004.jpg" alt="맛집 뽑기" class="d-block"
                         style="min-width: 100%; min-height: 200px"/>
                    <div class="bg-opacity-50 carousel-caption bg-light p-2 rounded-3 text-dark">
                        <h3>맛집 뽑기</h3>
                        <p>랜덤으로 맛집을 추천해드려요!</p>
                    </div>
                </a>
            </div>
            <div class="carousel-item">
                <a onclick="return board(`notice`,1);"  class="stretched-link">
                    <img src="/img/005.jpg" alt="게시판" class="d-block"
                         style="min-width: 100%; min-height: 200px"/>
                    <div class="opacity-75 carousel-caption bg-light p-2 rounded-3 text-dark">
                        <h3 class="opacity-100">게시판</h3>
                        <p class="opacity-100">다른 사람들의 여행 꿀팁을 확인해보세요</p>
                    </div>
                </a>
            </div>
        </div>
        <!-- Left and right controls/icons -->
        <button class="carousel-control-prev" type="button"
                data-bs-target="#maincarousel" data-bs-slide="prev">
            <span class="carousel-control-prev-icon"></span>
        </button>
        <button class="carousel-control-next" type="button"
                data-bs-target="#maincarousel" data-bs-slide="next">
            <span class="carousel-control-next-icon"></span>
        </button>
    </div>
    <!-- 캐러셀 end-->

    <!-- 마이페이지 start -->
    <div id="mypage">
        <ul class="nav nav-tabs">
            <li class="nav-item"><a class="nav-link tap active"
                                    data-bs-toggle="tab" data-bs-target="#userInfo"
                                    onclick="taplist(this)" aria-current="page" href="#">내 프로필</a></li>
            <li class="nav-item"><a class="nav-link tap"
                                    data-bs-toggle="tab" data-bs-target="#userPost"
                                    onclick="taplist(this)" href="#">작성한 글 보기</a></li>
            <li class="nav-item"><a class="nav-link tap"
                                    data-bs-toggle="tab" data-bs-target="#changeData"
                                    onclick="taplist(this)" href="#">프로필 정보 수정</a></li>
        </ul>
        <div class="tab-content" id="myTabContent">
            <div class="tab-pane fade show active" id="userInfo" role="tabpanel"
                 aria-labelledby="userInfo-tab">
                <table class="table">
                    <thead>
                    <tr>
                        <th>아이디</th>
                        <th>이름</th>
                        <th>이메일</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td id="mypageId">${user.userId}</td>
                        <td id="mypageName">${user.userName}</td>
                        <td id="mypageEmail">${user.email}</td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <div class="tab-pane fade" id="userPost" role="tabpanel"
                 aria-labelledby="userPost-tab">
                <div class="col-9" id="table-box">
                    <table class="table table-hover" id="board">
                        <thead>
                        <tr>
                            <th class="text-center">번호</th>
                            <th class="text-center">제목</th>
                            <th class="text-center">작성자</th>
                            <th class="text-center">조회수</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr style="text-align: center;">
                            <td>1</td>
                            <td>제목입니다</td>
                            <td>김석주</td>
                            <td>1</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="tab-pane fade" id="changeData" role="tabpanel"
                 aria-labelledby="changeData-tab">
                <div class="col-12">
                    <form method="post" action="/user/update" id="userUpdateForm">
                        <input type="hidden" name="userId" value="${user.userId}">
                        <table class="table table-borderless display-flex p-3"
                               style="text-align: center; vertical-align: middle">
                            <thead>
                            </thead>
                            <tbody>
                            <tr>
                                <td colspan="2">
                                    <label for="originalpw">기존 비밀번호</label>
                                    <input type="password" class="form-control" id="originalpw" name="userPwd"
                                           placeholder="" value="" required/>
                                </td>
                                <td rowspan="2">
                                    <button type="button" class="btn btn-primary btn-lg btn-block m-1"
                                            id="changePW" onclick="registUser();">비밀번호 변경
                                    </button>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <label for="newPw">새 비밀번호</label>
                                    <input type="password" class="form-control" id="newPw" name="newPwd" placeholder=""
                                           value="" required/>
                                </td>
                                <td>
                                    <label for="newPwCheck">새 비밀번호 확인</label>
                                    <input type="password" class="form-control" id="newPwCheck" placeholder=""
                                           value="" required/>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </form>

                    <div class="col-md">
                        <form method="get" action="/user/delete">
                            <input type="hidden" name="userId" value="${user.userId}">
                            <div class="custom-control custom-checkbox">
                                <input type="checkbox" class="custom-control-input"
                                       id="deleteConfirm" required/>
                                <label class="custom-control-label" for="deleteConfirm">회원 탈퇴시 저장된 회원 정보가 사라지는 것을
                                    이해했으며, 회원 탈퇴에 동의합니다.</label>
                            </div>
                            <button class="btn btn-primary btn-lg btn-danger btn-block m-1"
                                    id="deleteUser">회원 탈퇴
                            </button>
                        </form>
                    </div>
                </div>

            </div>
        </div>
    </div>
    <!-- 마이페이지 end -->

    <!-- 테마여행 start -->
    <div id="themetrip">
        <!-- Map start -->
        <div class="container text-center" style="text-align: center">
            <center>
                <div id="map" style="width: 100%; height: 500px"></div>
            </center>

        </div>

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
                </tbody>
            </table>
        </div>

        <!-- 검색 결과 end -->
    </div>
    <!-- 테마여행 end -->

    <!-- 여행계획 start -->
    <div id="myplan">
        <div class="row">
            <div class="col-6">
                <!-- 검색창 start -->
                <div class="container text-center">
                    <form class="d-flex my-3" action="${root}/main">
                        <input type="hidden" class="form-control" name="action"
                               value="searchPlan"/> <select id="plansearch-area"
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
                    </select> <select id="plansearch-content-id" class="form-select me-2" name="type">
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
                        <input id="plansearch-keyword" class="form-control me-2" type="search"
                               placeholder="검색어" aria-label="검색어" name="keyword"/>
                        <input class="btn btn-outline-success" type="submit" value="검색"/>
                    </form>
                </div>
                <!-- 검색창 end -->

                <!-- Map start -->
                <div class="container text-center" style="text-align: center">
                    <div id="planmap" style="width: 100%; height: 200px"></div>
                    <script type="text/javascript"
                            src="//dapi.kakao.com/v2/maps/sdk.js?appkey=00a97d244434a3292c2f2c25d767ace3"></script>
                </div>
                <script type="text/javascript">var container = document.getElementById('map'); //지도를 담을 영역의 DOM 레퍼런스
                var options = { //지도를 생성할 때 필요한 기본 옵션
                    center: new kakao.maps.LatLng(37.55998551, 126.9752993), //지도의 중심좌표.
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
                        var marker = new kakao.maps.Marker({position: pos});
                        var iwContent = `<div class="toast-body">${place.title}</div>`;
                        var infowindow = new kakao.maps.InfoWindow({
                            position: pos,
                            content: iwContent
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
                        <tbody id="plantrip-list">
                        <c:forEach var="place" items="${places }">
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

                    function delItem(item) {
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
                       value="savePlan"/>
                <button class="btn btn-primary btn-lg btn-block" id="save-list">
                    저장
                </button>
            </form>
        </div>
    </div>
    <!-- 여행계획 end -->

    <!-- 이상형월드컵 start -->
    <div id="worldcup">
        <!-- start -->
        <div class="row justify-content-center">
            <div class="col-12 ">
                <h2 style="text-align: center;" id="worldtitle">국내 관광지 월드컵 8강 (1/4)</h2>
            </div>
            <div class="row justify-content-center">
                <div
                        class="col-4 border border-primary rounded-3 bg-primary-subtle p-3"
                        style="text-align: center;" id="s1" onclick="select1()">

                </div>
                <div class="col-1 align-self-center">
                    <h2 style="text-align: center;">VS</h2>
                </div>
                <div
                        class="col-4 border border-primary rounded-3 bg-primary-subtle p-3"
                        style="text-align: center;" id="s2" onclick="select2()">
                </div>
            </div>
        </div>
        <script type="text/javascript">
            var worldcups = [], selected = [];
            var round = 8, step = 1;
            <c:forEach var="worldcup" items="${worldcups}">
            worldcups.push({
                id:"${worldcup.id}",
                title:"${worldcup.title}",
                img:"${worldcup.img}",
                content:"${worldcup.content}",
                wins:"${worldcup.wins}"
            });
            </c:forEach>
            show();
            console.log(worldcups);
            function shuffle(array) {
                for (let index = array.length - 1; index > 0; index--) {
                    const randomPosition = Math.floor(Math.random() * (index + 1));
                    const temporary = array[index];
                    array[index] = array[randomPosition];
                    array[randomPosition] = temporary;
                }
            }
            function show(){
                if(worldcups.length == 0){
                    if(round>1){
                        shuffle(selected);
                        worldcups = selected;
                        selected = [];
                        round/=2;
                        step = 1;
                    }
                    if(round==1){
                        location.href = `${root }/main?action=worldcup&selected=<%="${worldcups[0].id }"%>`;
                        return;
                    }
                }
                document.getElementById("worldtitle").innerHTML= (round==2)?`국내 관광지 월드컵 결승`:`국내 관광지 월드컵 <%="${round }"%>강 (<%="${step++ }"%>/<%="${round/2 }"%>)`;
                document.getElementById("s1").innerHTML= `
				<img src="${root }/assets/img/<%="${worldcups[0].img }"%>" class="img-fluid rounded-3" id="img1" />
				<h2 style="text-align: center;" id="text1"><%="${worldcups[0].content }"%></h2>`;
                document.getElementById("s2").innerHTML= `
				<img src="${root }/assets/img/<%="${worldcups[1].img }"%>" class="img-fluid rounded-3" id="img1" />
				<h2 style="text-align: center;" id="text1"><%="${worldcups[1].content }"%></h2>`;
            }
            function select1(){
                selected.push(worldcups.shift());
                worldcups.shift();
                show();
            }
            function select2(){
                worldcups.shift();
                selected.push(worldcups.shift());
                show();
            }
        </script>
        <!-- end -->
    </div>
    <!-- 이상형월드컵 end -->

    <!-- 이상형월드컵 결과 start -->
    <div id="worldcup-result">
        <div class="col-12 justify-content-center" style="text-align: center;">
            <h2 class="align-middle col-12 border border-primary bg-primary-subtle p-3" style="text-align: center;" id="worldcuptitle">🛫나의 마음 속 1등 관광지는 바로 ${selectedWorldcup.title }입니다!!!🛫</h2>

        </div>
        <div class="row justify-content-center">
            <div class="align-middle col-4 p-5 m-5" style="text-align: center;" id="sel1" >
                <img src="${root }/assets/img/${selectedWorldcup.img }" class="align-middle img-fluid rounded-3" id="img1" />
                <h2 style="text-align: center;" id="text1">${selectedWorldcup.content }</h2>
            </div>
            <div class="col-6 p-3" style="text-align: center;" id="res1" >
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
                    <tbody id="worldcup-list">
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
    </div>
    <!-- 이상형월드컵 결과 end -->

    <!-- 맛집 뽑기 start -->
    <div id="recommend">
        <h1>맛집 뽑기 기능은 나중에 추가될 예정입니다...</h1>
        <img src="/img/sad.jpeg" alt="TBA" width="500px" />
    </div>
    <!-- 맛집 뽑기 end -->

    <!-- 게시판 start -->
    <div id="boardview">
        <div class="row">
            <!-- 게시판 리스트 목록 start -->
            <div class="col-4">
                <div class="list-group" id="list-tab" role="tablist">
                    <a class="list-group-item list-group-item-action active"
                       id="board1-list" data-bs-toggle="list" href="#list-board1"
                       role="tab" aria-controls="list-board1" onclick="return board(`notice`,1);">공지사항</a> <a
                        class="list-group-item list-group-item-action" id="board2-list"
                        data-bs-toggle="list" href="#list-board1" role="tab"
                        aria-controls="list-board1" onclick="return board(`review`,1);">여행 후기</a> <a
                        class="list-group-item list-group-item-action" id="board3-list"
                        data-bs-toggle="list" href="#list-board1" role="tab"
                        aria-controls="list-board1" onclick="return board(`free`,1);">자유게시판</a> <a
                        class="list-group-item list-group-item-action" id="board4-list"
                        data-bs-toggle="list" href="#list-board1" role="tab"
                        aria-controls="list-board1" onclick="return board(`team`,1);">일행 구하기</a>
                </div>
            </div>
            <!-- 게시판 리스트 목록 end -->

            <!-- 게시판 내용 start -->
            <div class="col-8">
                <div class="tab-content" id="nav-tabContent">
                    <div class="tab-pane fade show active text-center" id="list-board1"
                         role="tabpanel" aria-labelledby="board1-list">
                        <div id = "boardRes">

                        </div>
                        <!-- 검색창 -->
                        <form method="post" action="${root}/main">
                            <input type="hidden" name="action" value="mvNoticeBoardWrite">
                            <button class="btn btn-primary" style="float: right;">글쓰기</button>
                        </form>
                        <div>
                            <select>
                                <option>제목</option>
                                <option>내용</option>
                                <option>제목+내용</option>
                            </select> <input type="text" name="trip-search">
                            <button type="button" class="btn btn-outline-primary">검색</button>
                        </div>
                    </div>
                </div>
            </div>
            <!-- 게시판 내용 end -->

        </div>
    </div>
    <!-- 게시판 end -->

</div>
<!-- main div -->

<!-- 하단 footer start -->
<footer
        class="navbar navbar-expand-lg navbar-light bg-light justify-content-end">
    <div class="container">
        <div class="row">
            <ul class="navbar-nav">
                <li><a href="#" class="nav-link text-secondary">개인정보처리방침</a></li>
                <li><a href="#" class="nav-link text-secondary">이용약관</a></li>
                <li><a href="#" class="nav-link text-secondary">오시는길</a></li>
                <li><label class="nav-link text-secondary">&copy;
                    SSAFY Corp.</label></li>
            </ul>
        </div>
    </div>
</footer>
<!-- 하단 footer end -->

</body>

<script src="/js/main.js">
</script>
<script>
    <c:if test="${empty view}">
    load(["themesearch", "maincarousel"]);
    </c:if>
    <c:if test="${!empty view}">
    load(["${view}"]);
    </c:if>

    //request 객체에 msg가 들어있을 때 해당 내용 알림창 띄우기
    <c:if test="${!empty msg}">
    alert("${msg}");
    </c:if>
</script>
<script>
</script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN"
        crossorigin="anonymous"></script>

</html>