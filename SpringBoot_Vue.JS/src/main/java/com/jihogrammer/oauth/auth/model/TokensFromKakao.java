package com.jihogrammer.oauth.auth.model;

import lombok.Getter;

@Getter
public class TokensFromKakao {

    String token_type;
    String access_token;
    String refresh_token;
    String scope;
    long expires_in;
    long refresh_token_expires_in;

}
