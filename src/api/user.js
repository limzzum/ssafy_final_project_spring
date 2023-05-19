import { apiInstance } from "./index.js";

const api = apiInstance();

async function login(user, success, fail) {
  await api.post(`/user/login`, JSON.stringify(user)).then(success).catch(fail);
}

async function findByUserNo(userNo, success, fail) {
  api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
  await api.get(`/user/select/${userNo}`).then(success).catch(fail);
}

async function tokenRegeneration(user, success, fail) {
  api.defaults.headers["refresh-token"] =
    sessionStorage.getItem("refresh-token"); //axios header에 refresh-token 셋팅
  await api.post(`/user/refresh`, user).then(success).catch(fail);
}

async function regist(user, success, fail) {
  console.log(JSON.stringify(user));
  await api.post(`/user`, JSON.stringify(user)).then(success).catch(fail);
}

async function logout(userNo, success, fail) {
  api.defaults.headers["access-token"] = sessionStorage.getItem("access-token"); //axios header에 refresh-token 셋팅
  await api.get(`/user/logout/${userNo}`).then(success).catch(fail);
}

export { login, findByUserNo, tokenRegeneration, logout, regist };
