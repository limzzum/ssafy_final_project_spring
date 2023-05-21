<template>
  <b-container class="bv-example-row mt-3">
    <!-- <theme-list-item
      v-for="(place, index) in places"
      :key="index"
      :contentId="place.contentId"
      :title="place.title"
      :firstImage="place.firstImage"
      :sidoName="place.sidoName"
      :contentTypeName="place.contentTypeName"
    >
    </theme-list-item> -->

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
          <!-- <template #cell(title)="data">
            <router-link
              :to="{ name: 'placeview', params: { id: data.item.contentId } }"
            >
              {{ data.item.title }}
            </router-link>
          </template> -->
        </b-table>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import { searchPlace } from "@/api/place";
// import ThemeListItem from "./ThemeListItem.vue";

export default {
  components: {},
  name: "ThemeList",
  data() {
    return {
      places: [],
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
  created() {
    let param = {
      page: 1,
    };
    searchPlace(
      param,
      this.condition,
      ({ data }) => {
        console.log("data111 " + data.result);
        this.places = data.result;
      },
      (error) => {
        console.log(error);
      }
    );
  },
  // computed: {
  //   ...mapState(placeStore, ["isLogin", "isLoginError", "userInfo"]),
  // },
  // methods: {
  //   ...mapActions(placeStore, ["userConfirm", "getUserInfo"]),
  //   async confirm() {
  //     await this.userConfirm(this.user);
  //     console.log("userConfirm 끝");
  //     let token = sessionStorage.getItem("access-token");
  //     console.log("1. confirm() token >> " + token);
  //     if (this.isLogin) {
  //       await this.getUserInfo(token);
  //       console.log("4. confirm() userInfo :: ", this.userInfo);
  //       this.$router.push({ name: "main" });
  //     }
  //   },
  //   movePage() {
  //     this.$router.push({ name: "join" });
  //   },
  // },
};
</script>

<style></style>
