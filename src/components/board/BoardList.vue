<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert show
          ><h3>{{ boardTitle }}</h3></b-alert
        >
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col class="text-right">
        <b-button variant="outline-primary" @click="moveWrite()"
          >글쓰기</b-button
        >
      </b-col>
    </b-row>
    <b-row>
      <b-col>
        <b-table
          hover
          :items="articles"
          :fields="fields"
          @row-clicked="viewArticle"
        >
          <template #cell(title)="data">
            <!-- <board-list-item :postId=data.postId :title="data.title" :user-name="data.userName" :hits="data.hits" :create-time="data.createTime" ></board-list-item> -->
            <router-link
              :to="{ name: 'boardview', params: { postId: data.item.postId } }"
            >
              {{ data.item.title }}
            </router-link>
          </template>
        </b-table>
      </b-col>
    </b-row>
    <board-pagenation />
  </b-container>
</template>

<script>
// import { listArticle } from "@/api/board";
import { mapState, mapActions } from "vuex";
import BoardPagenation from "./BoardPagenation.vue";
export default {
  components: { BoardPagenation },
  name: "BoardList",
  data() {
    return {
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
    this.searchArticle();
    // let param = {
    //   pg: 1,
    //   spp: 20,
    //   key: null,
    //   word: null,
    // };
    // let option = {
    //   title: null,
    //   content: null,
    //   boardType: 'notice',
    //   user_no: null
    // };
    // listArticle(
    //   param.pg,
    //   option,
    //   ({ data }) => {
    //     this.articles = data.result;
    //     console.log(data.result);
    //   },
    //   (error) => {
    //     console.log(error);
    //   }
    // );
  },
  computed: {
    ...mapState("boardStore", ["boardType", "boardTitle", "articles", "currentPage"]),
    ...mapActions("boardStore", ["searchArticle"]),
  },
  methods: {
    moveWrite() {
      this.$router.push({ name: "boardwrite" });
    },
    viewArticle(item) {
      this.$router.push({
        name: "boardview",
        params: { postId: item.postId },
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
.tdClass {
  width: 50px;
  text-align: center;
}
.tdSubject {
  width: 300px;
  text-align: left;
}
</style>
