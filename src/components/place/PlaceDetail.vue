<template>
  <b-container>
    <b-card border-variant="primary" class="mb-2" no-body>
      <b-card-img
        :src="img"
        style="max-height: 400px; object-fit: cover"
      ></b-card-img>
      <template #header>
        <b-row class="text-center">
          <b-col cols="10"
            ><h3>{{ title }}</h3></b-col
          >
          <b-col cols="2"
            ><b-button variant="primary" @click="back" class="align-self-end"
              >목록</b-button
            ></b-col
          >
        </b-row>
      </template>
      <b-card-body class="text-left">
        <div v-html="message"></div>
      </b-card-body>
    </b-card>
  </b-container>
</template>

<script>
export default {
  name: "PlaceDetail",
  data() {
    return {};
  },
  props: {
    place: Object,
  },
  computed: {
    title() {
      if (!this.place) return "";
      return this.place.title ? this.place.title : "오류";
    },
    message() {
      if (!this.place) return "";
      return this.place.overview
        ? "<div>" + this.place.overview.split(".").join(".<br>") + "</div>"
        : "";
    },
    img() {
      if (!this.place) return "";
      return this.place.firstImage
        ? this.place.firstImage
        : require("@/assets/img/on_error.png");
    },
  },
  methods: {
    back() {
      this.$emit("backEmit");
    },
  },
};
</script>
<style>
.table th,
.table td {
  vertical-align: middle;
}
</style>
