package com.tomtom.scoop.global.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tomtom.scoop.domain.user.model.dao.RefreshToken;
import com.tomtom.scoop.domain.user.model.entity.User;
import com.tomtom.scoop.domain.user.repository.RefreshTokenRepository;
import com.tomtom.scoop.domain.user.service.UserService;
import com.tomtom.scoop.global.util.JwtTokenUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.time.Duration;

@Component
@Slf4j
@RequiredArgsConstructor
public class OAuthSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final UserService userService;
    private final JwtTokenUtil jwtTokenUtil;
    private final RefreshTokenRepository refreshTokenRepository;
    private final long refreshTokenValidTime = Duration.ofDays(14).toMillis();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        String oauthId = String.valueOf(oAuth2User.getAttributes().get("id"));

        String accessToken = jwtTokenUtil.generateAccessToken(oauthId);
        String refreshToken = jwtTokenUtil.generateRefreshToken(oauthId);


        refreshTokenRepository.save(
                RefreshToken.builder()
                        .key(oauthId)
                        .value(refreshToken)
                        .expiredTime(refreshTokenValidTime)
                        .build());

        User findUser = userService.findByOauthId(oauthId);
        LoginResult loginResult = new LoginResult(accessToken, refreshToken, false);
        //새로운 회원일 경우
        if (findUser.getName() == null) {
            loginResult.isFirst = true;
        }
        response.setContentType("application/json");
        response.setStatus(HttpStatus.CREATED.value());
        ObjectMapper mapper = new ObjectMapper();
        response.getWriter().write(mapper.writeValueAsString(loginResult));
    }

    private String makeRedirectUrl(String url, String accessToken, String refreshToken) {

        return UriComponentsBuilder.fromUriString(url)
                .queryParam("accessToken", accessToken)
                .queryParam("refreshToken", refreshToken)
                .build().toUriString();
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    private class LoginResult {
        private String accessToken;
        private String refreshToken;
        private boolean isFirst;
    }

}
