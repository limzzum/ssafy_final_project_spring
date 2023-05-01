const button = document.getElementById("btn-search");
const searchArea = document.getElementById("search-area");
const content = document.getElementById("search-content-id");
const key =
  "tfDmUSbPW7w4tFMBoa1dONgt34vD57W5tY%2FleKR9DbeoS7kVJNF%2B86jpJVI%2FsYS3Jn9DqYYjbuwAtElJVbEnhQ%3D%3D";

let areaUrl =
  "https://apis.data.go.kr/B551011/KorService1/areaCode1?serviceKey=" +
  key +
  "&numOfRows=20&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json";

// fetch(areaUrl, { method: "GET" }).then(function (response) { return response.json() }).then(function (data) { makeOption(data); });
fetch(areaUrl, { method: "GET" })
  .then((response) => response.json())
  .then((data) => makeOption(data));

function makeOption(data) {
  let areas = data.response.body.items.item;
  // console.log(areas);
  areas.forEach((area) => {
    let opt = document.createElement("option");
    opt.setAttribute("value", area.code);
    opt.appendChild(document.createTextNode(area.name));

    searchArea.appendChild(opt);
  });
}

button.addEventListener("click", async (e) => {
  e.preventDefault();
  let url = `https://apis.data.go.kr/B551011/KorService1/searchKeyword1?serviceKey=${serviceKey}&numOfRows=10&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json&listYN=Y&arrange=A`;
  let areaCode = document.getElementById("search-area").value;
  let contentTypeId = document.getElementById("search-content-id").value;
  let keyword = document.getElementById("search-keyword").value;

  if (parseInt(areaCode)) url += `&areaCode=${areaCode}`;
  if (parseInt(contentTypeId)) url += `&contentTypeId=${contentTypeId}`;
  if (!keyword) {
    alert("검색어를 입력하세요");
    return;
  } else url += `&keyword=${keyword}`;

  let response = await fetch(url);
  let json = await response.json();

  let result = json.response.body.items.item;
  console.log(result);
  maketable(result);
  updateMap(result);
});

let mapContainer = document.getElementById("map"), // 지도를 표시할 div
  mapOption = {
    center: new kakao.maps.LatLng(37.5545416306, 126.9717664185), // 지도의 중심좌표
    level: 5, // 지도의 확대 레벨
  };

let map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
let markers = [];

window.onload = function () {
  console.log("page loaded");

  let result = JSON.parse(localStorage.getItem("searchResult"));
  if (result) {
    console.log(result);
    maketable(result);
    updateMap(result);
  }
  localStorage.removeItem("searchResult");
};

const maketable = (result) => {
  const tableBody = document.getElementById("trip-list");
  tableBody.innerHTML = ``;
  let idx = 0;
  for (let item of result) {
    console.log(item.mapx);
    let img = item.firstimage ? item.firstimage : "/assets/img/김민섭.PNG";
    tableBody.innerHTML += `<tr class="item">
            <td><img src="${img}" alt="" height="100px"/></td>
            <td>${item.title}</td>
            <td>${item.addr1}</td>
        </tr>`;
    idx++;
  }
  tableLink();
};
const tableLink = (e) => {
  document.querySelectorAll(".item").forEach(function (item) {
    item.addEventListener("click", function () {
      document.getElementById("my-list").innerHTML +=
        item.innerHTML.substring(0, item.innerHTML.length - 6) +
        `<td><button class="remove-from-list btn btn-danger btn-lg btn-block">
            삭제
          </button></td></tr>`;
    });
  });
};
const updateMap = (result) => {
  for (let marker of markers) {
    marker.setMap(null);
  }
  markers = [];
  var bounds = new kakao.maps.LatLngBounds();
  for (let item of result) {
    let pos = new kakao.maps.LatLng(item.mapy, item.mapx);
    let marker = new kakao.maps.Marker({ position: pos });
    let iwContent = `<div class="toast-body">${item.title}</div>`;
    let infowindow = new kakao.maps.InfoWindow({
      position: pos,
      content: iwContent,
    });
    markers.push(marker);
    marker.setMap(map);
    infowindow.open(map, marker);
    bounds.extend(pos);
  }
  map.setBounds(bounds);
};
