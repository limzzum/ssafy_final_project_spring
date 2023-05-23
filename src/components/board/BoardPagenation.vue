<template>
  <div class="overflow-auto">
    <b-pagination
      v-model="curPage"
      :total-rows="rows"
      :per-page="perPage"
      align="center"
      limit="9"
    ></b-pagination>
  </div>
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
    ...mapState("boardStore", ["articles", "totalPageNum"]),

    rows() {
      console.log("total " + this.totalPageNum);
      return this.totalPageNum;
    },
  },
  watch: {
    curPage() {
      console.log("change page");
      this.setPage();
    },
  },
  methods: {
    ...mapMutations("boardStore", ["SET_CURRENT_PAGE"]),
    ...mapActions("boardStore", ["searchArticle"]),
    setPage() {
      console.log("change method");
      this.SET_CURRENT_PAGE(this.curPage);
      this.searchArticle();
    },
  },
};
</script>
