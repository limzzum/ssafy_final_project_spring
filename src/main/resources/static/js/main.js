
function load(selected) {
    let contents = document.querySelectorAll("#maindiv>div");
    contents.forEach(item => {
        item.style = "display:none";
    });
    selected.forEach(item=>{
        document.querySelector("#"+item).style = "display:inline"
    })
}


function taplist(e) {
    document.querySelectorAll(".tap").forEach(function (item) {
        item.classList.remove("active");
    });
    e.classList.add("active");
}

function registUser(){
    let pw = document.querySelector("#newPw");
    let check = document.querySelector("#newPwCheck");
    if(pw.value==check.value) document.querySelector("#userUpdateForm").submit();
    else alert("새 비밀번호가 일치하지 않습니다");
}
//kakaoMap
document.write(' <script type="text/javascript"\n' +
    '                    src="//dapi.kakao.com/v2/maps/sdk.js?appkey=00a97d244434a3292c2f2c25d767ace3"></script>');
async function kakaoMap(){

    let container = document.getElementById('map'); //지도를 담을 영역의 DOM 레퍼런스
    let options = { //지도를 생성할 때 필요한 기본 옵션
        center: new kakao.maps.LatLng(37.55998551, 126.9752993), //지도의 중심좌표.
        level: 3 //지도의 레벨(확대, 축소 정도)
    };

    let map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴
    let markers = [];
    for (let marker of markers) {
        marker.setMap(null);
    }
    markers = [];
    var bounds = new kakao.maps.LatLngBounds();

}



//관광지 search

async function searchPlace(page){
    if(!page){
        page = "";
    }
    console.log("search")
    let sido = document.getElementById(page+"search-area").value
    let type = document.getElementById(page+"search-content-id").value
    let keyword = document.getElementById(page+"search-keyword").value
    console.log(sido);
    console.log(type);
    console.log(keyword);
    if(!sido || !type || !keyword){
        alert("null값 안돼요");
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
    console.log(result);
    if(!page) {
        load([`themesearch`, `themetrip`]);
    }else {
        load(['themesearch','myplan'])
    }
    let tripList = document.querySelector("#"+page+"trip-list")
    let container = document.getElementById(page+'map'); //지도를 담을 영역의 DOM 레퍼런스
    let options = { //지도를 생성할 때 필요한 기본 옵션
        center: new kakao.maps.LatLng(37.55998551, 126.9752993), //지도의 중심좌표.
        level: 3 //지도의 레벨(확대, 축소 정도)
    };

    let map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴
    let markers = [];
    for (let marker of markers) {
        marker.setMap(null);
    }
    markers = [];
    var bounds = new kakao.maps.LatLngBounds();
    result.forEach(place =>{
        let trip = `<tr>
                    <td><img src="${place.image}" alt="" height="100px" onerror="this.src=/img/김민섭.PNG"/></td>
                    <td>${place.title}</td>
                    <td>${place.addr1}</td>
                    <td>${place.lat}</td>
                    <td>${place.lng}</td>
                    </tr>`
        tripList.innerHTML += trip;
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
getRegion();


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

getContent();

document.querySelector("#trip-list").addEventListener("change", (e) => {

});


