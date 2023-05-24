import Vue from "vue";
import VueRouter from "vue-router";
import AppMain from "@/views/AppMain.vue";
import AppThemeTrip from "@/views/AppThemeTrip.vue";
import AppMyPlan from "@/views/AppMyPlan.vue";
import AppBoard from "@/views/AppBoard.vue";
import AppAdjacent from "@/views/AppAdjacent.vue";

import store from "@/store";

Vue.use(VueRouter);

const onlyAuthUser = async (to, from, next) => {
  const checkUserInfo = store.getters["userStore/checkUserInfo"];
  const checkToken = store.getters["userStore/checkToken"];
  let token = sessionStorage.getItem("access-token");

  if (checkUserInfo != null && token) {
    await store.dispatch("userStore/getUserInfo", token);
  }
  if (!checkToken || checkUserInfo === null) {
    alert("로그인이 필요한 페이지입니다..");
    // next({ name: "login" });
    router.push({ name: "login" }).catch(() => {});
  } else {
    next();
  }
};

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
    beforeEnter: onlyAuthUser,
  },
  {
    path: "/adjacent",
    name: "adjacent",
    component: AppAdjacent,
    beforeEnter: onlyAuthUser,
  },
  {
    path: "/board",
    name: "board",
    beforeEnter: onlyAuthUser,
    component: AppBoard,
    redirect: "/board/list",
    children: [
      {
        path: "list",
        name: "boardlist",
        props:{isMy:false},
        component: () =>
          import(
            /* webpackChunkName: "board" */ "@/components/board/BoardList"
          ),
      },
      {
        path: "write",
        name: "boardwrite",
        // beforeEnter: onlyAuthUser,
        component: () =>
          import(
            /* webpackChunkName: "board" */ "@/components/board/BoardWrite"
          ),
      },
      {
        path: "view/:postId",
        name: "boardview",
        // beforeEnter: onlyAuthUser,
        component: () =>
          import(
            /* webpackChunkName: "board" */ "@/components/board/BoardView"
          ),
      },
      {
        path: "modify",
        name: "boardmodify",
        // beforeEnter: onlyAuthUser,
        component: () =>
          import(
            /* webpackChunkName: "board" */ "@/components/board/BoardModify"
          ),
      },
      {
        path: "delete/:postId",
        name: "boarddelete",
        // beforeEnter: onlyAuthUser,
        component: () =>
          import(
            /* webpackChunkName: "board" */ "@/components/board/BoardDelete"
          ),
      },
    ],
  },
  {
    path: "/user",
    name: "user",
    component: () => import(/* webpackChunkName: "user" */ "@/views/AppUser"),
    children: [
      {
        path: "join",
        name: "join",
        component: () =>
          import(
            /* webpackChunkName: "user" */ "@/components/user/UserRegister"
          ),
      },
      {
        path: "login",
        name: "login",
        component: () =>
          import(/* webpackChunkName: "user" */ "@/components/user/UserLogin"),
      },
      {
        path: "mypage",
        name: "mypage",
        beforeEnter: onlyAuthUser,
        component: () =>
          import(/* webpackChunkName: "user" */ "@/components/user/UserMyPage"),
        children: [
          {
            path: "myboardlist",
            name: "myboardlist",
            props:{isMy:true},
            component: () =>
              import(
                /* webpackChunkName: "mypage" */ "@/components/board/BoardList"
              ),
          },
          {
            path: "myboardview",
            name: "myboardview",
            component: () =>
              import(
                /* webpackChunkName: "mypage" */ "@/components/board/BoardView"
              ),
          },
        ]
      },
    ],
  },
  {
    path: "/worldcup",
    name: "worldcup",
    beforeEnter: onlyAuthUser,
    component: () =>
      import(/* webpackChunkName: "user" */ "@/views/AppWorldcup.vue"),

    redirect: "/worldcup/progress",
    children: [
      {
        path: "progress",
        name: "progress",
        component: () =>
          import(
            /* webpackChunkName: "user" */ "@/components/worldcup/WorldcupProgress.vue"
          ),
      },
      {
        path: "result",
        name: "result",
        component: () =>
          import(
            /* webpackChunkName: "user" */ "@/components/worldcup/WorldcupResult.vue"
          ),
      },
    ],
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
