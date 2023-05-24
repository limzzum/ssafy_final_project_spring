<template>
  <b-container>  
    <div style="display: flex;">
      <BoardTypeNav :isMy="false"/>
      <router-view/>
    </div>
 
    
  </b-container>
</template>

<script>
import BoardTypeNav from "@/components/board/item/BoardTypeNav.vue";
export default {
  name: "AppMain",
  components: {
    BoardTypeNav
  },
  data() {
    return {
      message: "",
    };
  },
};
</script>

<style scoped></style>
