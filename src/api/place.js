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

async function searchPlace(condition, success, fail) {
  // condition :
  // {
  //   "addr1": "string",
  //   "contentId": 0,
  //   "contentTypeId": 0,
  //   "contentTypeName": "string",
  //   "firstImage": "string",
  //   "gugunCode": 0,
  //   "latitude": 0,
  //   "longitude": 0,
  //   "overview": "string",
  //   "sidoCode": 0,
  //   "sidoName": "string",
  //   "title": "string"
  // }
  await api.get(`/worldcup`).then(success).catch(fail);
}

export { placeDetail, getRegions, getContents, searchPlace };
