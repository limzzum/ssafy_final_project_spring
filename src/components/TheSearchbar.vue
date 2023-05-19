<template>
  <div id="themesearch" class="container text-center">
    <form class="d-flex my-3">
      <select id="search-area" class="form-select me-2" name="sidoCode">
        <option value="0" selected="">검색 할 지역 선택</option>
      </select>
      <select
        id="search-content-id"
        class="form-select me-2"
        name="contentTypeId"
      >
        <option value="0" selected>관광지 유형</option>
      </select>
      <input
        id="search-keyword"
        class="form-control me-2"
        type="search"
        placeholder="검색어"
        aria-label="검색어"
        name="title"
      />
      <input
        class="btn btn-outline-success"
        onclick="searchPlace();"
        value="검색"
      />
    </form>
  </div>
</template>

<script>
export default {
  name: "TheSearchbar",
  components: {},
  data() {
    return {
      message: "",
    };
  },
};
</script>

<style scoped></style>
