import { placeDetail, getRegions, getContents, searchPlace } from "@/api/place";

const placeStore = {
  namespaced: true,
  state: {
    regions: [{ value: null, text: "선택하세요" }],
    contents: [{ value: null, text: "선택하세요" }],
    places: [],
    place: null,
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
  },
  actions: {
    async getRegionInfo({ commit }) {
      await getRegions(
        ({ data }) => {
          console.log(data);
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
    async searchPlace({ commit }, condition) {
      await searchPlace(
        condition,
        ({ data }) => {
          commit("SET_PLACE_LIST", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
  },
};

export default placeStore;