<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert show><h3>글목록</h3></b-alert>
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col class="text-right">
        <b-button variant="outline-primary" @click="moveWrite()">글쓰기</b-button>
      </b-col>
    </b-row>
    <b-row>
      <b-col>
        <b-table striped hover :items="articles" :fields="fields" @row-clicked="viewArticle">
          <template #cell(title)="data">
            <!-- <board-list-item :postId=data.postId :title="data.title" :user-name="data.userName" :hits="data.hits" :create-time="data.createTime" ></board-list-item> -->
            <router-link :to="{ name: 'boardview', params: { postId: data.item.postId } }">
              {{data.item.title}}
            </router-link>
          </template>
        </b-table>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import { listArticle } from "@/api/board";

export default {
  name: "BoardList",
  data() {
    return {
      articles: [],
      fields: [
        { key: "postId", label: "글번호", tdClass: "tdClass" },
        { key: "title", label: "제목", tdClass: "tdSubject" },
        { key: "userName", label: "작성자", tdClass: "tdClass" },
        { key: "createTime", label: "작성일", tdClass: "tdClass" },
        { key: "hits", label: "조회수", tdClass: "tdClass" },
      ],
    };
  },
  created() {
    let param = {
      pg: 1,
      spp: 20,
      key: null,
      word: null,
    };
    let option = {
      title: null,
      content: null,
      boardType: null,
      user_no: null
    };
    listArticle(
      param.pg,
      option,
      ({ data }) => {
        this.articles = data.result;
        console.log(data.result);
      },
      (error) => {
        console.log(error);
      }
    );
  },
  methods: {
    moveWrite() {
      this.$router.push({ name: "boardwrite" });
    },
    viewArticle(article) {
      this.$router.push({
        name: "boardview",
        params: { articleno: article.articleno },
      });
    },
  },
};
</script>

<style scope>
.tdClass {
  width: 50px;
  text-align: center;
}
.tdSubject {
  width: 300px;
  text-align: left;
}
</style>
