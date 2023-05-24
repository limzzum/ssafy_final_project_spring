<template>
  <b-form @submit.prevent="search">
    <b-form-row class="align-items-center">
      <b-col cols="3">
        <b-form-select
          v-model="condition.boardType"
          :options="boardTypeOptions"
        >
        </b-form-select>
      </b-col>
      <b-col cols="3">
        <b-form-select
          v-model="searchType"
          :options="searchOptions"
        ></b-form-select> </b-col
      ><b-col cols="4">
        <b-form-input
          v-model="searchKeyword"
          type="text"
          placeholder="키워드"
        ></b-form-input>
      </b-col>
      <b-col cols="2" class="text-center">
        <b-button
          class="m-1"
          type="submit"
          variant="primary"
          style="white-space: nowrap"
          >검색</b-button
        >
      </b-col>
    </b-form-row>
  </b-form>
</template>

<script>
import { mapState, mapActions } from "vuex";
const boardStore = "boardStore";
export default {
  name: "BoardSearchbar",
  components: {},
  data() {
    return {
      condition: {
        title: null,
        content: null,
        userName: null,
        userNo: null,
        boardType: null,
      },
      boardTypeOptions: [
        { value: null, text: "전체 게시판" },
        { value: "notice", text: "공지사항" },
        { value: "review", text: "여행후기" },
        { value: "free", text: "자유게시판" },
        { value: "team", text: "일행구하기" },
        { value: "plan", text: "여행코스" },
      ],
      searchOptions: [
        { value: "title", text: "글 제목" },
        { value: "content", text: "글 내용" },
        { value: "userName", text: "작성자" },
      ],
      searchType: "title",
      searchKeyword: "",
    };
  },
  computed: {
    ...mapState(boardStore, ["boardTypeData", "boardType"]),
  },
  methods: {
    ...mapActions(boardStore, [
      "setCondition",
      "setBoardType",
      "searchArticle",
    ]),
    async search() {
      this.condition["title"] = null;
      this.condition["content"] = null;
      this.condition["userName"] = null;
      this.condition[this.searchType] = this.searchKeyword;
      this.setCondition(this.condition);
      console.log(this.condition);
      this.setBoardType(null);
      await this.searchArticle();
    },
  },
};
</script>

<style scoped></style>
