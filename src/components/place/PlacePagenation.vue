<template>
  <b-pagination
    v-model="curPage"
    :total-rows="rows"
    :per-page="perPage"
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
      perPage: 10,
      curPage: 1,
    };
  },
  computed: {
    ...mapState(placeStore, ["places", "totalPageNum"]),
    rows() {
      return this.totalPageNum;
    },
  },
  watch: {
    curPage() {
      this.setPage();
    },
  },
  methods: {
    ...mapMutations(placeStore, ["SET_CURRENT_PAGE"]),
    ...mapActions(placeStore, ["searchPlace"]),
    setPage() {
      this.SET_CURRENT_PAGE(this.curPage);
      this.searchPlace();
    },
  },
};
</script>
