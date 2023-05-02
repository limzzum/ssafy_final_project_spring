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
        <a class="navbar-brand text-primary fw-bold" onclick="return load(`#maincarousel`);"> <img
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
                                        href="${root }/main?action=mvtrip">테마 여행</a></li>
                <li class="nav-item"><a class="nav-link" aria-current="page"
                                        href="${root }/main?action=mvplan">나의 여행 계획</a></li>
                <li class="nav-item"><a class="nav-link" aria-current="page"
                                        href="${root }/main?action=mvworldcup">관광지 월드컵</a></li>
                <li class="nav-item"><a class="nav-link" aria-current="page"
                                        href="${root }/main?action=mvrecommend">맛집 뽑기</a></li>
                <li class="nav-item"><a class="nav-link" aria-current="page"
                                        href="${root }/main?action=mvboard">게시판</a></li>
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
                                            onclick="return load(`#mypage`);">
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

<div class="container text-center">
    <h1>잘못된 접근 방식입니다</h1>
    <a href="/" ><img src="/img/tba.jpg"/></a>
</div>


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

<script>
    //request 객체에 msg가 들어있을 때 해당 내용 알림창 띄우기
    <c:if test="${!empty msg}">
    alert("${msg}");
    </c:if>
</script>
<script src="/js/main.js">
</script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN"
        crossorigin="anonymous"></script>
</html>