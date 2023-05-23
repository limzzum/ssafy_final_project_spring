<template>
  <b-container class="text-center">
    <div v-if="!places.length"><b-alert show>관광지를 검색하세요</b-alert></div>
    <b-table
      hover
      :items="places"
      :fields="fields"
      @row-clicked="viewPlace"
      class="text-center"
      style="vertical-align: middle"
      v-show="!detailShow"
      outlined
      v-if="places.length"
    >
    </b-table>
    <PlacePagenation v-show="!detailShow" v-if="places.length" />
    <PlaceDetail
      v-show="detailShow"
      :place="detailPlace"
      @backEmit="closeDetail"
    />
  </b-container>
</template>

<script>
import { mapState } from "vuex";
import PlacePagenation from "@/components/place/PlacePagenation.vue";
import PlaceDetail from "@/components/place/PlaceDetail.vue";
const placeStore = "placeStore";

export default {
  components: {
    PlacePagenation,
    PlaceDetail,
  },
  name: "PlaceList",
  data() {
    return {
      condition: {
        contentTypeId: 0,
        sidoCode: "",
        title: "",
      },
      fields: [
        { key: "title", label: "이름", tdClass: "col-3" },
        { key: "contentTypeName", label: "유형", tdClass: "col-1" },
        { key: "sidoName", label: "지역", tdClass: "col-1" },
      ],
      detailShow: false,
      detailPlace: null,
    };
  },
  computed: {
    ...mapState(placeStore, ["places"]),
  },
  created() {
    this.detailShow = false;
  },
  methods: {
    viewPlace(item) {
      this.detailShow = true;
      this.detailPlace = item;
      console.log(this.detailPlace);
    },
    closeDetail() {
      this.detailShow = false;
    },
  },
};
</script>
<style>
.table th,
.table td {
  vertical-align: middle;
}
</style>
