import { apiInstance } from "./index.js";

const api = apiInstance();

function writeComment(comment, success, fail) {
  console.log(comment);
  api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
  api.post(`/comment`, JSON.stringify(comment)).then(success).catch(fail);
}

export { writeComment };
