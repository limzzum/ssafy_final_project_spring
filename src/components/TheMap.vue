<template>
  <b-container class="p-3">
    <div id="map"></div>
  </b-container>
</template>

<script>
import { mapState } from "vuex";
const placeStore = "placeStore";
export default {
  name: "TheMap",
  data() {
    return {
      map: null,
      markers: [],
      selectedMarkers: [],
      selectedImg: null,
      bounds: null,
      polyline: null,
      distanceOverlay: null,
      infoWindows: [],
      isLoading: true,
    };
  },
  computed: {
    ...mapState(placeStore, ["place", "places", "selected"]),
  },
  created() {
    const script = document.createElement("script");
    script.src = `//dapi.kakao.com/v2/maps/sdk.js?appkey=${process.env.VUE_APP_KAKAO_MAP_API_KEY}&autoload=false`;
    document.head.appendChild(script);
    script.onload = () => {
      window.kakao.maps.load(this.init);
    };
  },
  watch: {
    places: function (places) {
      if (this.isLoading) return;
      this.mark(places);
      this.selectMark(this.selected);
    },
    selected: function (places) {
      if (this.isLoading) return;
      this.mark(this.places);
      this.selectMark(places);
    },
  },
  methods: {
    init() {
      this.map = new window.kakao.maps.Map(document.getElementById("map"), {
        center: new window.kakao.maps.LatLng(37.566352778, 126.977952778),
        level: 3,
      });
      this.map.relayout();

      this.map.addControl(
        new window.kakao.maps.MapTypeControl(),
        window.kakao.maps.ControlPosition.TOPRIGHT
      );
      this.map.addControl(
        new window.kakao.maps.ZoomControl(),
        window.kakao.maps.ControlPosition.RIGHT
      );
      // this.mark(this.markSelected ? this.selected : this.places);
      this.mark(this.places);
      this.selectMark(this.selected);
      this.map.relayout();
      this.isLoading = false;
    },
    mark(places) {
      this.markers.forEach((item) => {
        item.setMap(null);
      });
      this.bounds = new window.kakao.maps.LatLngBounds();
      if (!places || places.length) return;
      mark: for (let pos of places) {
        // console.log(pos);
        for (let sel of this.selected) {
          if (pos.contentId == sel.contentId) {
            continue mark;
          }
        }
        let latlng = new window.kakao.maps.LatLng(pos.latitude, pos.longitude);
        let marker = new window.kakao.maps.Marker({
          map: this.map,
          position: latlng,
        });
        this.markers.push(marker);
        this.bounds.extend(latlng);
      }
      this.map.setBounds(this.bounds);
    },
    selectMark(places) {
      if (this.distanceOverlay) this.distanceOverlay.setVisible(false);
      this.selectedMarkers.forEach((item) => {
        item.setMap(null);
      });
      this.infoWindows.forEach((item) => {
        item.close();
      });
      if (this.polyline) this.polyline.setMap(null);
      if (!places.length) {
        if (!this.bounds.length)
          this.bounds.extend(
            new window.kakao.maps.LatLng(37.566352778, 126.977952778)
          );
        this.map.setBounds(this.bounds);
        return;
      }
      if (!this.bounds) this.bounds = new window.kakao.maps.LatLngBounds();
      this.selectedImg = new window.kakao.maps.MarkerImage(
        "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png",
        new window.kakao.maps.Size(24, 35)
      );
      let line = [];
      this.infoWindows = [];
      for (let pos of places) {
        let latlng = new window.kakao.maps.LatLng(pos.latitude, pos.longitude);
        let marker = new window.kakao.maps.Marker({
          map: this.map,
          position: latlng,
          image: this.selectedImg,
        });
        this.selectedMarkers.push(marker);
        let info = new window.kakao.maps.InfoWindow({
          position: latlng,
          content: this.infoHtml(pos.title),
        });
        info.open(this.map, marker);
        this.infoWindows.push(info);
        this.bounds.extend(latlng);
        line.push(latlng);
      }
      this.polyline = new window.kakao.maps.Polyline({
        path: line,
        strokeWeight: 5, // 선의 두께 입니다
        strokeColor: "#ff3c00", // 선의 색깔입니다
        strokeOpacity: 0.7, // 선의 불투명도 입니다 1에서 0 사이의 값이며 0에 가까울수록 투명합니다
        strokeStyle: "solid", // 선의 스타일입니다
      });
      var infoTitle = document.querySelectorAll(".info-window");
      infoTitle.forEach(function (e) {
        var w = e.offsetWidth + 10;
        var ml = w / 2;
        // e.parentElement.style.top = "82px";
        e.parentElement.style.left = "50%";
        e.parentElement.style.marginLeft = -ml + "px";
        e.parentElement.style.width = w + "px";
        e.parentElement.previousSibling.style.display = "none";
        e.parentElement.parentElement.style.border = "0px";
        e.parentElement.parentElement.style.background = "unset";
      });
      this.polyline.setMap(this.map);
      this.map.setBounds(this.bounds);
      if (places.length > 1 && this.polyline)
        this.showDistance(
          this.getTimeHTML(Math.round(this.polyline.getLength())),
          line[line.length - 1]
        );
    },
    infoHtml(str) {
      return `<span class="info-window">${
        str.length > 5 ? str.substring(0, 4) + "..." : str
      }</span>`;
    },
    showDistance(content, position) {
      if (this.distanceOverlay) {
        // 커스텀오버레이가 생성된 상태이면
        this.distanceOverlay.setVisible(true);
        // 커스텀 오버레이의 위치와 표시할 내용을 설정합니다
        this.distanceOverlay.setPosition(position);
        this.distanceOverlay.setContent(content);
      } else {
        // 커스텀 오버레이가 생성되지 않은 상태이면

        // 커스텀 오버레이를 생성하고 지도에 표시합니다
        this.distanceOverlay = new window.kakao.maps.CustomOverlay({
          map: this.map, // 커스텀오버레이를 표시할 지도입니다
          content: content, // 커스텀오버레이에 표시할 내용입니다
          position: position, // 커스텀오버레이를 표시할 위치입니다.
          xAnchor: 0,
          yAnchor: 0,
          zIndex: 3,
        });
      }
    },
    getTimeHTML(distance) {
      // 도보의 시속은 평균 4km/h 이고 도보의 분속은 67m/min입니다
      var walkkTime = (distance / 67) | 0;
      var walkHour = "",
        walkMin = "";

      // 계산한 도보 시간이 60분 보다 크면 시간으로 표시합니다
      if (walkkTime > 60) {
        walkHour =
          '<span class="number">' + Math.floor(walkkTime / 60) + "</span>시간 ";
      }
      walkMin = '<span class="number">' + (walkkTime % 60) + "</span>분";

      // 자전거의 평균 시속은 16km/h 이고 이것을 기준으로 자전거의 분속은 267m/min입니다
      var bycicleTime = (distance / 227) | 0;
      var bycicleHour = "",
        bycicleMin = "";

      // 계산한 자전거 시간이 60분 보다 크면 시간으로 표출합니다
      if (bycicleTime > 60) {
        bycicleHour =
          '<span class="number">' +
          Math.floor(bycicleTime / 60) +
          "</span>시간 ";
      }
      bycicleMin = '<span class="number">' + (bycicleTime % 60) + "</span>분";

      // 거리와 도보 시간, 자전거 시간을 가지고 HTML Content를 만들어 리턴합니다
      var content = '<ul class="dotOverlay distanceInfo">';
      content += "    <li>";
      content +=
        '        <span class="label">총거리</span><span class="number">' +
        distance +
        "</span>m";
      content += "    </li>";
      content += "    <li>";
      content += '        <span class="label">도보</span>' + walkHour + walkMin;
      content += "    </li>";
      content += "    <li>";
      content +=
        '        <span class="label">자전거</span>' + bycicleHour + bycicleMin;
      content += "    </li>";
      content += "</ul>";

      return content;
    },
  },
};
</script>

