<template>
  <b-container>
    <TheSearchbar class="m-2" />
    <b-row>
      <b-col cols="4"><TheMap /></b-col>
      <b-col cols="8"><PlaceList /></b-col>
    </b-row>
  </b-container>
</template>

<script>
import TheSearchbar from "@/components/TheSearchbar.vue";
import TheMap from "@/components/TheMap.vue";
import PlaceList from "@/components/place/PlaceList.vue";
export default {
  name: "AppThemeTrip",
  components: {
    TheSearchbar,
    TheMap,
    PlaceList,
  },
  data() {
    return {
      message: "",
    };
  },
};
</script>

<style>
.table th,
.table td {
  vertical-align: middle;
}
</style>
