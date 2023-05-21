import { apiInstance } from "./index.js";

const api = apiInstance();

async function placeDetail(id, success, fail) {
  await api.get(`/place/${id}`).then(success).catch(fail);
}

async function getRegions(success, fail) {
  console.log("get regions....");
  await api.get(`/place/region`).then(success).catch(fail);
}

async function getContents(success, fail) {
  await api.get(`/place/content`).then(success).catch(fail);
}

async function searchPlace(param, condition, success, fail) {
  await api
    .post(`/place/search/${param.page}`, JSON.stringify(condition))
    .then(success)
    .catch(fail);
}

export { placeDetail, getRegions, getContents, searchPlace };
