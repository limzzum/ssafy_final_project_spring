import { placeDetail, getRegions, getContents, searchPlace } from "@/api/place";

const placeStore = {
  namespaced: true,
  state: {
    regions: [{ value: null, text: "선택하세요" }],
    contents: [{ value: null, text: "선택하세요" }],
    places: [],
    place: null,
    condition: {
      contentTypeId: null,
      sidoCode: null,
      title:null
    },
    currentPage: 1,
    totalPageNum: 1,
  },
  getters: {},
  mutations: {
    CLEAR_REGION_LIST(state) {
      state.regions = [{ value: null, text: "선택하세요" }];
    },
    CLEAR_CONTENT_LIST(state) {
      state.contents = [{ value: null, text: "선택하세요" }];
    },
    CLEAR_PLACE_LIST(state) {
      state.places = [];
      state.place = null;
    },
    SET_REGION_LIST(state, regions) {
      regions.forEach((region) => {
        state.regions.push({ value: region.sidoCode, text: region.sidoName });
      });
    },
    SET_CONTENT_LIST(state, contents) {
      contents.forEach((content) => {
        state.contents.push({
          value: content.contentTypeId,
          text: content.contentTypeName,
        });
      });
    },
    SET_PLACE_LIST(state, places) {
      state.places = places;
    },
    SET_DETAIL_PLACE(state, place) {
      state.place = place;
    },
    SET_CONDITION(state, condition) {
      console.log("set condition");
      state.condition = condition;
      console.log(state.condition)
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
    async getRegionInfo({ commit }) {
      await getRegions(
        ({ data }) => {
          console.log(data);
          commit("CLEAR_REGION_LIST");
          commit("SET_REGION_LIST", data.result);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    async getContentInfo({ commit }) {
      await getContents(
        ({ data }) => {
          commit("CLEAR_CONTENT_LIST");
          commit("SET_CONTENT_LIST", data.result);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    async detailPlace({ commit }, id) {
      await placeDetail(
        id,
        ({ data }) => {
          commit("SET_DETAIL_PLACE", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    async searchPlace({ commit, state }) {
      console.log(this.condition);
      await searchPlace(
        { page: state.currentPage },
        state.condition,
        ({ data }) => {
          console.log(data);
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

export default placeStore;
