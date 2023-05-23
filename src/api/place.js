import { apiInstance } from "./index.js";

const api = apiInstance();

async function placeDetail(id, success, fail) {
  await api.get(`/place/${id}`).then(success).catch(fail);
}

async function getRegions(success, fail) {
  await api.get(`/place/region`).then(success).catch(fail);
}

async function getContents(success, fail) {
  await api.get(`/place/content`).then(success).catch(fail);
}

async function searchPlace(param, condition, success, fail) {
  api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
  await api
    .post(`/place/search/${param.page}`, JSON.stringify(condition))
    .then(success)
    .catch(fail);
}

export { placeDetail, getRegions, getContents, searchPlace };
