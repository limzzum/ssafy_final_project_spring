<template>
  <b-container>
    <TheSearchbar class="m-2" />
    <TheCarousel />
  </b-container>
</template>

<script>
import TheSearchbar from "@/components/TheSearchbar.vue";
import TheCarousel from "@/components/TheCarousel.vue";
export default {
  name: "AppMain",
  components: {
    TheSearchbar,
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
