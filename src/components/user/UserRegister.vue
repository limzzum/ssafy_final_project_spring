<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert variant="secondary" show><h3>회원가입</h3></b-alert>
      </b-col>
    </b-row>
    <b-row>
      <b-col></b-col>
      <b-col cols="8">
        <b-card class="text-center mt-3" style="max-width: 40rem" align="left">
          <b-form class="text-left">
            <b-alert
              show
              variant="danger"
              v-if="isRegistError"
              v-text="isRegistError"
            ></b-alert>
            <b-form-group label="아이디:" label-for="userid">
              <b-form-input
                id="userid"
                v-model="user.userId"
                required
                placeholder="아이디 입력...."
                @keyup.enter="confirm"
                @click="
                  isRegistError = null;
                  isEmptyId = false;
                "
              ></b-form-input
              ><b-alert show variant="danger" v-if="isEmptyId"
                >아이디를 입력하세요.</b-alert
              >
            </b-form-group>
            <b-form-group label="비밀번호:" label-for="userpwd">
              <b-form-input
                type="password"
                id="userpwd"
                v-model="user.userPwd"
                required
                placeholder="비밀번호 입력...."
                @keyup.enter="confirm"
                @click="
                  isNotEqualPwd = false;
                  isEmptyPwd = false;
                "
              ></b-form-input>
              <b-alert show variant="danger" v-if="isEmptyPwd"
                >비밀번호를 입력하세요.</b-alert
              > </b-form-group
            ><b-form-group label="비밀번호 확인:" label-for="userpwdCheck">
              <b-form-input
                type="password"
                id="userpwdCheck"
                v-model="pwdCheck"
                required
                placeholder="비밀번호 확인...."
                @keyup.enter="confirm"
                @click="isNotEqualPwd = false"
              ></b-form-input>
              <b-alert show variant="danger" v-if="isNotEqualPwd"
                >비밀번호가 일치하지 않습니다.</b-alert
              >
            </b-form-group>
            <b-form-group label="이름:" label-for="username">
              <b-form-input
                id="username"
                v-model="user.userName"
                required
                placeholder="이름 입력...."
                @keyup.enter="confirm"
                @click="isEmptyName = false"
              ></b-form-input> </b-form-group
            ><b-alert show variant="danger" v-if="isEmptyName"
              >이름을 입력하세요.</b-alert
            >
            <b-form-group label="이메일:" label-for="useremail">
              <b-form-input
                type="email"
                id="useremail"
                v-model="user.email"
                required
                placeholder="이메일 입력...."
                @keyup.enter="confirm"
                @click="isEmptyEmail = false"
              ></b-form-input
              ><b-alert show variant="danger" v-if="isEmptyEmail"
                >이메일을 입력하세요.</b-alert
              >
            </b-form-group>
            <b-button
              type="button"
              variant="primary"
              class="m-1"
              @click="confirm"
              >회원가입</b-button
            >
            <b-button
              type="button"
              variant="success"
              class="m-1"
              @click="movePage"
              >로그인</b-button
            >
          </b-form>
        </b-card>
      </b-col>
      <b-col></b-col>
    </b-row>
  </b-container>
</template>

<script>
import { mapActions } from "vuex";

const userStore = "userStore";

export default {
  name: "UserRegister",
  data() {
    return {
      // isLoginError: false,
      user: {
        userId: null,
        userPwd: null,
        userName: null,
        email: null,
      },
      pwdCheck: null,
      isEmptyId: false,
      isEmptyPwd: false,
      isNotEqualPwd: false,
      isEmptyName: false,
      isEmptyEmail: false,
      isRegistError: false,
    };
  },
  methods: {
    ...mapActions(userStore, ["userRegist"]),
    async confirm() {
      if (this.user.userId == null) {
        this.isEmptyId = true;
        return;
      }
      if (this.user.userPwd == null) {
        this.isEmptyPwd = true;
        return;
      }
      if (this.user.userPwd != this.pwdCheck) {
        this.isNotEqualPwd = true;
        return;
      }
      if (this.user.userName == null) {
        this.isEmptyName = true;
        return;
      }
      if (this.user.email == null) {
        this.isEmptyEmail = true;
        return;
      }
      console.log("vue에서...");
      console.log(this.user);
      let msg = await this.userRegist(this.user);
      console.log(msg);
      if (msg != "success") {
        this.isRegistError = msg;
        return;
      }
      alert("회원가입이 정상적으로 완료되었습니다!!");
      this.movePage();
    },
    movePage() {
      this.$router.push({ name: "login" });
    },
  },
};
</script>

<style></style>
