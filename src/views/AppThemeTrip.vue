<template>
  <b-container>
    Theme Trip
    <TheSearchbar />
    <the-map />
    <theme-list />
  </b-container>
</template>

<script>
import TheSearchbar from "@/components/TheSearchbar.vue";
import ThemeList from "@/components/themetrip/ThemeList.vue";
import TheMap from "@/components/TheMap.vue";
export default {
  name: "AppThemeTrip",
  components: {
    TheSearchbar,
    ThemeList,
    TheMap,
  },
  data() {
    return {
      message: "",
    };
  },
};
</script>

<style scoped></style>
