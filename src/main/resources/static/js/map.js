function initMap(container) {
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

function themetrip() {
    let table = document.querySelector("#trip-list");
    table.innerHTML = "";
    table.parentNode.style = "display:none";
    document.querySelector("#placepage").innerHTML="";
    load([`themesearch`, `themetrip`]);
    let container = document.getElementById('map'); //지도를 담을 영역의 DOM 레퍼런스
    initMap(container).relayout();
}

async function myplan() {
    let table = document.querySelector("#plantrip-list");
    table.innerHTML = "";
    table.parentNode.style = "display:none";
    document.querySelector("#planplacepage").innerHTML="";
    load([`myplan`]);
    let container = document.getElementById('planmap'); //지도를 담을 영역의 DOM 레퍼런스
    initMap(container).relayout();
}

async function searchPlace(plan, page = 1) {
    if (!plan) {
        plan = "";
        load([`themesearch`, `themetrip`]);
    } else {
        load([`myplan`]);
    }
    console.log("search")
    let sidoCode = document.getElementById(plan + "search-area").value;
    let contentTypeId = document.getElementById(plan + "search-content-id").value;
    let title = document.getElementById(plan + "search-keyword").value;
    let config = {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            "sidoCode": sidoCode.toString(),
            "contentTypeId": contentTypeId.toString(),
            "title": title.toString()
        })
    };
    let response = await fetch("/api/place/search/" + page, config);
    let json = await response.json();
    let result = json.result;
    console.log(json.result);
    let tripList = document.querySelector("#" + plan + "trip-list");
    tripList.parentNode.style = "display:inline";
    tripList.innerHTML = "";
    let pageDiv = document.querySelector("#" + plan + "placepage");
    let container = document.getElementById(plan + 'map'); //지도를 담을 영역의 DOM 레퍼런스
    let map = initMap(container); //지도 생성 및 객체 리턴
    let markers = [];
    for (let marker of markers) {
        marker.setMap(null);
    }
    markers = [];
    var bounds = new kakao.maps.LatLngBounds();
    result.forEach(place => {
        let trip = plan ?`<tr onclick="addMyPlane(${place.contentId});" style="height: 110px">`:`<tr onclick="detail(${place.contentId})" style="height: 110px">`;
        trip += `<td><img src="${place.firstImage}" class = "img-fluid img-thumbnail" style="max-height: 100px; max-width: 100px" onerror="this.src=/img/김민섭.PNG"/></td>
                <td>${place.title}</td>
                <td>${place.addr1}</td>`;
        let suffix = plan ? `</tr>` : `<td>${place.overview.length > 70 ? place.overview.substring(0, 70) + "..." : place.overview}</td></tr>`;
        tripList.innerHTML += trip + suffix;
        let pos = new kakao.maps.LatLng(place.latitude, place.longitude);
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
    pageDiv.innerHTML = pageHtml(`return searchPlace('${plan}'`, `);`, page, json.pages);
}

async function getRegion() {
    let regionUrl = "/api/place/region";
    let config = {
        method: "GET"
    };
    let response = await fetch(regionUrl, config);
    let json = await response.json();

    let regions = json.result;
    let area = document.querySelectorAll("#search-area, #plansearch-area");
    console.log(regions)
    area.forEach(a => {
        regions.forEach((data) => {
            let option = document.createElement("option");
            option.value = data.sidoCode;
            option.innerHTML = data.sidoName;
            a.append(option);
        });
    })

}

async function getContent() {
    let gugunUrl = "/api/place/content";
    let config = {
        method: "GET"
    };
    let response = await fetch(gugunUrl, config);
    let json = await response.json();

    console.log(json);
    let guguns = json.result;
    let area = document.querySelectorAll("#search-content-id, #plansearch-content-id");
    // area.innerHTML = `<option value="0" selected>구/군 선택</option>`;
    area.forEach(a => {
        guguns.forEach((data) => {
            let option = document.createElement("option");
            option.value = data.contentTypeId;
            option.innerHTML = data.contentTypeName;
            a.append(option);
        });
    })

}


let myList = document.querySelector("#my-list");

async function addMyPlane(id) {
    let config = {
        method: "GET"
    };
    let response = await fetch("/api/place/" + id, config);
    let json = await response.json();
    let result = json.result;
    console.log(json.result)

    let add = `<tr>
	                        <td><img src="${result.firstImage}" class = "img-fluid img-thumbnail" style="max-height: 100px; max-width: 100px" onerror="this.src=/img/김민섭.PNG"/></td>
	                        <td>${result.title}</td>
	                        <td>${result.addr1}</td>
	                  </tr>
	                  <div class="plan-place" style="display:none">${result.contentId}</div>
`;

    // myList.append(add);
    document.querySelector("#my-list").innerHTML += add
}

async function detail(contentId) {
    let config = {
        method: "GET"
    };
    let response = await fetch("/api/place/" + contentId, config);
    let json = await response.json();
    let result = json.result;
    console.log(json.result)

    let add = `<div onclick="load([\`themesearch\`, \`themetrip\`]);">
	                        <div><img src= "${result.firstImage}" class = "img-fluid img-thumbnail" style="width: 100%; height: 300px" onerror="this.src=/img/김민섭.PNG"/></div>
	                        <div>이름 : ${result.title}</div>
	                        <div>주소 : ${result.addr1}</div>
	                        <div>설명 : ${result.overview}</div>
	                        <div>유형 : ${result.contentTypeName}</div>
	                        <div>지역 : ${result.sidoName}</div>
	                 </div>
	                 
`;

    // myList.append(add);
    document.querySelector("#detailPlace").innerHTML = add
    load([`tripDetailPage`]);
}



