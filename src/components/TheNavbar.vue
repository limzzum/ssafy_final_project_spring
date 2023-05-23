<template>
  <div>
    <b-navbar toggleable="lg" type="light" variant="light">
      <b-container>
        <b-navbar-brand href="#"
          ><router-link to="/"
            ><b-img
              rounded
              id="logo-img"
              src="@/assets/img/trip_logo.png" /></router-link
        ></b-navbar-brand>

        <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

        <b-collapse id="nav-collapse" is-nav>
          <b-navbar-nav>
            <b-nav-item href="#"
              ><router-link to="/themetrip"
                ><b-row class="p-3" @click="clearPlace"
                  >테마 여행</b-row
                ></router-link
              ></b-nav-item
            >
            <b-nav-item href="#"
              ><router-link to="/myplan"
                ><b-row class="p-3" @click="clearPlace"
                  >나의 여행 계획</b-row
                ></router-link
              ></b-nav-item
            >
            <b-nav-item href="#"
              ><router-link to="/adjacent"
                ><b-row class="p-3" @click="clearPlace"
                  >가까운 관광지</b-row
                ></router-link
              ></b-nav-item
            >
            <b-nav-item href="#"
              ><router-link to="/worldcup"
                ><b-row class="p-3">관광지 월드컵</b-row></router-link
              ></b-nav-item
            >
            <b-nav-item href="#"
              ><router-link to="/board"
                ><b-row class="p-3">게시판</b-row></router-link
              ></b-nav-item
            >
          </b-navbar-nav>

          <!-- after login -->
          <b-navbar-nav class="ml-auto" v-if="userInfo">
            <b-nav-item class="align-self-center">
              <b-avatar
                variant="primary"
                v-text="userInfo.userId.charAt(0).toUpperCase()"
              ></b-avatar>
              {{ userInfo.userName }}({{ userInfo.userId }})님 환영합니다.
            </b-nav-item>
            <b-nav-item class="align-self-center">
              <router-link
                :to="{ name: 'mypage' }"
                class="link align-self-center"
                >내정보보기</router-link
              >
            </b-nav-item>
            <b-nav-item
              class="align-self-center link"
              @click.prevent="onClickLogout"
              >로그아웃</b-nav-item
            >
          </b-navbar-nav>
          <!-- before login -->
          <b-navbar-nav class="ml-auto" v-else>
            <b-nav-item-dropdown right>
              <template #button-content>
                <b-icon icon="people" font-scale="2"></b-icon>
              </template>
              <b-dropdown-item href="#">
                <router-link :to="{ name: 'join' }" class="link">
                  <b-icon icon="person-circle"></b-icon> 회원가입
                </router-link>
              </b-dropdown-item>
              <b-dropdown-item href="#">
                <router-link :to="{ name: 'login' }" class="link">
                  <b-icon icon="key"></b-icon> 로그인
                </router-link>
              </b-dropdown-item>
            </b-nav-item-dropdown>
          </b-navbar-nav>
        </b-collapse>
      </b-container>
    </b-navbar>
  </div>
</template>

<script>
import { mapState, mapGetters, mapActions } from "vuex";
const userStore = "userStore";
const placeStore = "placeStore";
export default {
  name: "TheNavbar",
  computed: {
    ...mapState(userStore, ["isLogin", "userInfo"]),
    ...mapGetters(["checkUserInfo"]),
  },
  methods: {
    ...mapActions(userStore, ["userLogout"]),
    ...mapActions(placeStore, ["clearPlace"]),
    // ...mapMutations(memberStore, ["SET_IS_LOGIN", "SET_USER_INFO"]),
    onClickLogout() {
      // this.SET_IS_LOGIN(false);
      // this.SET_USER_INFO(null);
      // sessionStorage.removeItem("access-token");
      // if (this.$route.path != "/") this.$router.push({ name: "main" });
      console.log(this.userInfo.userNo);
      //vuex actions에서 userLogout 실행(Backend에 저장 된 리프레시 토큰 없애기
      //+ satate에 isLogin, userInfo 정보 변경)
      // this.$store.dispatch("userLogout", this.userInfo.userid);
      this.userLogout(this.userInfo.userNo);
      sessionStorage.removeItem("access-token"); //저장된 토큰 없애기
      sessionStorage.removeItem("refresh-token"); //저장된 토큰 없애기
      if (this.$route.path != "/") this.$router.push({ name: "main" });
    },
  },
};
</script>

<style scoped>
#logo-img {
  height: 100px;
}
a {
  text-decoration: none;
  color: black;
}
a:hover {
  text-decoration: none;
  color: darkslateblue;
}
</style>
