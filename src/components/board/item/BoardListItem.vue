<template>
  <b-tr>
    <b-td>{{ postId }}</b-td>
    <b-td><router-link :to="'/board/view/'+postId"> aa</router-link></b-td>
    <!-- <b-th class="text-left">
      
      <router-link :to="{ name: 'boardview', params: { postId: postId } }">{{ title }}</router-link>
    </b-th> -->
    <b-td>{{ hits }}</b-td>
    <b-td>{{ userName }}</b-td>
    <b-td>{{ createTime | dateFormat }}</b-td>
  </b-tr>
</template>

<script>
import moment from "moment";

export default {
  name: "BoardListItem",
  props: {
    postId: Number,
    userName: String,
    boardTitle: String,
    hits: Number,
    createTime: String,
  },
  filters: {
    dateFormat(regtime) {
      return moment(new Date(regtime)).format("YY.MM.DD");
    },
  },
};
</script>

<style></style>
