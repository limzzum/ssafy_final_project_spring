import { apiInstance } from "./index.js";

const api = apiInstance();

async function getList(success, fail) {
  api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
  await api.get(`/worldcup`).then(success).catch(fail);
}

async function updateResult(id, success, fail) {
  api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
  await api.get(`/worldcup/${id}`).then(success).catch(fail);
}

export { getList, updateResult };
