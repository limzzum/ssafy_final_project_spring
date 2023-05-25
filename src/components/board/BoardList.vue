<template>
  <b-container
    ><b-row class="text-center p-2 m-2">
      <b-col class="text-center" cols="2">
        <b-button-group vertical size="md">
          <b-button
            v-for="(value, name) in boardTypeData"
            :key="value"
            @click="search(name)"
            variant="outline-primary"
            :pressed="isActive(name)"
            style="white-space: nowrap"
            >{{ value }}</b-button
          >
        </b-button-group>
      </b-col>
      <b-col cols="10">
        <b-row class="m-2 align-items-center">
          <b-col cols="11">
            <BoardSearchbar />
          </b-col>
          <b-col cols="1">
            <b-button
              variant="outline-primary"
              style="white-space: nowrap"
              @click="moveWrite"
              >글쓰기</b-button
            >
          </b-col>
        </b-row>
        <b-table
          v-if="articles.length"
          hover
          :items="articles"
          :fields="fields"
          style="white-space: nowrap"
          @row-clicked="viewArticle"
        >
          <template #cell(createTime)="data">
            {{ getTime(data) }}
          </template>
        </b-table>
        <b-alert show v-if="!articles.length">검색된 글이 없습니다.</b-alert>
        <BoardPagination v-if="articles.length" />
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import { mapState, mapActions } from "vuex";
const boardStore = "boardStore";
const placeStore = "placeStore";
import BoardPagination from "./BoardPagination.vue";
import BoardSearchbar from "@/components/board/BoardSearchbar.vue";
import moment from "moment-timezone";
export default {
  components: { BoardPagination, BoardSearchbar },
  name: "BoardList",
  data() {
    return {
      fields: [
        { key: "postId", label: "글번호", tdClass: "col-1" },
        { key: "title", label: "제목", tdClass: "col-5" },
        { key: "userName", label: "작성자", tdClass: "col-1" },
        { key: "createTime", label: "작성일", tdClass: "col-1" },
        { key: "hits", label: "조회수", tdClass: "col-1" },
      ],
      condition: {
        title: null,
        content: null,
        userName: null,
        userNo: null,
        boardType: null,
      },
    };
  },
  created() {
    if (this.boardType == null) {
      this.setBoardType("notice");
    }
    this.condition.boardType = this.boardType;
    this.setCondition(this.condition);
    this.searchArticle();
  },
  computed: {
    ...mapState(boardStore, [
      "boardTypeData",
      "boardType",
      "articles",
      "currentPage",
      "article",
    ]),
  },
  methods: {
    ...mapActions(boardStore, [
      "setCondition",
      "setBoardType",
      "searchArticle",
      "setArticle",
    ]),
    ...mapActions(placeStore, ["setSelected", "clearPlace"]),
    viewArticle(item) {
      this.setArticle(item);
      this.clearPlace();
      this.setSelected(item.places);
      this.$router.push({
        name: "boardview",
      });
    },
    getTime(data) {
      let tdiff = parseInt(
        moment.duration(moment().diff(data.item.createTime)).asMinutes() +
          9 * 60
      );
      if (tdiff >= 24 * 60) {
        tdiff = parseInt(tdiff / (24 * 60));
        if (tdiff >= 365) return parseInt(tdiff / 365) + "년 전";
        if (tdiff >= 30) return parseInt(tdiff / 30) + "달 전";
        if (tdiff >= 7) return parseInt(tdiff / 7) + "주 전";
        if (tdiff >= 1) return parseInt(tdiff) + "일 전";
      }
      if (tdiff >= 60) return parseInt(tdiff / 60) + "시간 전";
      if (tdiff <= 0) return "방금 전";
      return parseInt(tdiff) + "분 전";
    },
    search(type) {
      // console.log(type);
      this.setBoardType(type);
      this.condition.boardType = this.boardType;
      this.setCondition(this.condition);
      // console.log(this.boardType);
      this.searchArticle();
      this.$router.push({ name: "boardlist" }).catch(() => {});
    },
    isActive(type) {
      return type == this.boardType;
    },
    moveWrite() {
      this.$router.push({
        name: "boardwrite",
        params: { type: "write", backroute: "boardlist" },
      });
    },
  },
};
</script>

<style>
a {
  text-decoration: none;
  color: black;
}
a:hover {
  text-decoration: none;
  color: darkblue;
}
.table th,
.table td {
  vertical-align: middle;
}
</style>
