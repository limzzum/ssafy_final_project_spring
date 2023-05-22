import { apiInstance } from "./index.js";

const api = apiInstance();

function listArticle(page, success, fail) {
  api.get(`/api/board/review/${page}`).then(success).catch(fail);
}

function writeArticle(article, success, fail) {
  console.log(article);
  api.post(`/api/board`, JSON.stringify(article)).then(success).catch(fail);
}

function getArticle(postId, success, fail) {
  console.log(postId)
  api.get(`/api/board/${postId}`).then(success).catch(fail);
}

function modifyArticle(article, success, fail) {
  api.put(`/api/board`, JSON.stringify(article)).then(success).catch(fail);
}

function deleteArticle(postId, success, fail) {
  api.delete(`/api/board/${postId}`).then(success).catch(fail);
}

export { listArticle, writeArticle, getArticle, modifyArticle, deleteArticle };
