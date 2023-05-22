<template>
  <b-container> AppBoard 

    <router-view/>
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
