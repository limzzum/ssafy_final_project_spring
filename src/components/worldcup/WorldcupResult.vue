<template>
  <b-container>
    <b-col class="justify-content-center text-center">
      <h2
        class="align-middle col-12 border border-primary p-3"
        style="text-align: center; background: rgba(192, 218, 255, 0.31)"
        v-text="`🛫나의 마음 속 1등 관광지는 바로 ${win.title}입니다!!!🛫`"
      ></h2>
    </b-col>
    <b-row class="justify-content-center">
      <b-col class="p-3 border border-primary">
        <h2 style="text-align: center">다른 사람들의 선택은...</h2>
        <b-table
          hover
          :items="items"
          :fields="fields"
          :busy.sync="isBusy"
          :tbody-tr-class="rowClass"
          class="text-center align-middle"
        >
          <template #cell(img)="data">
            <b-img
              :src="require(`@/assets/img/${data.value}`)"
              fluid
              thumbnail
              style="max-height: 100px"
            />
          </template>
          <template #cell(wins)="data">
            <b-progress striped animated show-value height="30px">
              <b-progress-bar
                :value="`${percentage(data.value) * 0.8 + 20}`"
                :variant="`${data.item.id == win.id ? 'success' : 'warning'}`"
                >{{ percentage(data.value).toFixed(1) }}%</b-progress-bar
              >
            </b-progress>
          </template>
        </b-table>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import { mapActions, mapState } from "vuex";
const worldcupStore = "worldcupStore";
export default {
  name: "WorldcupResult",
  components: {},
  data() {
    return {
      isBusy: true,
      fields: [
        {
          key: "img",
          label: "이미지",
          tdClass: "col-1",
        },
        {
          key: "title",
          label: "관광지명",
          tdClass: "col-1",
        },
        {
          key: "content",
          label: "설명",
          tdClass: "col-3",
        },
        {
          key: "wins",
          label: "1등 비율",
          tdClass: "col-4",
        },
      ],
    };
  },
  async created() {
    await this.sortWorldcupInfo();
    this.isBusy = false;
  },
  computed: {
    ...mapState(worldcupStore, ["worldcups", "win", "totalwins"]),
    items() {
      return this.worldcups;
    },
  },
  methods: {
    ...mapActions(worldcupStore, ["sortWorldcupInfo"]),
    percentage(num) {
      return (parseInt(num) / parseInt(this.totalwins)) * 100;
    },
    rowClass(item, type) {
      if (!item || type !== "row") return;
      if (item.id === this.win.id) return "table-success";
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
