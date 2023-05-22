<template>
    
<b-button-group vertical
style="border: 1px; width: 230px; height: 200px;">
    <b-button @click="search('notice')" variant="outline-secondary">공지사항</b-button>
<b-button @click="search('review')" variant="outline-secondary">여행후기</b-button>
<b-button @click="search('free')" variant="outline-secondary">자유게시판</b-button>
<b-button @click="search('team')" variant="outline-secondary">일행구하기</b-button>

</b-button-group>
  </template>
  
  <script>
import { mapMutations, mapActions } from "vuex";
const boardStore = "boardStore";
  export default {
    name: "BoardTypeNav",
      data() {
            return {
                
            };
    },

    methods: {
    ...mapMutations(boardStore, ["SET_BOARD_TYPE"]),
    ...mapActions(boardStore, [
      "searchArticle",
    ]),
  
    async search(type) {
      console.log("search")
          this.SET_BOARD_TYPE(type);
      this.searchArticle();
      // },
      // TODO : search 후 /themetrip 이동 구현
    },
  },


   
  };
  </script>
  

  
  <style></style>
  
