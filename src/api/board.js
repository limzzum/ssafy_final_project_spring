import { apiInstance } from "./index.js";

const api = apiInstance();

function listArticle(page,option, success, fail) {
  api.post(`/board/search/${page}`, JSON.stringify(option)).then(success).catch(fail);
}

function writeArticle(article, success, fail) {
  console.log(article);
  api.post(`/board`, JSON.stringify(article)).then(success).catch(fail);
}

function getArticle(postId, success, fail) {
  console.log(postId)
  api.get(`/board/${postId}`).then(success).catch(fail);
}

function modifyArticle(article, success, fail) {
  api.put(`/board`, JSON.stringify(article)).then(success).catch(fail);
}

function deleteArticle(postId, success, fail) {
  api.delete(`/board/${postId}`).then(success).catch(fail);
}

export { listArticle, writeArticle, getArticle, modifyArticle, deleteArticle };
