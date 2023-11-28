<template>
  <b-container>
    <PlaceSearchbar class="m-2" route="themetrip" />
    <TheCarousel />
  </b-container>
</template>

<script>
import PlaceSearchbar from "@/components/place/PlaceSearchbar.vue";
import TheCarousel from "@/components/TheCarousel.vue";
export default {
  name: "AppMain",
  components: {
    PlaceSearchbar,
    TheCarousel,
  },
  data() {
    return {
      message: "",
    };
  },
};
</script>

<style scoped></style>
