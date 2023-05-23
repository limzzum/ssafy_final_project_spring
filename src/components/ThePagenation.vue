<template>
  <b-pagination
    v-model="curPage"
    :total-rows="rows"
    :per-page="perPage"
    align="fill"
    limit="7"
  ></b-pagination>
</template>

<script>
import { mapState, mapMutations, mapActions } from "vuex";
export default {
  data() {
    return {
      perPage: 10,
      curPage: 1,
    };
  },
  computed: {
    ...mapState("placeStore", ["places", "totalPageNum"]),
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
    ...mapMutations("placeStore", ["SET_CURRENT_PAGE"]),
    ...mapActions("placeStore", ["searchPlace"]),
    setPage() {
      this.SET_CURRENT_PAGE(this.curPage);
      this.searchPlace();
    },
  },
};
</script>
