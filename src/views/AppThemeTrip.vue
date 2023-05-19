<template>
  <b-container> Theme Trip
    <search-bar></search-bar>
    
    </b-container>
</template>

<script>
import SearchBar from "@/components/themetrip/SearchBar.vue";
export default {
  name: "AppThemeTrip",
  components: {
    SearchBar
  },
  data() {
    return {
      message: "",
    };
  },
};
</script>

<style scoped></style>
