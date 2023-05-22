<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert show><h3>상세 보기</h3></b-alert>
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col class="text-left">
        <b-button variant="outline-primary" @click="moveList">목록</b-button>
      </b-col>
    </b-row>
    <b-row align-h="center">
      <img :src="place.firstImage" width="80%" height="350" alt="이미지없음" />
    </b-row>
    <br />
    <b-row class="mb-1">
      <b-col>
        <b-card
          :header-html="`<h3>${place.contentId}.
          ${place.title} [${place.sidoName}]</h3><div><h6>${place.addr1}</div><div>${place.overview}</h6></div>`"
          class="mb-2"
          border-variant="dark"
          no-body
        >
          <b-card-body class="text-left">
            <div v-html="message"></div>
          </b-card-body>
        </b-card>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
// import moment from "moment";
import { placeDetail } from "@/api/place";

export default {
  name: "PlaceDetail",
  data() {
    return {
      place: {},
    };
  },
  computed: {
    message() {
      if (this.place.content)
        return this.place.content.split("\n").join("<br>");
      return "";
    },
  },
  created() {
    let param = this.$route.params.contentId;
    placeDetail(
      param,
      ({ data }) => {
        this.place = data.result;
      },
      (error) => {
        console.log(error);
      }
    );
  },
  methods: {
    moveList() {
      this.$router.push({ name: "placeList" });
    },
  },
};
</script>

<style></style>
