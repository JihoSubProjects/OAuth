package com.jihogrammer.oauth.auth.controller;

import com.jihogrammer.oauth.auth.model.TokensFromKakao;
import com.jihogrammer.oauth.auth.service.kakao.KakaoAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class OAuthController {

    @Autowired
    KakaoAPI kakaoAPI;

    /**
     * Kakao API Server로부터 access_token을 발급받아
     * Kakao Profile을 반환합니다.
     * @return Kakao Profile
     */
    @GetMapping("/kakao")
    public ResponseEntity<Map> kakaoLogin(@RequestParam("code") String code) {
        TokensFromKakao tokens = kakaoAPI.getTokens(code);
        // 논의가 필요한 부분
        // access_token 관리는 어떻게?
        // refresh_token 관리는 어떻게?
        // token 유효기간 관리는 어떻게?
        Map response = kakaoAPI.getProfile(tokens);
        return ResponseEntity.ok(response);
    }

}
