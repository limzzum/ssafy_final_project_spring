<template>
  <b-form>
    <b-form-row class="align-items-center">
      <b-col cols="3">
        <b-form-select
          v-model="condition.sidoCode"
          :options="regions"
        ></b-form-select>
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
        <b-button class="m-1" type="button" variant="primary" @click="search"
          >검색</b-button
        >
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
  data() {
    return {
      condition: {
        contentTypeId: 0,
        sidoCode: "",
        title: "",
      },
    };
  },
  async created() {
    console.log("created");
    await this.getRegionInfo();
    console.log(this.contents);
    await this.getContentInfo();
    console.log(this.regions);
  },
  computed: {
    ...mapState(placeStore, ["regions", "contents"]),
  },
  methods: {
    ...mapActions(placeStore, [
      "getRegionInfo",
      "getContentInfo",
      "searchPlace",
    ]),
    async search() {
      // let param = {
      //   page: 1,
      // };

      this.searchPlace(
        this.condition,
        ({ data }) => {
          console.log("data111 " + data.result);
        },
        (error) => {
          console.log(this.condition);
          console.log(error);
        }
      );
      // TODO : search 후 /themetrip 이동 구현
    },
  },
};
</script>

<style scoped></style>
