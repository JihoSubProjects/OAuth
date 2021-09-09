import axios from "axios";

const HOST = "http://localhost";
const LOGIN_URI = "auth/kakao";

async function kakaoLogin(code) {
  return await axios.get(`${HOST}/${LOGIN_URI}?code=${code}`);
}

export { kakaoLogin };
