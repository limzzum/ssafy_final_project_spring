import { placeDetail, getRegions, getContents, searchPlace } from "@/api/place";

const placeStore = {
  namespaced: true,
  state: {
    regions: [{ value: null, text: "선택하세요" }],
    contents: [{ value: null, text: "선택하세요" }],
    places: [],
    selected: [],
    place: null,
    condition: {
      contentTypeId: null,
      sidoCode: null,
      title: null,
      latitude: null,
      longitude: null,
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
    CLEAR_SELECTED_LIST(state) {
      state.selected = [];
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
    SET_PLACE(state, place) {
      state.place = place;
    },
    SET_PLACE_LIST(state, places) {
      state.places = places;
    },
    SET_DETAIL_PLACE(state, place) {
      state.place = place;
    },
    SET_CONDITION(state, condition) {
      state.condition = condition;
      // console.log("condition : " + state.condition);
    },
    SET_CURRENT_PAGE(state, page) {
      state.currentPage = page;
      // console.log("current page : " + state.currentPage);
    },
    SET_TOTAL_PAGE_NUM(state, num) {
      state.totalPageNum = num;
    },
    SET_SELECTED(state, list) {
      state.selected = list;
    },
    ADD_SELECTED(state, place) {
      for (let sel of state.selected) {
        if (sel.contentId == place.contentId) return;
      }
      state.selected.push(place);
      // console.log(state.selected);
    },
    SUB_SELECTED(state, place) {
      let tmp = [];
      for (let sel of state.selected) {
        if (sel.contentId == place.contentId) continue;
        tmp.push(sel);
      }
      state.selected = tmp;
      // console.log(state.selected);
    },
  },

  actions: {
    async getRegionInfo({ commit }) {
      await getRegions(
        ({ data }) => {
          // console.log(data);
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
    clearPlace({ commit }) {
      commit("CLEAR_PLACE_LIST");
      commit("CLEAR_SELECTED_LIST");
    },
    clearPlaceList({ commit }) {
      commit("CLEAR_PLACE_LIST");
    },
    setPlace({ commit }, place) {
      commit("SET_PLACE", place);
    },
    async searchPlace({ commit, state }) {
      // console.log(state.condition);
      await searchPlace(
        { page: state.currentPage },
        state.condition,
        ({ data }) => {
          // console.log(data);
          // console.log(data.result);
          commit("SET_PLACE_LIST", data.result);
          commit("SET_TOTAL_PAGE_NUM", data.totalNum);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    setPage({ commit }, page) {
      commit("SET_CURRENT_PAGE", page);
    },
    setCondition({ commit }, condition) {
      commit("SET_CONDITION", condition);
      commit("SET_CURRENT_PAGE", 1);
    },
    selectPlace({ commit }, place) {
      commit("ADD_SELECTED", place);
    },
    unselectPlace({ commit }, place) {
      commit("SUB_SELECTED", place);
    },
    setSelected({ commit }, list) {
      commit("SET_SELECTED", list);
    },
  },
};

export default placeStore;
