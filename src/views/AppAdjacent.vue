<template>
  <b-container
    ><PlaceSearchbar class="m-2" />
    <b-row>
      <b-col cols="7" class="text-center"
        ><PlaceList :viewDetail="false"
      /></b-col>
      <b-col cols="5" class="p-3"><TheMap :markSelected="true" /></b-col>
    </b-row>
  </b-container>
</template>

<script>
import PlaceSearchbar from "@/components/place/PlaceSearchbar.vue";
import PlaceList from "@/components/place/PlaceList.vue";
import TheMap from "@/components/TheMap.vue";

export default {
  name: "AppAdjacent",
  components: { TheMap, PlaceList, PlaceSearchbar },
  data() {
    return {
      message: "",
    };
  },
};
</script>

<style scoped></style>
