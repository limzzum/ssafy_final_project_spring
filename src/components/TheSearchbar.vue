<template>
  <b-form>
    <b-row>
      <b-col cols="3">
        <b-form-select
          v-model="condition.sidoCode"
          :options="regions"
        ></b-form-select>
      </b-col>
      <b-col cols="3">
        <b-form-select
          v-model="condition.contentId"
          :options="contents"
        ></b-form-select> </b-col
      ><b-col cols="4">
        <b-form-input
          v-model="condition.content"
          type="text"
          placeholder="관광지 이름"
        ></b-form-input>
      </b-col>
      <b-col cols="2">
        <b-button class="m-1" type="button" variant="primary" @click="search"
          >검색</b-button
        >
      </b-col>
    </b-row>
  </b-form>
  <!--  <div id="themesearch" class="container text-center">-->
  <!--    <form class="d-flex my-3">-->
  <!--      <select id="search-area" class="form-select me-2" name="sidoCode">-->
  <!--        <option value="0" selected="">검색 할 지역 선택</option>-->
  <!--      </select>-->
  <!--      <select-->
  <!--        id="search-content-id"-->
  <!--        class="form-select me-2"-->
  <!--        name="contentTypeId"-->
  <!--      >-->
  <!--        <option value="0" selected>관광지 유형</option>-->
  <!--      </select>-->
  <!--      <input-->
  <!--        id="search-keyword"-->
  <!--        class="form-control me-2"-->
  <!--        type="search"-->
  <!--        placeholder="검색어"-->
  <!--        aria-label="검색어"-->
  <!--        name="title"-->
  <!--      />-->
  <!--      <input-->
  <!--        class="btn btn-outline-success"-->
  <!--        onclick="searchPlace();"-->
  <!--        value="검색"-->
  <!--      />-->
  <!--    </form>-->
  <!--  </div>-->
</template>

<script>
import { mapActions, mapState } from "vuex";
const placeStore = "placeStore";

export default {
  name: "TheSearchbar",
  components: {},
  data() {
    return {
      condition: {
        contentId: null,
        sidoCode: null,
        title: null,
      },
    };
  },
  async created() {
    console.log("created");
    await this.getRegionInfo();
    console.log(this.contents);
    await this.getContentInfo();
    console.log(this.regions);
  },
  computed: {
    ...mapState(placeStore, ["regions", "contents"]),
  },
  methods: {
    ...mapActions(placeStore, ["getRegionInfo", "getContentInfo"]),
    async search() {
      // TODO : search 후 /themetrip 이동 구현
    },
  },
};
</script>

<style scoped></style>
