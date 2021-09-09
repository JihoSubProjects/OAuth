<template>
  <div class="home">
    <img alt="Vue logo" src="../assets/logo.png" />
    <h2 v-if="memberProfile">안녕하세요, {{ memberProfile.nickname }}님!</h2>
    <div v-else class="login-button-group">
      <img
        v-for="button in buttons"
        :key="button.name"
        :alt="button.name"
        :src="button.src"
        @click="button.callback"
      />
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";

export default {
  name: "Home",
  data() {
    return {
      buttons: [
        {
          name: "Kakao",
          src: require("../assets/kakao_login_large_narrow.png"),
          callback: () => {
            console.log(this.KAKAO_LOGIN_URL);
            location.href = this.KAKAO_LOGIN_URL;
          },
        },
        {
          name: "Kakao",
          src: require("../assets/kakao_login_large_narrow.png"),
          callback: () => {
            console.log(this.KAKAO_LOGIN_URL);
            location.href = this.KAKAO_LOGIN_URL;
          },
        },
        {
          name: "Kakao",
          src: require("../assets/kakao_login_large_narrow.png"),
          callback: () => {
            console.log(this.KAKAO_LOGIN_URL);
            location.href = this.KAKAO_LOGIN_URL;
          },
        },
      ],
    };
  },
  computed: {
    ...mapState("auth", ["KAKAO_LOGIN_URL", "memberProfile"]),
  },
};
</script>

<style scoped>
.login-button-group > img {
  max-width: 200px;
  margin: 10px auto;
  display: block;
}
</style>
