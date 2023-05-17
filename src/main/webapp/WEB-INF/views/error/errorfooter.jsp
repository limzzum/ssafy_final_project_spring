<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %><%-- jstl 사용하기 위한 코드 --%>

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