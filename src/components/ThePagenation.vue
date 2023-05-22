<template>
  <div class="overflow-auto">
    <b-pagination
      v-model="curPage"
      :total-rows="rows"
      :per-page="perPage"
      aria-controls="my-table"
    ></b-pagination>

    <p class="mt-3">Current Page: {{ curPage }}</p>
  </div>
</template>

<script>
import { mapState, mapMutations, mapActions } from "vuex";
export default {
  data() {
    return {
      perPage: 10,
      curPage:1
    };
  },
  computed: {
    ...mapState("placeStore", ["places", "totalPageNum"]),
    

    rows() {
      console.log("total "+this.totalPageNum)
      return this.totalPageNum;
    },
  },
  watch: {

    curPage() {
      console.log("change page")
      this.setPage();
      
    }
  },
  methods: {
    ...mapMutations("placeStore", ["SET_CURRENT_PAGE"]),
    ...mapActions("placeStore", ["searchPlace"]),
    setPage() {
      console.log("change method")
      this.SET_CURRENT_PAGE(this.curPage);
      this.searchPlace();

    }
  }
};
</script>
