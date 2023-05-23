<template>
  <b-container
    ><TheSearchbar class="m-2" />
    <b-row>
      <b-col cols="6" class="text-center"><PlaceList /></b-col>
      <b-col cols="6" class="p-3"><TheMap /></b-col>
    </b-row>
  </b-container>
</template>

<script>
import TheSearchbar from "@/components/TheSearchbar.vue";
import PlaceList from "@/components/place/PlaceList.vue";
import TheMap from "@/components/TheMap.vue";

export default {
  name: "AppMyPlan",
  components: { TheMap, PlaceList, TheSearchbar },
  data() {
    return {
      message: "",
    };
  },
};
</script>

<style scoped></style>
