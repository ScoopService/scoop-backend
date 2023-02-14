package com.tomtom.scoop.global.config;

import com.tomtom.scoop.global.security.CustomUserDetailsService;
import com.tomtom.scoop.global.security.JwtTokenFilter;
import com.tomtom.scoop.global.security.OAuthService;
import com.tomtom.scoop.global.security.OAuthSuccessHandler;
import com.tomtom.scoop.global.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig{

    private final OAuthService oAuthService;
    private final OAuthSuccessHandler oAuthSuccessHandler;
    private final JwtTokenUtil jwtTokenUtil;
    private final CustomUserDetailsService customUserDetailsService;


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        http
                .cors()
                .and()
                .httpBasic().disable()
                .formLogin().disable()
                .csrf().disable().authorizeHttpRequests()
                .requestMatchers("/v3/api-docs/**", "/swagger*/**", "/swagger-ui/","api-docs.html").permitAll()
                .anyRequest().authenticated()
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .and()
                .addFilterBefore(new JwtTokenFilter(customUserDetailsService, jwtTokenUtil), UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling()
//                .authenticationEntryPoint(new CustomAuthenticationEntryPoint())
                .and()
                .oauth2Login()
                .defaultSuccessUrl("/")
                .userInfoEndpoint()
                .userService(oAuthService)
                .and()
                .successHandler(oAuthSuccessHandler);
        return http.build();

    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("HEAD", "GET", "POST", "PUT", "DELETE"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true);
        configuration.setMaxAge(3600L);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }




}
