<template>
  <div id="app">
    <TheNavbar />
    <router-view />
    <TheFooter />
  </div>
</template>
<script>
import TheNavbar from "@/components/TheNavbar.vue";
import TheFooter from "@/components/TheFooter.vue";
import Vue from "vue";
import { BootstrapVue, IconsPlugin } from "bootstrap-vue";
export default {
  name: "App",
  components: {
    TheNavbar,
    TheFooter,
  },
};

Vue.use(BootstrapVue);
Vue.use(IconsPlugin);
</script>
<style>
.table th,
.table td {
  vertical-align: middle;
}
#app {
  font-family: "AppFont";
}
@font-face {
  font-family: "AppFont";
  src: url("./assets/font/SUITE-Regular.ttf") format("truetype");
  font-weight: 400;
}
</style>
