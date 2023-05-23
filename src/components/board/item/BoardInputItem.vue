<template>
  <b-row class="mb-1">
    <b-col style="text-align: left">
      <b-form @submit="onSubmit" @reset="onReset">
        <b-form-group id="userid-group" label="작성자:" label-for="userName" description="작성자를 입력하세요.">
          <b-form-input
            id="userName"
            :disabled="isUserid"
            v-model="article.userName"
            type="text"
            required
            placeholder="작성자 입력..."
          ></b-form-input>
        </b-form-group>

        <b-form-group id="subject-group" label="제목:" label-for="title" description="제목을 입력하세요.">
          <b-form-input
            id="title"
            v-model="article.title"
            type="text"
            required
            placeholder="제목 입력..."
          ></b-form-input>
        </b-form-group>

        <b-form-group id="content-group" label="내용:" label-for="content">
          <b-form-textarea
            id="content"
            v-model="article.content"
            placeholder="내용 입력..."
            rows="10"
            max-rows="15"
          ></b-form-textarea>
        </b-form-group>

        <b-button type="submit" variant="primary" class="m-1" v-if="this.type === 'register'">글작성</b-button>
        <b-button type="submit" variant="primary" class="m-1" v-else>글수정</b-button>
        <b-button type="reset" variant="danger" class="m-1">초기화</b-button>
      </b-form>
    </b-col>
  </b-row>
 
</template>

<script>
import {  modifyArticle, getArticle } from "@/api/board";
import { mapActions, mapState } from "vuex";
export default {
  name: "BoardInputItem",
  data() {
    return {
      article: {
        postId: 0,
        userName: "",
        title: "",
        content: "",
      },
      isUserid: false,
    };
  },
  props: {
    type: { type: String },
  },
  created() {
    
    if (this.type === "modify") {
      let param = this.$route.params.postId;
      getArticle(
        param,
        ({ data }) => {
          // this.article.articleno = data.article.articleno;
          // this.article.userid = data.article.userid;
          // this.article.subject = data.article.subject;
          // this.article.content = data.article.content;
          this.article = data.result;
        },
        (error) => {
          console.log(error);
        }
      );
      this.isUserid = true;
    }
  },
  computed: {
    ...mapState("boardStore", ["boardType"]),
  },
  methods: {
    ...mapState("userStore", ["userNo"]),
    ...mapActions("boardStore", ["writeArticle","searchArticle"]),
    onSubmit(event) {
      event.preventDefault();

      let err = true;
      let msg = "";
      !this.article.userName && ((msg = "작성자 입력해주세요"), (err = false), this.$refs.userName.focus());
      err && !this.article.title && ((msg = "제목 입력해주세요"), (err = false), this.$refs.title.focus());
      err && !this.article.content && ((msg = "내용 입력해주세요"), (err = false), this.$refs.content.focus());

      if (!err) alert(msg);
      else this.type === "register" ? this.registArticle() : this.modifyArticle();
    },
    onReset(event) {
      event.preventDefault();
      this.article.postId = 0;
      this.article.title = "";
      this.article.content = "";
      this.moveList();
    },
    registArticle() {
      
      let param = {
        userNo: sessionStorage.getItem("userNo"),
        title: this.article.title,
        content: this.article.content,
        boardType:this.boardType
      };
      console.log(param)
      this.writeArticle(param);
      this.moveList();
      
    },
    modifyArticle() {
      let param = {
        postId: this.article.postId,
        title: this.article.title,
        content: this.article.content,
      };
      modifyArticle(
        param,
        ({ data }) => {
          let msg = "수정 처리시 문제가 발생했습니다.";
          if (data.msg === "success") {
            msg = "수정이 완료되었습니다.";
          }
          alert(msg);
          // 현재 route를 /list로 변경.
          this.moveList();
        },
        (error) => {
          console.log(error);
        }
      );
    },
    moveList() {
        this.$router.push({ name: "boardlist" });
  
    },
  },
};
</script>

<style></style>
