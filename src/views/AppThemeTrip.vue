<template>
  <b-container>
    Theme Trip
    <TheSearchbar />
  </b-container>
</template>

<script>
import TheSearchbar from "@/components/TheSearchbar.vue";
export default {
  name: "AppThemeTrip",
  components: {
    TheSearchbar,
  },
  data() {
    return {
      message: "",
    };
  },
};
</script>

<style scoped></style>
