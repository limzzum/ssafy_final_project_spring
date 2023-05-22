<template>
  <b-container>  
    <div style="display: flex;">
      <b-button-group vertical 
      style="border: 1px; width: 230px; height: 200px;">
          <b-button variant="outline-secondary">공지사항</b-button>
    <b-button variant="outline-secondary">여행후기</b-button>
    <b-button variant="outline-secondary">자유게시판</b-button>
    <b-button variant="outline-secondary">일행구하기</b-button>
    
  </b-button-group>
      <router-view/>
    </div>
 
    
  </b-container>
</template>

<script>
export default {
  name: "AppMain",
  components: {},
  data() {
    return {
      message: "",
    };
  },
};
</script>

<style scoped></style>
