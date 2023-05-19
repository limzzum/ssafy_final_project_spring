import Vue from "vue";
import VueRouter from "vue-router";
import AppMain from "@/views/AppMain.vue";
import AppThemeTrip from "@/views/AppThemeTrip.vue";
import AppMyPlan from "@/views/AppMyPlan.vue";
import AppWorldcup from "@/views/AppWorldcup.vue";
import AppBoard from "@/views/AppBoard.vue";
import AppRecommend from "@/views/AppRecommend.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "main",
    component: AppMain,
  },
  {
    path: "/themetrip",
    name: "themetrip",
    component: AppThemeTrip,
  },
  {
    path: "/myplan",
    name: "myplan",
    component: AppMyPlan,
  },
  {
    path: "/worldcup",
    name: "worldcup",
    component: AppWorldcup,
  },
  {
    path: "/recommend",
    name: "recommend",
    component: AppRecommend,
  },
  {
    path: "/board",
    name: "board",
    component: AppBoard,
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
