<script>
import { mapActions } from "vuex";

export default {
  created() {
    let code = this.$route.query.code;
    this.KAKAO_LOGIN(code).then(() => this.$router.push("/"));
  },
  methods: {
    ...mapActions("auth", ["KAKAO_LOGIN"]),
  },
  render() {
    return "";
  },
};
</script>
