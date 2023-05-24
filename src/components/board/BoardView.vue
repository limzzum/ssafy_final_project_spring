<template>
  <b-container class="m-3 p-2">
    <b-row>
      <b-col>
        <b-card border-variant="black" class="mb-2" no-body>
          <b-card-header>
            <b-row>
              <b-col class="board-title" cols="2">{{
                article.boardTitle
              }}</b-col>
              <b-col class="board-title"> | </b-col>
              <b-col class="article-title" cols="8">{{ article.title }}</b-col>
            </b-row>
            <b-row>
              <b-col class="article-info" cols="3">
                {{ article.userName }}
                | {{ regTime }}
              </b-col>
              <b-col class="article-icon" cols="8">
                <BIconEye variant="secondary"></BIconEye> {{ article.hits }}
                <BIconChatDots variant="secondary"></BIconChatDots> 0
              </b-col>
            </b-row>
          </b-card-header>
          <b-card-body class="article-body">
            <b-row>
              <b-col>
                {{ article.content }}
              </b-col>
            </b-row>
          </b-card-body>
          <b-card-footer>
            <b-row>
              <b-col> 등록된 댓글이 없습니다. </b-col>
            </b-row>
          </b-card-footer>
        </b-card>
      </b-col>
    </b-row>
    <b-row class="text-center">
      <b-col>
        <router-link to="/board/list">
          <b-button variant="primary"> 목록 </b-button>
        </router-link>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import { mapState, mapActions } from "vuex";
import moment from "moment";
const boardStore = "boardStore";
export default {
  name: "BoardView",
  components: {},
  data() {
    return {};
  },
  computed: {
    ...mapState(boardStore, ["article"]),
    regTime() {
      return moment(this.article.regTime).format("YY.MM.DD hh:mm");
    },
  },
  methods: {
    ...mapActions(boardStore, []),
  },
};
</script>

<style scoped>
.board-title {
  font-size: x-large;
  text-align: center;
  color: cornflowerblue;
}
.article-title {
  font-size: x-large;
  text-align: left;
}
.article-info {
  text-align: center;
  color: grey;
}
.article-icon {
  text-align: right;
  color: grey;
}
.article-body {
  min-height: 300px;
}
</style>
