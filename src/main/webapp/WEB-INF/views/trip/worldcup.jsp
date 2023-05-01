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

	<!-- start -->
	<div class="row justify-content-center">
		<div class="col-12 ">
			<h2 style="text-align: center;" id="title">국내 관광지 월드컵 8강 (1/4)</h2>
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
			document.getElementById("title").innerHTML= (round==2)?`국내 관광지 월드컵 결승`:`국내 관광지 월드컵 <%="${round }"%>강 (<%="${step++ }"%>/<%="${round/2 }"%>)`;
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

	<%@ include file="/include/footer.jsp"%>