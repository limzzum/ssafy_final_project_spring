<template>
  <b-container>
    <PlaceSearchbar class="m-2" />
    <b-row>
      <b-col cols="7"><PlaceList :viewDetail="true" /></b-col>
      <b-col cols="5" class="p-3"><TheMap :markSelected="false" /></b-col>
    </b-row>
  </b-container>
</template>

<script>
import PlaceSearchbar from "@/components/place/PlaceSearchbar.vue";
import TheMap from "@/components/TheMap.vue";
import PlaceList from "@/components/place/PlaceList.vue";
export default {
  name: "AppThemeTrip",
  components: {
    PlaceSearchbar,
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
