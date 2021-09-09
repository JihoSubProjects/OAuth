<template>
  <div class="about">
    <h1 v-if="memberProfile">{{ memberProfile["nickname"] }}</h1>
    <h2 v-else>로그인이 필요한 서비스입니다.</h2>
  </div>
</template>

<script>
import { mapState } from "vuex";

export default {
  computed: {
    ...mapState("auth", ["memberProfile"]),
  },
};
</script>
