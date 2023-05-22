<template>
  <div>
    <b-row class="m-3">
      <b-col
        class="border border-primary rounded"
        style="background: rgba(131, 182, 255, 0.31)"
      >
        <h2
          style="text-align: center"
          id="worldtitle"
          v-text="
            `국내 관광지 월드컵 ` +
            (rounds != 1 ? `${rounds << 1}강 (${round}/${rounds})` : `결승!!`)
          "
        ></h2>
      </b-col>
    </b-row>

    <b-row class="m-3">
      <b-col
        cols="5"
        class="border border-primary rounded p-2"
        @click="progress(0)"
      >
        <b-img :src="require(`@/assets/img/${worldcups[0].img}`)" fluid />
        <h3 v-text="worldcups[0].content" class="text-center"></h3>
      </b-col>
      <b-col cols="2" class="align-self-center">
        <h1 class="text-center text-danger">VS</h1></b-col
      >
      <b-col
        cols="5"
        class="border border-primary rounded p-2"
        @click="progress(1)"
      >
        <b-img :src="require(`@/assets/img/${worldcups[1].img}`)" fluid />
        <h3 v-text="worldcups[1].content" class="text-center"></h3>
      </b-col>
    </b-row>
  </div>
</template>

<script>
import { mapActions, mapState } from "vuex";
const worldcupStore = "worldcupStore";

export default {
  name: "WorldcupProgress",
  components: {},
  data() {
    return {};
  },
  async created() {
    await this.getWorldcupInfo();
    // console.log(this.worldcups);
  },
  computed: {
    ...mapState(worldcupStore, ["worldcups", "selected", "rounds", "round"]),
  },
  methods: {
    ...mapActions(worldcupStore, [
      "getWorldcupInfo",
      "updateWorldcupResult",
      "chooseWorldcup",
    ]),
    async progress(num) {
      if (this.rounds == 1 && this.round == 1) {
        this.updateWorldcupResult(this.worldcups[num]);
        this.$router.push({ name: "result" });
      } else this.chooseWorldcup(num);
    },
  },
};
</script>

<style scoped>
.border {
  background: rgba(221, 248, 218, 0.29);
}
.border:hover {
  background: rgba(73, 225, 54, 0.31);
}
</style>
