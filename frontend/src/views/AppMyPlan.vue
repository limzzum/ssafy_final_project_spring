<template>
  <b-container
    ><PlaceSearchbar class="m-2" />
    <b-row>
      <b-col cols="8" class="text-center">
        <TheMap />
        <PlaceList :viewDetail="false" />
      </b-col>
      <b-col cols="4" class="p-3">
        <PlaceSelect />
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import PlaceSearchbar from "@/components/place/PlaceSearchbar.vue";
import PlaceList from "@/components/place/PlaceList.vue";
import TheMap from "@/components/TheMap.vue";
import PlaceSelect from "@/components/place/PlaceSelect.vue";

export default {
  name: "AppMyPlan",
  components: { TheMap, PlaceList, PlaceSearchbar, PlaceSelect },
  data() {
    return {
      message: "",
    };
  },
};
</script>

<style scoped></style>
