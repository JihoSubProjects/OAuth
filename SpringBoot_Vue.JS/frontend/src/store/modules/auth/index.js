import { kakaoLogin } from "../../../api";

const REDIRECT_URI = process.env.VUE_APP_REDIRECT_URI;

const KAKAO = {
  APP_KEY: process.env.VUE_APP_KAKAO_APP_KEY,
  HOST: "https://kauth.kakao.com",
  LOGIN_URI: "oauth/authorize",
};

export default {
  namespaced: true,
  state: {
    memberProfile: null,
    KAKAO_LOGIN_URL: `${KAKAO.HOST}/${KAKAO.LOGIN_URI}?client_id=${KAKAO.APP_KEY}&redirect_uri=${REDIRECT_URI}&response_type=code`,
  },
  mutations: {
    SET_MEMBER_PROFILE(state, profile) {
      state.memberProfile = profile;
    },
  },
  actions: {
    async KAKAO_LOGIN({ commit }, code) {
      return await kakaoLogin(code)
        .then(({ data }) => {
          commit("SET_MEMBER_PROFILE", data.properties);
        })
        .catch((e) => console.error(e));
    },
  },
};
