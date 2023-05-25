<template>
  <b-container class="m-3 p-2">
    <b-row>
      <b-col>
        <b-card border-variant="black" class="mb-2" no-body>
          <b-card-header style="white-space: nowrap">
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
          <b-tabs
            v-if="selected != null && selected.length"
            card
            style="text-align: center"
          >
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
                    <b-col cols="4">
                      {{ item.title }}
                    </b-col>
                    <b-col cols="2">
                      <b-button variant="primary" @click="detailPlace(item)">
                        상세
                      </b-button>
                    </b-col>
                  </b-row>
                </b-card>
              </b-card-group>
            </b-tab>
            <b-tab title="지도" active> <TheMap /></b-tab>
          </b-tabs>
          <b-card-footer>
            <b-row>
              <b-col v-if="!article.comments.length">
                등록된 댓글이 없습니다.
              </b-col>
              <b-table v-else> TODO </b-table>
            </b-row>
            <hr />
            <b-row class="align-items-center">
              <b-col cols="11">
                <b-card>
                  <b-form>
                    <b-form-textarea
                      rows="3"
                      placeholder="댓글을 입력하세요"
                      no-resize
                      v-model="comment.content"
                      @focus="isEmptyContent = false"
                    ></b-form-textarea>
                  </b-form>
                </b-card>
              </b-col>
              <b-col cols="1" style="white-space: nowrap" class="text-left">
                <b-button variant="primary" @click="regComment">등록</b-button>
              </b-col>
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
import TheMap from "@/components/TheMap.vue";
const boardStore = "boardStore";
const userStore = "userStore";
const placeStore = "placeStore";
export default {
  name: "BoardView",
  components: { TheMap },
  data() {
    return {
      isEmptyContent: false,
      comment: {
        postId: null,
        parentId: -1,
        userNo: null,
        content: null,
      },
    };
  },
  created() {
    this.setSelected(this.article.places);
    this.comment.postId = this.article.postId;
    this.comment.userNo = this.userInfo.userNo;
  },
  computed: {
    ...mapState(userStore, ["userInfo"]),
    ...mapState(boardStore, ["article"]),
    ...mapState(placeStore, ["selected"]),
    regTime() {
      return moment(this.article.regTime).format("YY.MM.DD hh:mm");
    },
  },
  watch: {
    article(item) {
      this.setSelected(item.places);
    },
  },
  methods: {
    ...mapActions(placeStore, ["setSelected"]),
    img(link) {
      return link ? link : require("@/assets/img/on_error.png");
    },
    detailPlace(place) {
      //TODO: 기능 구현
      console.log(place);
    },
    regComment() {},
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
.no-padding .card-body {
  padding: 5px;
}
</style>
