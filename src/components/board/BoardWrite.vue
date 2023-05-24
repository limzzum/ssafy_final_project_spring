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
        <template #footer>
          <b-row v-if="type == 'write'" style="white-space: nowrap">
            <b-col cols="9"></b-col>
            <b-col class="text-right">
              <b-button variant="danger" @click="back">취소</b-button>
            </b-col>
            <b-col>
              <b-button variant="primary" @click="write">작성</b-button></b-col
            >
          </b-row>
        </template>
      </b-card>
    </b-form>
  </b-container>
</template>

<script>
import { mapState, mapActions } from "vuex";
const boardStore = "boardStore";
const userStore = "userStore";
export default {
  name: "BoardWrite",
  components: {},
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
      },
      isEmptyTitle: false,
      isEmptyContent: false,
    };
  },
  computed: {
    ...mapState(userStore, ["userInfo"]),
  },
  created() {
    this.type = this.$route.params.type;
    this.backroute = this.$route.params.backroute;
    this.article.userNo = this.userInfo.userNo;
    if (this.userInfo.isAdmin === "T")
      this.boardTypeOptions.push({ value: "notice", text: "공지사항" });
  },
  methods: {
    ...mapActions(boardStore, ["writeArticle", "setBoardType"]),
    write() {
      if (this.article.title == null || this.article.title.length < 2) {
        this.isEmptyTitle = true;
        return;
      }
      if (this.article.content == null || this.article.content.length < 5) {
        this.isEmptyContent = true;
        return;
      }
      this.writeArticle(this.article);
      this.setBoardType(this.article.boardType);
      this.$router.push({ name: "boardlist" });
    },
    back() {
      this.$router.push({ name: this.backroute });
    },
  },
};
</script>

<style></style>
