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

	<!-- Main start -->
	<div class="container text-center">
		<!-- 그리드 -->
		<div class="row">
			<!-- 게시판 리스트 목록 start -->
			<div class="col-4">
				<div class="list-group" id="list-tab" role="tablist">
					<a class="list-group-item list-group-item-action active"
						id="board1-list" data-bs-toggle="list" href="#list-board1"
						role="tab" aria-controls="list-board1">공지사항</a> <a
						class="list-group-item list-group-item-action" id="board2-list"
						data-bs-toggle="list" href="#list-board2" role="tab"
						aria-controls="list-board2">여행 후기</a> <a
						class="list-group-item list-group-item-action" id="board3-list"
						data-bs-toggle="list" href="#list-board3" role="tab"
						aria-controls="list-board3">자유게시판</a> <a
						class="list-group-item list-group-item-action" id="board4-list"
						data-bs-toggle="list" href="#list-board4" role="tab"
						aria-controls="list-board4">일행 구하기</a>
				</div>
			</div>
			<!-- 게시판 리스트 목록 end -->

			<!-- 게시판 내용 start -->
			<div class="col-8">
				<div class="tab-content" id="nav-tabContent">
					<div class="tab-pane fade show active" id="list-board1"
						role="tabpanel" aria-labelledby="board1-list">
						<h3>공지사항</h3>
						<table class="table table-hover" id="board">
							<thead>
								<tr>
									<th>번호</th>
									<th>제목</th>
									<th>작성자</th>
									<th>조회수</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>3</td>
									<td>주말동안 공부를 열심히 해 오세요</td>
									<td>SSAFY</td>
									<td>8</td>
								</tr>
								<tr>
									<td>2</td>
									<td>이거보다 더 잘 만들어 오세요</td>
									<td>조용준</td>
									<td>11</td>
								</tr>
								<tr>
									<td>1</td>
									<td>예비군 출결 소명 관련 사항</td>
									<td>홍선주</td>
									<td>12</td>
								</tr>
								<tr>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
								</tr>
								<tr>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
								</tr>
								<tr>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
								</tr>
								<tr>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
								</tr>
								<tr>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
								</tr>
								<tr>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
								</tr>
								<tr>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
								</tr>
							</tbody>
						</table>
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
					<div class="tab-pane fade" id="list-board2" role="tabpanel"
						aria-labelledby="board2-list">
						<h3>여행 후기</h3>
						<table class="table table-hover" id="board">
							<thead>
								<tr>
									<th>번호</th>
									<th>제목</th>
									<th>작성자</th>
									<th>조회수</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>3</td>
									<td>신대방 자취방 여행 레전드 썰 풉니다</td>
									<td>김민섭</td>
									<td>119</td>
								</tr>
								<tr>
									<td>2</td>
									<td>내돈내산 김민섭 자취방 여행 솔직후기</td>
									<td>왕준영</td>
									<td>13</td>
								</tr>
								<tr>
									<td>1</td>
									<td>전주 가볼만했던곳</td>
									<td>김석주</td>
									<td>11</td>
								</tr>
								<tr>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
								</tr>
								<tr>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
								</tr>
								<tr>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
								</tr>
								<tr>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
								</tr>
								<tr>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
								</tr>
								<tr>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
								</tr>
								<tr>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
								</tr>
							</tbody>
						</table>
						<!-- 검색창 -->
						<form method="post" action="${root}/main">
							<input type="hidden" name="action" value="mvBoardWrite">
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
					<div class="tab-pane fade" id="list-board3" role="tabpanel"
						aria-labelledby="board3-list">
						<h3>자유게시판</h3>
						<table class="table table-hover" id="board">
							<thead>
								<tr>
									<th>번호</th>
									<th>제목</th>
									<th>작성자</th>
									<th>조회수</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>3</td>
									<td>주말 아침부터 뭐하는거냐..</td>
									<td>김석주</td>
									<td>1</td>
								</tr>
								<tr>
									<td>2</td>
									<td>놀러가고 싶다</td>
									<td>김석주</td>
									<td>1</td>
								</tr>
								<tr>
									<td>1</td>
									<td>심심하다</td>
									<td>김석주</td>
									<td>1</td>
								</tr>
								<tr>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
								</tr>
								<tr>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
								</tr>
								<tr>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
								</tr>
								<tr>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
								</tr>
								<tr>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
								</tr>
								<tr>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
								</tr>
								<tr>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
								</tr>
							</tbody>
						</table>
						<!-- 검색창 -->
						<form method="post" action="${root}/main">
							<input type="hidden" name="action" value="mvBoardWrite">
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
					<div class="tab-pane fade" id="list-board4" role="tabpanel"
						aria-labelledby="board4-list">
						<h3>일행 구하기</h3>
						<table class="table table-hover" id="board">
							<thead>
								<tr>
									<th>번호</th>
									<th>제목</th>
									<th>작성자</th>
									<th>조회수</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>3</td>
									<td>존잘남과 함께 여행가실분 구합니다</td>
									<td>김민섭</td>
									<td>10</td>
								</tr>
								<tr>
									<td>2</td>
									<td>같이 놀러가실분</td>
									<td>곽민규</td>
									<td>13</td>
								</tr>
								<tr>
									<td>1</td>
									<td>같이 여행가실분</td>
									<td>김석주</td>
									<td>1</td>
								</tr>
								<tr>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
								</tr>
								<tr>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
								</tr>
								<tr>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
								</tr>
								<tr>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
								</tr>
								<tr>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
								</tr>
								<tr>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
								</tr>
								<tr>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
								</tr>
							</tbody>
						</table>
						<!-- 검색창 -->
						<form method="post" action="${root}/main">
							<input type="hidden" name="action" value="mvBoardWrite">
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
	<!-- Main end -->


	<%@ include file="/include/footer.jsp"%>