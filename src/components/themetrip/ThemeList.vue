<template>
  <b-container class="bv-example-row mt-3">
    <TheSearchbar route="themetrip" />
    <TheMap />
    <b-row>
      <b-col>
        <b-table
          hover
          :items="places"
          :fields="fields"
          @row-clicked="viewPlace"
          class="text-center align-middle"
        >
          <template v-slot:cell(firstImage)="data">
            <b-img
              class="img-fluid img-thumbnail"
              :src="`${
                data.item.firstImage
                  ? data.item.firstImage
                  : require('@/assets/img/on_error.png')
              }`"
              style="min-height: 100px; max-height: 100px"
            />
          </template>
        </b-table>
      </b-col>
    </b-row>
    <ThePagenation />
  </b-container>
</template>

<script>
import { mapState } from "vuex";
import TheMap from "../TheMap.vue";
import ThePagenation from "../ThePagenation.vue";
import TheSearchbar from "../TheSearchbar.vue";

export default {
  components: { ThePagenation, TheSearchbar, TheMap },
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
        { key: "firstImage", label: "이미지", tdClass: "tdClass col-1" },
        { key: "title", label: "이름", tdClass: "tdSubject col-1" },
        { key: "contentTypeName", label: "유형", tdClass: "tdClass col-1" },
        { key: "sidoName", label: "지역", tdClass: "tdClass col-1" },
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

<style>
.table th,
.table td {
  vertical-align: middle;
}
</style>
