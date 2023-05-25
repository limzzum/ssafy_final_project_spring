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
                    v-model="userInfo.userId"
                    readonly
                  ></b-form-input>
                </b-col>
              </b-row>
              <b-row class="align-items-center p-2">
                <b-col cols="2">이름</b-col>
                <b-col cols="10">
                  <b-form-input
                    id="userid"
                    v-model="userInfo.userName"
                    readonly
                  ></b-form-input>
                </b-col>
              </b-row>
              <b-row class="align-items-center p-2">
                <b-col cols="2">이메일</b-col>
                <b-col cols="10">
                  <b-form-input
                    id="userid"
                    v-model="userInfo.email"
                    readonly
                  ></b-form-input>
                </b-col>
              </b-row>
              <b-row class="align-items-center p-2">
                <b-col cols="2">비밀번호</b-col>
                <b-col cols="10">
                  <b-form-input id="userid" readonly></b-form-input>
                </b-col>
              </b-row>
              <b-row class="align-items-center p-2">
                <b-col cols="2">비밀번호 확인</b-col>
                <b-col cols="10">
                  <b-form-input id="userid" readonly></b-form-input>
                </b-col>
              </b-row>
            </b-form>
            <b-row>
              <b-col cols="6"></b-col>
              <b-col>
                <b-button variant="primary">작성한 글 보기</b-button>
              </b-col>
              <b-col>
                <b-button variant="warning">회원 정보 수정</b-button>
              </b-col>
              <b-col>
                <b-button variant="danger">회원 탈퇴</b-button>
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

export default {
  name: "UserMyPage",
  components: {},
  data() {
    return {};
  },
  computed: {
    ...mapState(userStore, ["userInfo"]),
  },
  methods: {
    ...mapActions(userStore, ["userUpdate", "userDelete"]),
  },
};
</script>

<style></style>