<style>
#map {
  width: 100%;
  height: 400px;
}

.info-window {
  display: block;
  background: rgba(80, 98, 127, 0.75);
  color: #fff;
  text-align: center;
  height: 24px;
  line-height: 22px;
  border-radius: 4px;
  padding: 0px 10px;
  white-space: nowrap;
}
.dot {
  overflow: hidden;
  float: left;
  width: 12px;
  height: 12px;
  background: url("https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/mini_circle.png");
}
.dotOverlay {
  position: relative;
  bottom: 10px;
  border-radius: 6px;
  border: 1px solid #ccc;
  border-bottom: 2px solid #ddd;
  float: left;
  font-size: 12px;
  padding: 5px;
  background: #fff;
}
.dotOverlay:nth-of-type(n) {
  border: 0;
  box-shadow: 0px 1px 2px #888;
}
.number {
  font-weight: bold;
  color: #ee6152;
}
.dotOverlay:after {
  content: "";
  position: absolute;
  margin-left: -6px;
  left: 50%;
  bottom: -8px;
  width: 11px;
  height: 8px;
  background: url("https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/vertex_white_small.png");
}
.distanceInfo {
  position: relative;
  top: 5px;
  left: 5px;
  list-style: none;
  background: rgba(255, 255, 255, 0.85);
  margin: 0;
}
.distanceInfo .label {
  display: inline-block;
  width: 50px;
}
.distanceInfo:after {
  content: none;
}
</style>
