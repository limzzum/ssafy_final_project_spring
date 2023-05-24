<template>
  <b-container>
    <b-card border-variant="black" class="mb-2" no-body>
      <b-card-header>
        <b-row class="text-center">
          <b-col cols="3">{{ article.boardTitle }}</b-col>
          <b-col> | </b-col>
          <b-col cols="8">{{ article.title }}</b-col>
        </b-row>
        <b-row class="text-center">
          <b-col cols="4">
            {{ article.userName }}
            | {{ regTime }}
          </b-col>
          <b-col cols="8">
            <BIconEye variant="secondary"></BIconEye> {{ article.hits }}
            <BIconChatDots variant="secondary"></BIconChatDots> 0
          </b-col>
        </b-row>
      </b-card-header>
      <b-card-body class="text-left">
        <div v-html="message"></div>
      </b-card-body>
    </b-card>
  </b-container>
</template>

<script>
import { mapState, mapActions } from "vuex";
import moment from "moment";
const boardStore = "boardStore";
export default {
  name: "BoardView",
  components: {},
  data() {
    return {};
  },
  computed: {
    ...mapState(boardStore, ["article"]),
    regTime() {
      return moment(this.article.regTime).format("YY.MM.DD hh:mm");
    },
  },
  methods: {
    ...mapActions(boardStore, []),
  },
};
</script>

<style scoped></style>
