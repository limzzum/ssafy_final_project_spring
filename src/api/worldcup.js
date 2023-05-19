import { apiInstance } from "./index.js";

const api = apiInstance();

async function getList(success, fail) {
  await api.get(`/worldcup`).then(success).catch(fail);
}

async function updateResult(id, success, fail) {
  await api.get(`/worldcup/${id}`).then(success).catch(fail);
}

export { getList, updateResult };
