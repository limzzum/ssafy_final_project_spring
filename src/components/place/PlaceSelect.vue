<template>
  <b-container>
    <b-card class="text-center">
      <template #header> <h3>여행 계획 세우기</h3> </template>
      <div v-if="!selected.length">
        <b-alert show>선택된 장소가 없습니다</b-alert>
      </div>
      <b-table-simple
        hover
        class="text-center"
        style="vertical-align: middle"
        outlined
      >
        <draggable v-model="tmp" draggable=".item" tag="tbody" @end="print">
          <b-tr v-for="place in tmp" :key="place.contentId" class="item">
            <b-td>{{ place.title }}</b-td>
            <b-td
              ><b-button variant="danger" @click="unselectPlace(place)"
                >X</b-button
              ></b-td
            >
          </b-tr>
        </draggable>
      </b-table-simple>
      <div style="font-size: small">드래그해서 순서를 조정하세요</div>
      <template #footer>
        <b-row>
          <b-col>
            <b-button variant="primary" @click="regist">게시글로 등록</b-button>
          </b-col>
        </b-row>
      </template>
    </b-card>
  </b-container>
</template>

<script>
import { mapState, mapActions } from "vuex";
import draggable from "vuedraggable";
const placeStore = "placeStore";
export default {
  name: "PlaceSelected",
  components: {
    draggable,
  },
  data() {
    return {
      tmp: null,
    };
  },
  watch: {
    tmp(list) {
      this.setSelected(list);
    },
    selected(list) {
      this.tmp = list;
    },
  },
  created() {
    this.tmp = this.selected;
  },
  computed: {
    ...mapState(placeStore, ["selected"]),
  },
  methods: {
    ...mapActions(placeStore, ["unselectPlace", "setSelected"]),
    print() {
      console.log(this.tmp);
    },
    regist() {
      if (!this.selected.length) {
        alert("먼저 장소를 선택해주세요");
        return;
      }
    },
  },
};
</script>

<style scoped>
.table th,
.table td {
  vertical-align: middle;
}
</style>
