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
      return this.totalPageNum;
    },
  },
  watch: {
    curPage() {
      this.setPage();
    },
  },
  methods: {
    ...mapMutations("boardStore", ["SET_CURRENT_PAGE"]),
    ...mapActions("boardStore", ["searchArticle"]),
    setPage() {
      this.SET_CURRENT_PAGE(this.curPage);
      this.searchArticle();
    },
  },
};
</script>
