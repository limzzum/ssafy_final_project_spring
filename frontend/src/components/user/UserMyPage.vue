<template>
  <b-container>
    <b-card no-body>
      <b-tabs card>
        <b-tab title="회원 정보" active>
          <b-card-text>
            <b-form class="p-2">
              <b-row class="align-items-center p-2">
                <b-col cols="2">아이디</b-col>
                <b-col cols="10">
                  <b-form-input
                    id="userid"
                    v-model="usertmp.userId"
                    readonly
                  ></b-form-input>
                </b-col>
              </b-row>
              <b-row class="align-items-center p-2">
                <b-col cols="2">이름</b-col>
                <b-col cols="10">
                  <b-form-input
                    id="userid"
                    v-model="usertmp.userName"
                    :readonly="blockModify"
                  ></b-form-input>
                </b-col>
              </b-row>
              <b-row class="align-items-center p-2">
                <b-col cols="2">이메일</b-col>
                <b-col cols="10">
                  <b-form-input
                    id="userid"
                    v-model="usertmp.email"
                    :readonly="blockModify"
                  ></b-form-input>
                </b-col>
              </b-row>
              <b-row class="align-items-center p-2" v-show="!blockModify">
                <b-col cols="2">기존 비밀번호</b-col>
                <b-col cols="10">
                  <b-form-input
                    id="userid"
                    v-model="pwdCheck"
                    type="password"
                    @focus="userMatch = true"
                  ></b-form-input>
                </b-col>
              </b-row>
              <b-row v-if="!userMatch">
                <b-col cols="12">
                  <b-alert show variant="danger">
                    저장된 비밀번호와 일치하지 않습니다
                  </b-alert>
                </b-col>
              </b-row>
              <b-row class="align-items-center p-2" v-show="!blockModify">
                <b-col cols="2">새 비밀번호</b-col>
                <b-col cols="10">
                  <b-form-input
                    id="userid"
                    v-model="usertmp.userPwd"
                    type="password"
                    @focus="
                      pwdMatch = true;
                      validNew = true;
                    "
                  ></b-form-input>
                </b-col>
              </b-row>
              <b-row class="align-items-center p-2" v-show="!blockModify">
                <b-col cols="2">새 비밀번호 확인</b-col>
                <b-col cols="10">
                  <b-form-input
                    id="userid"
                    v-model="pwdConfirm"
                    type="password"
                    @focus="
                      pwdMatch = true;
                      validNew = true;
                    "
                  ></b-form-input>
                </b-col>
              </b-row>
              <b-row v-if="!pwdMatch">
                <b-col cols="12">
                  <b-alert show variant="danger">
                    두 비밀번호가 일치하지 않습니다
                  </b-alert>
                </b-col>
              </b-row>
              <b-row v-if="!validNew">
                <b-col cols="12">
                  <b-alert show variant="danger">
                    비밀번호는 네 글자 이상이어야 합니다
                  </b-alert>
                </b-col>
              </b-row>
            </b-form>
            <b-row v-if="blockModify">
              <b-col cols="6"></b-col>
              <b-col>
                <b-button variant="primary" @click="myArticles"
                  >작성한 글 보기</b-button
                >
              </b-col>
              <b-col>
                <b-button variant="warning" @click="modify"
                  >회원 정보 수정</b-button
                >
              </b-col>
              <b-col>
                <b-button variant="danger" @click="leave">회원 탈퇴</b-button>
              </b-col>
            </b-row>
            <b-row v-if="!blockModify">
              <b-col cols="8"></b-col>
              <b-col cols="2">
                <b-button variant="danger" @click="blockModify = true"
                  >취소</b-button
                >
              </b-col>
              <b-col cols="2">
                <b-button variant="primary" @click="confirmModify"
                  >회원 정보 수정</b-button
                >
              </b-col>
            </b-row>
          </b-card-text>
        </b-tab>
      </b-tabs>
    </b-card>
  </b-container>
</template>

<script>
import { mapState, mapActions } from "vuex";
const userStore = "userStore";
const boardStore = "boardStore";

export default {
  name: "UserMyPage",
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
      usertmp: {
        userId: null,
        email: null,
        userName: null,
        userPwd: null,
      },
      pwdConfirm: null,
      blockModify: true,
      userMatch: true,
      validNew: true,
      pwdMatch: true,
      pwdCheck: null,
    };
  },
  created() {
    this.usertmp.userNo = this.userInfo.userNo;
    this.usertmp.userId = this.userInfo.userId;
    this.usertmp.email = this.userInfo.email;
    this.usertmp.userName = this.userInfo.userName;
  },
  computed: {
    ...mapState(userStore, ["userInfo"]),
  },
  methods: {
    ...mapActions(userStore, [
      "userUpdate",
      "userUpdate",
      "userDelete",
      "userLogout",
    ]),
    ...mapActions(boardStore, ["setCondition", "searchArticle"]),
    myArticles() {
      console.log(this.userInfo);
      this.condition.userNo = this.userInfo.userNo;
      this.setCondition(this.condition);
      this.searchArticle();
      this.$router.push({ name: "boardlist", params: { noUpdate: true } });
    },
    modify() {
      this.blockModify = false;
    },
    confirmModify() {
      if (this.pwdCheck != this.userInfo.userPwd) {
        this.userMatch = false;
        return;
      }
      if (this.pwdConfirm != this.usertmp.userPwd) {
        this.pwdMatch = false;
        return;
      }
      if (this.usertmp.userPwd == null || this.usertmp.userPwd.length < 4) {
        this.validNew = false;
        return;
      }
      if (this.userUpdate(this.usertmp))
        alert("회원님의 정보가 성공적으로 수정되었습니다.");
      this.blockModify = true;
    },
    leave() {
      if (!confirm("정말로 등록된 모든 정보를 삭제하고 탈퇴하시겠습니까?"))
        return;
      this.userDelete();
      alert("회원 탈퇴되었습니다.");
      this.userLogout(this.userInfo.userNo);
      sessionStorage.removeItem("access-token"); //저장된 토큰 없애기
      sessionStorage.removeItem("refresh-token"); //저장된 토큰 없애기
      if (this.$route.path != "/") this.$router.push({ name: "main" });
    },
  },
};
</script>

<style></style>
