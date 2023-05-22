import { listArticle, writeArticle, getArticle, modifyArticle, deleteArticle } from "@/api/board";

const boardStore = {
  namespaced: true,
  state: {
    articles: [],
    condition: {
      contentTypeId: null,
      sidoCode: null,
      title:null
    },
    boardType:null,
    currentPage: 1,
    totalPageNum: 1,
  },
  getters: {},
  mutations: {
   
    CLEAR_ARTICLE_LIST(state) {
      state.articles = [];
      state.article = null;
    },
   
    SET_CONTENT_LIST(state, contents) {
      contents.forEach((content) => {
        state.contents.push({
          value: content.contentTypeId,
          text: content.contentTypeName,
        });
      });
    },
    SET_ARTICLE_LIST(state, article) {
      state.articles = article;
      console.log("boardlist set mutation ",state.articles);
    },
    SET_DETAIL_ARTICLE(state, article) {
      state.place = article;
    },
    SET_BOARD_TYPE(state, type) {
      state.boardType = type;
      console.log(state.boardType)
    },
    SET_CURRENT_PAGE(state, page) {
      console.log("store mutation")
      state.currentPage = page;
      console.log(state.currentPage)
    },
    SET_TOTAL_PAGE_NUM(state, num) {
      state.totalPageNum = num;
    },
    
  },

  actions: {
    async writeArticle({ commit }, id) {
      await writeArticle(
        id,
        ({ data }) => {
          commit("SET_DETAIL_PLACE", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    async modifyArticle({ commit }, id) {
      await modifyArticle(
        id,
        ({ data }) => {
          commit("SET_DETAIL_PLACE", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    async detailArticle({ commit }, id) {
      await getArticle(
        id,
        ({ data }) => {
          commit("SET_DETAIL_PLACE", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    async searchArticle({ commit, state }) {
      await listArticle(
        state.currentPage,
        { boardType: state.boardType },
        ({ data }) => {
          console.log(data.result);
          commit("SET_ARTICLE_LIST", data.result);
          commit("SET_TOTAL_PAGE_NUM", data.pages);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    async deleteArticle({ commit, state }) {
      await deleteArticle(
        { page: state.currentPage },
        state.condition,
        ({ data }) => {
          console.log(data.result);
          commit("SET_PLACE_LIST", data.result);
          commit("SET_TOTAL_PAGE_NUM", data.pages);
        },
        (error) => {
          console.log(error);
        }
      );
    },
  },
};

export default boardStore;
