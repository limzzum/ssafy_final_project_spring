<template>
  <div id="app">
    <TheNavbar />
    <router-view />
  </div>
</template>
<script>
import TheNavbar from "@/components/TheNavbar.vue";
import Vue from "vue";
import { BootstrapVue, IconsPlugin } from "bootstrap-vue";
export default {
  name: "App",
  components: {
    TheNavbar,
  },
};

Vue.use(BootstrapVue);
Vue.use(IconsPlugin);
</script>
<style></style>
