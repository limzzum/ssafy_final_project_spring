<template>
  <b-container class="align-middle p-3">
    <b-form>
      <b-card border-variant="primary">
        <template #header>
          <b-row class="align-items-center">
            <b-col cols="3">
              <b-form-select
                v-model="article.boardType"
                :options="boardTypeOptions"
              ></b-form-select>
            </b-col>
            <b-col class="text-right"> 제목 </b-col>
            <b-col cols="8">
              <b-form-input
                v-model="article.title"
                placeholder="제목을 입력하세요"
                @focus="isEmptyTitle = false"
              ></b-form-input>
            </b-col>
          </b-row>
          <b-row v-if="isEmptyTitle">
            <b-col cols="4"></b-col>
            <b-col cols="8">
              <b-alert show variant="danger"
                >두 글자 이상의 제목을 입력해주세요</b-alert
              >
            </b-col>
          </b-row>
        </template>
        <b-alert show variant="danger" v-if="isEmptyContent">
          다섯 글자 이상의 내용을 입력해주세요
        </b-alert>
        <b-form-textarea
          rows="20"
          placeholder="내용을 입력하세요"
          no-resize
          v-model="article.content"
          @focus="isEmptyContent = false"
        ></b-form-textarea>
        <b-tabs v-if="selected.length" style="text-align: center" card>
          <b-tab title="태그된 장소">
            <b-card-group v-for="item in selected" :key="item.contentId">
              <b-card class="no-padding">
                <b-row class="align-items-center">
                  <b-col cols="5">
                    <b-card-img
                      :src="img(item.firstImage)"
                      fluid
                      style="
                        max-height: 75px;
                        min-height: 75px;
                        object-fit: cover;
                      "
                    ></b-card-img>
                  </b-col>
                  <b-col></b-col>
                  <b-col cols="5">
                    {{ item.title }}
                  </b-col>
                  <b-col cols="1">
                    <b-button variant="danger" @click="unselectPlace(item)">
                      X
                    </b-button>
                  </b-col>
                </b-row>
              </b-card>
            </b-card-group>
          </b-tab>
          <b-tab title="지도" active> <TheMap /></b-tab>
        </b-tabs>
        <template #footer>
          <b-row v-if="!type || type == 'write'" style="white-space: nowrap">
            <b-col cols="9"></b-col>
            <b-col class="text-right">
              <b-button variant="danger" @click="back">취소</b-button>
            </b-col>
            <b-col>
              <b-button variant="primary" @click="write">작성</b-button></b-col
            >
          </b-row>

          <b-row v-if="type == 'modify'" style="white-space: nowrap">
            <b-col cols="9"></b-col>
            <b-col class="text-right">
              <b-button variant="danger" @click="back">취소</b-button>
            </b-col>
            <b-col>
              <b-button variant="primary" @click="modify">작성</b-button></b-col
            >
          </b-row>
        </template>
      </b-card>
    </b-form>
  </b-container>
</template>

<script>
import { mapState, mapActions } from "vuex";
import TheMap from "@/components/TheMap.vue";
const boardStore = "boardStore";
const userStore = "userStore";
const placeStore = "placeStore";
export default {
  name: "BoardWrite",
  components: { TheMap },
  data() {
    return {
      type: null,
      backroute: null,
      boardTypeOptions: [
        { value: "review", text: "여행후기" },
        { value: "free", text: "자유게시판" },
        { value: "team", text: "일행구하기" },
        { value: "plan", text: "여행코스" },
      ],
      article: {
        title: null,
        content: null,
        userNo: null,
        boardType: "free",
        places: null,
      },
      isEmptyTitle: false,
      isEmptyContent: false,
    };
  },
  computed: {
    ...mapState(userStore, ["userInfo"]),
    ...mapState(placeStore, ["selected"]),
  },
  created() {
    this.type = this.$route.params.type;
    this.backroute = this.$route.params.backroute;
    this.article.userNo = this.userInfo.userNo;
    if (this.userInfo.isAdmin === "T")
      this.boardTypeOptions.push({ value: "notice", text: "공지사항" });
    if (this.$route.params.boardType == "plan") {
      this.boardTypeOptions = [
        { value: "review", text: "여행후기" },
        { value: "plan", text: "여행코스" },
      ];
      this.article.boardType = "plan";
    }
  },
  methods: {
    ...mapActions(boardStore, [
      "setCondition",
      "writeArticle",
      "searchArticle",
      "setBoardType",
    ]),
    ...mapActions(placeStore, ["unselectPlace"]),
    write() {
      if (this.article.title == null || this.article.title.length < 2) {
        this.isEmptyTitle = true;
        return;
      }
      if (this.article.content == null || this.article.content.length < 5) {
        this.isEmptyContent = true;
        return;
      }
      if (this.selected.length) {
        this.article.places = [];
        this.selected.forEach((place) => {
          // console.log(place);
          this.article.places.push(place.contentId);
        });
        console.log(this.article.places);
      }
      this.writeArticle(this.article);
      this.setCondition({
        title: null,
        content: null,
        userName: null,
        userNo: null,
        boardType: this.article.boardType,
      });
      this.setBoardType(this.article.boardType);
      this.searchArticle();
      this.$router.push({ name: "boardlist" });
    },
    back() {
      if (this.backroute) this.$router.push({ name: this.backroute });
      else this.$router.push({ name: "boardlist" });
    },
    img(link) {
      return link ? link : require("@/assets/img/on_error.png");
    },
  },
};
</script>

<style>
.no-padding .card-body {
  padding: 5px;
}
</style>
