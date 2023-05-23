<template>
  <b-form @submit.prevent="search">
    <b-form-row class="align-items-center">
      <b-col cols="3">
        <b-form-select v-model="condition.sidoCode" :options="regions">
        </b-form-select>
      </b-col>
      <b-col cols="3">
        <b-form-select
          v-model="condition.contentTypeId"
          :options="contents"
        ></b-form-select> </b-col
      ><b-col cols="5">
        <b-form-input
          v-model="condition.title"
          type="text"
          placeholder="관광지 이름"
        ></b-form-input>
      </b-col>
      <b-col cols="1" class="text-center">
        <b-button class="m-1" type="submit" variant="primary">검색</b-button>
      </b-col>
    </b-form-row>
  </b-form>
</template>

<script>
import { mapActions, mapState } from "vuex";
const placeStore = "placeStore";

export default {
  name: "TheSearchbar",
  components: {},
  props: {
    route: String,
  },
  data() {
    return {
      condition: {
        contentTypeId: null,
        sidoCode: null,
        title: null,
        latitude: null,
        longitude: null,
      },
    };
  },
  async created() {
    await this.getRegionInfo();
    await this.getContentInfo();
  },
  computed: {
    ...mapState(placeStore, ["regions", "contents"]),
  },
  methods: {
    ...mapActions(placeStore, [
      "getRegionInfo",
      "getContentInfo",
      "searchPlace",
      "setCondition",
    ]),
    async search() {
      console.log("search");
      this.setCondition(this.condition);
      this.searchPlace();
      if (this.route && this.route.length)
        this.$router.push({ name: this.route }).catch(() => {});
    },
  },
};
</script>

<style scoped></style>
