package com.jihogrammer.oauth.auth.service.kakao;

import com.google.gson.Gson;
import com.jihogrammer.oauth.auth.model.TokensFromKakao;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * Kakao Server와 REST 통신을 하는 Service Class
 */
@Service
public class KakaoAPI {

    private static final String APP_KEY = "fef1da7151005da8eece089628fe2577";
    private static final String REDIRECT_URI = "http://localhost:8080/oauth";

    private static final String LOGIN_HOST = "https://kauth.kakao.com";
    private static final String LOGIN_URI = "/oauth/token";
    private static final String LOGIN_URL = LOGIN_HOST + LOGIN_URI;

    private static final String PROFILE_HOST = "https://kapi.kakao.com";
    private static final String PROFILE_URI = "/v2/user/me";
    private static final String PROFILE_URL = PROFILE_HOST + PROFILE_URI;

    public TokensFromKakao getTokens(String code) {

        TokensFromKakao tokens = new TokensFromKakao();
        StringBuilder sb;

        try {
            URL url = new URL(LOGIN_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setDoOutput(true);

            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            sb = new StringBuilder();
            sb.append("grant_type=authorization_code")
                .append("&client_id=").append(APP_KEY)
                .append("&redirect_uri=").append(REDIRECT_URI)
                .append("&code=").append(code);
            bw.write(sb.toString());
            bw.flush();

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
            sb = new StringBuilder();
            String data;
            while ((data = br.readLine()) != null) sb.append(data);

            tokens = new Gson().fromJson(sb.toString(), TokensFromKakao.class);

            // HTTP 통신이 다 끝나고 close 해주어야 한다.
            // 중간에 닫아버리면 connection 또한 닫아지는 듯한 현상이 발생한다.
            // 이 이유 때문에 try with resources 방식을 사용할 수 없다.
            bw.close();
            br.close();
            conn.disconnect();

        } catch (Exception e) { e.printStackTrace(); }

        return tokens;

    }

    public Map getProfile(TokensFromKakao tokens) {

        Map profile = new HashMap();
        String accessToken = tokens.getAccess_token();
        StringBuilder sb;

        try {
            URL url = new URL(PROFILE_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
            conn.setRequestProperty("Authorization", "Bearer " + accessToken);

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
            sb = new StringBuilder();
            String data;
            while ((data = br.readLine()) != null) sb.append(data);

            System.out.println(sb);
            profile = (Map) new Gson().fromJson(sb.toString(), Map.class);

            br.close();
            conn.disconnect();

        } catch (Exception e) { e.printStackTrace(); }

        return profile;

    }

}
