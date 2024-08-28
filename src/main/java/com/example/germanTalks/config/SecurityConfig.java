// src/main/java/com/example/germanTalks/config/SecurityConfig.java
package com.example.germanTalks.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public ClientRegistrationRepository clientRegistrationRepository() {
        return new InMemoryClientRegistrationRepository(
                ClientRegistration.withRegistrationId("german-coffee-talks")
                        .clientId("0b8926a0-733f-4b65-bc37-a1232cccea03")
                        .clientSecret("zQn8Q~pgEJsa_lV-Lk3OjoKhGuA0s37ng9KMGcDX")
                        .redirectUri("http://localhost:8080/login/oauth2/code/")
                        .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                        .scope("openid", "profile", "email")
                        .authorizationUri("https://login.microsoftonline.com/0b3fc178-b730-4e8b-9843-e81259237b77/oauth2/v2.0/authorize")
                        .tokenUri("https://login.microsoftonline.com/0b3fc178-b730-4e8b-9843-e81259237b77/oauth2/v2.0/token")
                        .jwkSetUri("https://login.microsoftonline.com/common/discovery/v2.0/keys")
                        .userInfoUri("https://graph.microsoft.com/oidc/userinfo")
                        .userNameAttributeName("sub")
                        .clientName("german-coffee-talks")
                        .build()
        );
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/", "/login", "/language/getAll","/topic/getVideoUrlByTopic/**", "/question/getByTopic/**", "/login**", "/oauth2/**", "/topic/getAll", "user/getAll", "/api/**").permitAll()
                                .anyRequest().authenticated()
                )
                .oauth2Login(oauth2Login ->
                        oauth2Login
                                .defaultSuccessUrl("http://localhost:3000/home", true)
                );
        return http.build();
    }

}