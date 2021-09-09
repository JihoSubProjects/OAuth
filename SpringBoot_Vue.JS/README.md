# OAuth

## SpringBoot & Vue.js

### Environment

-   SpringBoot
    -   Java 11
    -   Gradle
    -   Dependencies
        -   `spring-boot-starter-web`
        -   `lombok`
        -   `com.google.code.gson`

-   Vue.js
    -   Vue 3
    -   Dependencies
        -   `axios`
        -   `vue-router`
        -   `vuex`

---

### Structure

-   SpringBoot

```text
com.jihogrammer.oauth.auth
    |
    +--- controller
    |        |
    |        +--- OAuthController (RestController)
    |
    +--- model
    |      |
    |      +--- TokensFromKakao (DTO)
    |
    +--- service
           |
           +--- kakao
                  |
                  +--- KakaoAPI (Service)
```

-   Vue.js

```text
|
+--- api (axios)
|
+--- router (vue-router)
|
+--- store (vuex)
|        |
|        +--- auth (vuex module)
|
+--- views
       |
       +--- Home (Login)
       |
       +--- About (MyPage)
       |
       +--- OAuth (Kakao OAuth Login)
```

---

### Flow

#### Diagram

>   reference : [PAYCO Developers](https://developers.payco.com/guide)

![OAuth Diagram](https://developers.payco.com/static/img/@img_guide2.jpg)

1.  Client에서 Login 버튼 클릭
    -   Kakao Login 페이지로 이동
    -   로그인 성공 시 *지정한 URL로 Redirect
        >   [Kakao Developers](https://developers.kakao.com)에서 지정한다.
2.  Redirect된 *페이지 URL에 Query Parameter `code`로 인증 코드 발급
    -   URL에서 `code`를 매핑하여 SpringBoot에 요청
    >   해당 페이지(`OAuth.vue`)를 구현하여 SpringBoot 통신한다.
3.  SpringBoot OAuthController가 code를 받아 `access_token` 요청
    -   `java.net.HttpUrlConnection`을 통해 Kakao 인증 서버와 통신
    -   `code`를 담아 `access_token` 발급을 요청
4.  발급된 `access_token`을 통해 사용자 정보(닉네임 등)을 요청
    -   Kakao Developers에서 지정한 Properties를 매핑
    -   Client에 Properties를 전달
5.  Vue는 SpringBoot로부터 받은 Properties를 통해 로그인 처리
