import { apiInstance } from "./index.js";

const api = apiInstance();

function listArticle(page, option, success, fail) {
  api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
  api
    .post(`/board/search/${page}`, JSON.stringify(option))
    .then(success)
    .catch(fail);
}

function writeArticle(article, success, fail) {
  console.log(article);
  api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
  api.post(`/board`, JSON.stringify(article)).then(success).catch(fail);
}

function getArticle(postId, success, fail) {
  // console.log(postId)
  api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
  api.get(`/board/${postId}`).then(success).catch(fail);
}

function modifyArticle(article, success, fail) {
  api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
  api.put(`/board`, JSON.stringify(article)).then(success).catch(fail);
}

function deleteArticle(postId, success, fail) {
  api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
  api.delete(`/board/${postId}`).then(success).catch(fail);
}

export { listArticle, writeArticle, getArticle, modifyArticle, deleteArticle };
