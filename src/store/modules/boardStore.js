import {
  listArticle,
  writeArticle,
  // eslint-disable-next-line no-unused-vars
  getArticle,
  // eslint-disable-next-line no-unused-vars
  modifyArticle,
  // eslint-disable-next-line no-unused-vars
  deleteArticle,
} from "@/api/board";

const boardStore = {
  namespaced: true,
  state: {
    boardTypeData: {
      notice: "공지사항",
      review: "여행후기",
      free: "자유게시판",
      team: "일행구하기",
      plan: "여행코스",
    },
    articles: [],
    article: {},
    boardType: "notice",
    condition: {
      title: null,
      content: null,
      userName: null,
      userNo: null,
      boardType: null,
    },
    currentPage: 1,
    totalPageNum: 1,
  },
  getters: {},
  mutations: {
    CLEAR_ARTICLE_LIST(state) {
      state.articles = [];
      state.article = null;
    },
    SET_ARTICLE_LIST(state, article) {
      state.articles = article;
    },
    SET_ARTICLE(state, article) {
      state.article = article;
    },
    SET_BOARD_TYPE(state, type) {
      state.boardType = type;
    },
    SET_BOARD_TITLE(state, title) {
      state.boardTitle = title;
    },
    SET_CONDITION(state, condition) {
      state.condition = condition;
    },
    SET_CURRENT_PAGE(state, page) {
      state.currentPage = page;
    },
    SET_TOTAL_PAGE_NUM(state, num) {
      state.totalPageNum = num;
    },
  },

  actions: {
    async writeArticle({ commit }, article) {
      console.log(article);
      await writeArticle(
        article,
        ({ data }) => {
          if (data.msg != "success") {
            alert("오류가 발생했습니다. 잠시 후 다시 시도해주세요.");
          }
        },
        (error) => {
          console.log(error);
        }
      );
      commit("SET_BOARD_TYPE", article.boardType);
    },
    setCondition({ commit }, condition) {
      commit("SET_CONDITION", condition);
      commit("SET_CURRENT_PAGE", 1);
    },
    setBoardType({ commit }, type) {
      commit("SET_BOARD_TYPE", type);
    },
    async searchArticle({ commit, state }) {
      // console.log(state.condition);
      await listArticle(
        state.currentPage,
        state.condition,
        ({ data }) => {
          // console.log(data);
          commit("SET_ARTICLE_LIST", data.result);
          commit("SET_TOTAL_PAGE_NUM", data.totalNum);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    setArticle({ commit }, article) {
      commit("SET_ARTICLE", article);
    },
  },
};

export default boardStore;
