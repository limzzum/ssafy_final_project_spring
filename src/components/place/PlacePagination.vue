<template>
  <b-pagination
    v-model="curPage"
    :total-rows="totalPageNum"
    :per-page="10"
    align="center"
    limit="9"
  ></b-pagination>
</template>

<script>
import { mapState, mapMutations, mapActions } from "vuex";
const placeStore = "placeStore";
export default {
  data() {
    return {
      curPage: 1,
    };
  },
  computed: {
    ...mapState(placeStore, ["currentPage", "totalPageNum"]),
  },
  watch: {
    curPage(page) {
      this.setPage(page);
      this.searchPlace();
    },
    currentPage(page) {
      this.curPage = page;
    },
  },
  methods: {
    ...mapMutations(placeStore, ["SET_CURRENT_PAGE"]),
    ...mapActions(placeStore, ["setPage", "searchPlace"]),
  },
};
</script>
