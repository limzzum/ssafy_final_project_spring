import jwtDecode from "jwt-decode";
import router from "@/router";
import {
  login,
  findByUserNo,
  tokenRegeneration,
  logout,
  regist,
  update,
} from "@/api/user";

const userStore = {
  namespaced: true,
  state: {
    isLogin: false,
    isLoginError: false,
    userInfo: null,
    userName : null,
    isValidToken: false,
  },
  getters: {
    checkUserInfo: function (state) {
      return state.userInfo;
    },
    checkToken: function (state) {
      return state.isValidToken;
    },
  },
  mutations: {
    SET_IS_LOGIN: (state, isLogin) => {
      state.isLogin = isLogin;
    },
    SET_IS_LOGIN_ERROR: (state, isLoginError) => {
      state.isLoginError = isLoginError;
    },
    SET_IS_VALID_TOKEN: (state, isValidToken) => {
      state.isValidToken = isValidToken;
    },
    SET_USER_INFO: (state, userInfo) => {
      state.isLogin = true;
      state.userInfo = userInfo;
    },
    SET_USER_NAME: (state, userName) => {
      state.isLogin = true;
      state.userName = userName;
    },
  },
  actions: {
    async userConfirm({ commit }, user) {
      await login(
        user,
        ({ data }) => {
          console.log(data);
          if (data.msg === "success") {
            let accessToken = data["access-token"];
            let refreshToken = data["refresh-token"];
            let userNo = data["user"]["userNo"];
            // console.log("login success token created!!!! >> ", accessToken, refreshToken);
            commit("SET_IS_LOGIN", true);
            commit("SET_IS_LOGIN_ERROR", false);
            commit("SET_IS_VALID_TOKEN", true);
            sessionStorage.setItem("access-token", accessToken);
            sessionStorage.setItem("refresh-token", refreshToken);
            sessionStorage.setItem("userNo", userNo);
          } else {
            commit("SET_IS_LOGIN", false);
            commit("SET_IS_LOGIN_ERROR", true);
            commit("SET_IS_VALID_TOKEN", false);
            console.log("userStore.js -> userConfirm : 로그인 실패");
          }
        },
        (error) => {
          console.log(error);
        }
      );
    },
    async userRegist({ commit }, user) {
      console.log(commit);
      let msg = null;
      await regist(
        user,
        ({ data }) => {
          console.log(data);
          if (data.msg === "success") {
            msg = "success";
          } else {
            msg = data.result;
          }
        },
        async (error) => {
          console.log(error);
          msg = "통신 오류 : 잠시 후 다시 시도해보세요";
        }
      );
      return msg;
    },
    async userUpdate({ commit }, user) {
      console.log("update ",commit);
      let msg = null;
      await update(
        user,
        ({ data }) => {
          console.log(data);
          if (data.msg === "success") {
            msg = "success";
          } else {
            msg = data.result;
          }
        },
        async (error) => {
          console.log(error);
          msg = "통신 오류 : 잠시 후 다시 시도해보세요";
        }
      );
      return msg;
    },
    async getUserInfo({ commit, dispatch }, token) {
      let decodeToken = jwtDecode(token);
      await findByUserNo(
        decodeToken.sub,
        ({ data }) => {
          if (data.msg === "success") {
            commit("SET_USER_INFO", data.user);
            commit("SET_USER_NAME", data.user.userName);
          } else {
            console.log("유저 정보 없음!!!!");
          }
        },
        async (error) => {
          console.log(
            "getUserInfo() error code [토큰 만료되어 사용 불가능.] ::: ",
            error.response.status
          );
          commit("SET_IS_VALID_TOKEN", false);
          await dispatch("tokenRegeneration");
        }
      );
    },
    async tokenRegeneration({ commit, state }) {
      await tokenRegeneration(
        ({ data }) => {
          if (data.msg === "success") {
            let accessToken = data["access-token"];
            sessionStorage.setItem("access-token", accessToken);
            commit("SET_IS_VALID_TOKEN", true);
          } else {
            console.log("리프레시 토큰 만료")
          }
        },
        async (error) => {
          // HttpStatus.UNAUTHORIZE(401) : RefreshToken 기간 만료 >> 다시 로그인!!!!
          console.log(error)
          if (error.response.status === 401) {
            console.log("갱신 실패");
            // 다시 로그인 전 DB에 저장된 RefreshToken 제거.
            await logout(
              state.userInfo.userid,
              ({ data }) => {
                if (data.message === "success") {
                  console.log("리프레시 토큰 제거 성공");
                } else {
                  console.log("리프레시 토큰 제거 실패");
                }
                alert("RefreshToken 기간 만료!!! 다시 로그인해 주세요.");
                commit("SET_IS_LOGIN", false);
                commit("SET_USER_INFO", null);
                commit("SET_IS_VALID_TOKEN", false);
                router.push({ name: "login" });
              },
              (error) => {
                console.log(error);
                commit("SET_IS_LOGIN", false);
                commit("SET_USER_INFO", null);
              }
            );
          }
        }
      );
    },
    async userLogout({ commit }, userNo) {
      console.log("userStore.js : logout 시도");
      await logout(
        userNo,
        ({ data }) => {
          console.log("userLogout 성공 : ");
          console.log(data);
          if (data.msg === "success") {
            commit("SET_IS_LOGIN", false);
            commit("SET_USER_INFO", null);
            commit("SET_IS_VALID_TOKEN", false);
          } else {
            console.log("유저 정보 없음!!!!");
          }
        },
        (error) => {
          console.log(error);
        }
      );
    },
  },
};

export default userStore;
