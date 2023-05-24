<template>
  <b-container class="text-center">
    <div v-if="!places.length">
      <b-alert show>관광지를 검색하고 선택하세요</b-alert>
    </div>
    <b-table
      hover
      :items="places"
      :fields="fields"
      @row-clicked="clicked"
      class="text-center"
      style="vertical-align: middle"
      v-show="!detailShow"
      outlined
      v-if="places.length"
    >
      <template #cell(contentId)="data">
        <b-button variant="primary" @click="search(data.item)">검색</b-button>
      </template>
    </b-table>
    <PlacePagination v-show="!detailShow" v-if="places.length" />
    <PlaceDetail
      v-show="detailShow"
      :place="detailPlace"
      @backEmit="closeDetail"
    />
  </b-container>
</template>

<script>
import { mapState, mapActions } from "vuex";
import PlacePagination from "@/components/place/PlacePagination.vue";
import PlaceDetail from "@/components/place/PlaceDetail.vue";
const placeStore = "placeStore";

export default {
  components: {
    PlacePagination,
    PlaceDetail,
  },
  props: {
    viewDetail: Boolean,
  },
  name: "PlaceList",
  data() {
    return {
      condition: {
        contentTypeId: null,
        sidoCode: null,
        title: null,
        latitude: null,
        longitude: null,
      },
      fields: [
        { key: "title", label: "이름", tdClass: "col-3" },
        { key: "contentTypeName", label: "유형", tdClass: "col-1" },
        { key: "sidoName", label: "지역", tdClass: "col-1" },
        { key: "contentId", label: "근처 관광지", tdClass: "col-1" },
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
    ...mapActions(placeStore, ["setCondition", "searchPlace", "selectPlace"]),
    clicked(item) {
      if (this.viewDetail) {
        this.detailShow = true;
        this.detailPlace = item;
      } else {
        console.log(item);
        this.selectPlace(item);
      }
    },
    closeDetail() {
      this.detailShow = false;
    },
    search(item) {
      this.condition.latitude = item.latitude;
      this.condition.longitude = item.longitude;
      this.setCondition(this.condition);
      this.searchPlace();
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
