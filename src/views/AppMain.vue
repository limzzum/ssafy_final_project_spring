<template>
  <b-container> Main <TheSearchbar /></b-container>
</template>

<script>
import TheSearchbar from "@/components/TheSearchbar.vue";
export default {
  name: "AppMain",
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
