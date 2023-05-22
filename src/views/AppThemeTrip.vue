<template>
  <b-container>
    <router-view />
  </b-container>
</template>

<script>
// import TheSearchbar from "@/components/TheSearchbar.vue";
// import TheMap from "@/components/TheMap.vue";
export default {
  name: "AppThemeTrip",
  components: {
    // TheMap,
  },
  data() {
    return {
      message: "",
    };
  },
};
</script>

<style scoped></style>
