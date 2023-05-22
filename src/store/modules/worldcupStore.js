import { getList, updateResult } from "@/api/worldcup";

const worldcupStore = {
  namespaced: true,
  state: {
    worldcups: [],
    selected: [],
    win: null,
    rounds: null,
    round: null,
    totalwins: null,
  },
  getters: {},
  mutations: {
    CLEAR_WORLDCUP_LIST(state) {
      state.worldcups = [];
    },
    CLEAR_SELECTED_LIST(state) {
      state.selected = [];
    },
    SET_WORLDCUP_LIST(state, selected) {
      state.totalwins = 0;
      selected.forEach((data) => {
        state.worldcups.push(data);
        state.totalwins += data.wins;
      });
      state.rounds = state.worldcups.length >> 1;
      state.round = 1;
    },
    SELECT_WORLDCUP_BY_NUM(state, num) {
      // console.log(state.selected);
      // console.log(state.worldcups);
      state.selected.push(state.worldcups[num]);
      state.worldcups.shift();
      state.worldcups.shift();
      if (state.worldcups.length) {
        state.round++;
      } else {
        state.round = 1;
        state.rounds >>= 1;
        state.worldcups = state.selected;
        state.selected = [];
      }
      // console.log(state.selected);
      // console.log(state.worldcups);
    },
    SET_WORLDCUP_WIN(state, win) {
      state.win = win;
    },
    SORT_WORLDCUP_LIST(state) {
      state.worldcups.sort(function (a, b) {
        return -(a.wins - b.wins);
      });
    },
  },
  actions: {
    async getWorldcupInfo({ commit }) {
      await getList(
        ({ data }) => {
          // console.log(data);
          commit("CLEAR_WORLDCUP_LIST");
          commit("CLEAR_SELECTED_LIST");
          commit("SET_WORLDCUP_LIST", data.result);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    sortWorldcupInfo({ commit }) {
      commit("SORT_WORLDCUP_LIST");
    },
    async updateWorldcupResult({ commit }, obj) {
      obj.wins++;
      await updateResult(
        obj.id,
        ({ data }) => {
          // console.log(data);
          commit("CLEAR_WORLDCUP_LIST");
          commit("SET_WORLDCUP_LIST", data.result);
          commit("SET_WORLDCUP_WIN", obj);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    chooseWorldcup({ commit }, num) {
      commit("SELECT_WORLDCUP_BY_NUM", num);
    },
  },
};

export default worldcupStore;
