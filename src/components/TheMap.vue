<template>
  <div id="map"></div>
</template>

<script>
import { mapState } from "vuex";
const placeStore = "placeStore";
export default {
  name: "TheMap",
  components: {},
  data() {
    return {
      map: null,
      markers: [],
    };
  },
  computed: {
    ...mapState(placeStore, ["place", "places"]),
  },
  created() {
    const script = document.createElement("script");
    script.src = `//dapi.kakao.com/v2/maps/sdk.js?appkey=${process.env.VUE_APP_KAKAO_MAP_API_KEY}&autoload=false`;
    script.onload = () => window.kakao.maps.load(this.init);
    document.head.appendChild(script);
  },
  watch: {
    places: function (places) {
      this.mark(places);
    },
  },
  methods: {
    init() {
      this.map = new window.kakao.maps.Map(document.getElementById("map"), {
        center: new window.kakao.maps.LatLng(37.566352778, 126.977952778),
        level: 3,
      });
      this.mark(this.places);
    },
    mark(places) {
      this.markers.forEach((item) => {
        item.setMap(null);
      });
      let bounds = new window.kakao.maps.LatLngBounds();
      places.forEach((pos) => {
        let latlng = new window.kakao.maps.LatLng(pos.latitude, pos.longitude);
        this.markers.push(
          new window.kakao.maps.Marker({
            map: this.map,
            position: latlng,
            title: pos.title,
          })
        );
        bounds.extend(latlng);
      });
      this.map.setBounds(bounds);
    },
  },
};
</script>

<style scoped>
#map {
  width: 100%;
  height: 400px;
}
</style>
