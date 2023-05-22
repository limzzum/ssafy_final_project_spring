<template>
  <div>
    <b-carousel
      id="carousel-1"
      v-model="slide"
      :interval="4000"
      controls
      indicators
      background="#ababab"
      img-width="1024"
      img-height="480"
      style="text-shadow: 1px 1px 2px #333"
    >
      <!-- Slides with custom text -->
      <b-carousel-slide img-src="@/assets/img/carousel1.png">
        <router-link to="themetrip"><h1>테마 여행</h1></router-link>
      </b-carousel-slide>
      <!-- Slides with custom text -->
      <b-carousel-slide img-src="@/assets/img/carousel2.png">
        <router-link to="myplan"><h1>나의 여행 계획</h1></router-link>
      </b-carousel-slide>
      <!-- Slides with custom text -->
      <b-carousel-slide img-src="@/assets/img/carousel3.png">
        <router-link to="worldcup"><h1>관광지 월드컵</h1></router-link>
      </b-carousel-slide>
      <!-- Slides with custom text -->
      <b-carousel-slide img-src="@/assets/img/carousel4.png">
        <router-link to="recommend"><h1>맛집 뽑기</h1></router-link>
      </b-carousel-slide>
      <!-- Slides with custom text -->
      <b-carousel-slide img-src="@/assets/img/carousel5.png">
        <router-link to="board"><h1>게시판</h1></router-link>
      </b-carousel-slide>
    </b-carousel>
  </div>
</template>

<script>
export default {
  name: "TheCarousel",
  components: {},
  data() {
    return {
      slide: 0,
      sliding: null,
    };
  },
  methods: {},
};
</script>

<style scoped>
a {
  text-decoration: none;
  color: white;
}
a:hover {
  text-decoration: none;
  color: azure;
}
</style>
