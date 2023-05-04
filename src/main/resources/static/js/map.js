function initMap(container){
	let initPos = new kakao.maps.LatLng(37.55998551, 126.9752993);
	let bounds = new kakao.maps.LatLngBounds();
	bounds.extend(initPos);
	let options = { //지도를 생성할 때 필요한 기본 옵션
		center: initPos, //지도의 중심좌표.
		level: 3 //지도의 레벨(확대, 축소 정도)
	};
	let map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴
	map.setBounds(bounds);
	map.relayout();
	return map;
}

function themetrip(){
	document.querySelector("#trip-list").innerHTML="";
	load([`themesearch`,`themetrip`]);
	let container = document.getElementById('map'); //지도를 담을 영역의 DOM 레퍼런스
	initMap(container).then(item=>item.relayout());
}

async function myplan(){
	document.querySelector("#plantrip-list").innerHTML="";
	load([`myplan`]);
	let container = document.getElementById('planmap'); //지도를 담을 영역의 DOM 레퍼런스
	initMap(container).then(item=>item.relayout());
}


async function searchPlace(page){
	if(!page){
		page = "";
	}
	console.log("search")
	let sido = document.getElementById(page+"search-area").value
	let type = document.getElementById(page+"search-content-id").value
	let keyword = document.getElementById(page+"search-keyword").value

	if(!sido || !type || !keyword){
		alert("검색 조건을 확인해주세요");
		return;
	}
	let config = {
		method:"POST",
		headers:{
			"Content-Type": "application/json"
		},
		body:JSON.stringify({
			"sido":sido.toString(),
			"type":type.toString(),
			"keyword":keyword.toString()
		})
	};
	let response = await fetch("/api/place/list", config);
	let json = await response.json();
	let result = json.result

	let tripList = document.querySelector("#"+page+"trip-list")
	let container = document.getElementById(page+'map'); //지도를 담을 영역의 DOM 레퍼런스
	let map = initMap(container); //지도 생성 및 객체 리턴
	let markers = [];
	for (let marker of markers) {
		marker.setMap(null);
	}
	markers = [];
	var bounds = new kakao.maps.LatLngBounds();
	result.forEach(place =>{
		let trip = `<tr onclick="return addMyPlane(${place.id});" style="height: 110px">
                    <td><img src="${place.image}" class = "img-fluid img-thumbnail" style="max-height: 100px; max-width: 100px" onerror="this.src=/img/김민섭.PNG"/></td>
                    <td>${place.title}</td>
                    <td>${place.addr1}</td>`;
		let suffix = page?`</tr>`:`<td>${place.lat}</td><td>${place.lng}</td></tr>`;
		tripList.innerHTML += trip+suffix;
		let pos = new kakao.maps.LatLng(place.lat, place.lng);
		let marker = new kakao.maps.Marker({position: pos});
		let iwContent = `<div class="toast-body">${place.title}</div>`;
		let infowindow = new kakao.maps.InfoWindow({
			position: pos,
			content: iwContent
		});
		markers.push(marker);
		marker.setMap(map);
		infowindow.open(map, marker);
		bounds.extend(pos);
	})
	map.setBounds(bounds);
	map.relayout();
	// document.querySelector("#trip-list").innerHTML = tripList;
}

async function getRegion() {
	let regionUrl = "/api/place/region";
	let config = {
		method:"GET"
	};
	let response = await fetch(regionUrl, config);
	let json = await response.json();

	let regions = json.result;
	let area = document.querySelectorAll("#search-area, #plansearch-area");
	console.log(area)
	area.forEach(a => {
		regions.forEach((data) => {
			let option = document.createElement("option");
			option.value = data.code;
			option.innerHTML = data.name;
			a.append(option);
		});
	})

}

async function getContent() {
	let gugunUrl = "/api/place/content";
	let config = {
		method:"GET"
	};
	let response = await fetch(gugunUrl, config);
	let json = await response.json();

	console.log(json);
	let guguns = json.result;
	let area = document.querySelectorAll("#search-content-id, #plansearch-content-id");
	// area.innerHTML = `<option value="0" selected>구/군 선택</option>`;
	area.forEach(a=>{
		guguns.forEach((data) => {
			let option = document.createElement("option");
			option.value = data.id;
			option.innerHTML = data.content;
			a.append(option);
		});
	})

}


let myList = document.querySelector("#my-list");
async function addMyPlane(id){
	let config = {
		method:"GET"
	};
	let response = await fetch("/api/place/"+id, config);
	let json = await response.json();
	let result = json.result;
	console.log(json.result)

	// let add = `<tr>
	//                         <td><img src="\${result.image}"></td>
	//                         <td>\${result.title}</td>
	//                         <td>\${result.addr1}</td>
	//                         <td></td>
	//                   </tr>`;
	// myList.append(add);
}


// document.querySelector("#placeOne").addEventListener("click", (e) => {
//
//     myList.append()
// });

