<template>
    
<b-button-group vertical
style="border: 1px; width: 230px; height: 200px;">
    <b-button @click="search('notice','공지사항')" variant="outline-secondary">공지사항</b-button>
<b-button @click="search('review','여행후기')" variant="outline-secondary">여행후기</b-button>
<b-button @click="search('free','자유게시판')" variant="outline-secondary">자유게시판</b-button>
<b-button @click="search('team','일행구하기')" variant="outline-secondary">일행구하기</b-button>
<b-button @click="search('plan','여행코스')" variant="outline-secondary">여행 코스</b-button>

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
    ...mapMutations(boardStore, ["SET_BOARD_TYPE","SET_BOARD_TITLE"]),
    ...mapActions(boardStore, [
      "searchArticle",
    ]),
  
    async search(type,title) {
      console.log("search")
      
      this.SET_BOARD_TYPE(type);
      this.SET_BOARD_TITLE(title);
      this.searchArticle();
      
      this.$router.push({ name: "boardlist" }).catch(() => { });
      
      // },
      // TODO : search 후 /themetrip 이동 구현
    },
  },


   
  };
  </script>
  

  
  <style></style>
  
