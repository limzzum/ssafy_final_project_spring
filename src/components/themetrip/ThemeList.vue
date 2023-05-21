<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-table
          striped
          hover
          :items="places"
          :fields="fields"
          @row-clicked="viewPlace"
        >
          <template v-slot:cell(firstImage)="data">
            <img
              class="img-fluid img-thumbnail"
              :src="data.item.firstImage"
              width="150"
              height="150"
              alt="이미지 없음"
            />
          </template>
        </b-table>
      </b-col>
    </b-row>
    <the-pagenation />
  </b-container>
</template>

<script>
import { mapState } from "vuex";
import ThePagenation from "../ThePagenation.vue";

export default {
  components: { ThePagenation },
  name: "ThemeList",
  data() {
    return {
      // isLoginError: false,
      condition: {
        contentTypeId: 0,
        sidoCode: "",
        title: "",
      },
      fields: [
        { key: "firstImage", label: "이미지", tdClass: "tdClass" },
        { key: "contentId", label: "번호", tdClass: "tdClass" },
        { key: "title", label: "이름", tdClass: "tdSubject" },
        { key: "contentTypeName", label: "유형", tdClass: "tdClass" },
        { key: "sidoName", label: "지역", tdClass: "tdClass" },
      ],
    };
  },
  computed: {
    ...mapState("placeStore", ["places"]),
  },

  // computed: {
  //   ...mapState(placeStore, ["isLogin", "isLoginError", "userInfo"]),
  // },
  methods: {
    // ...mapActions(placeStore, ["userConfirm", "getUserInfo"]),
    viewPlace(place) {
      this.$router.push({
        name: "placeview",
        params: { contentId: place.contentId },
      });
    },
  },
};
</script>

<style></style>
