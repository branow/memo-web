package com.branow.memoweb.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;


@EnableWebSecurity
@Configuration
public class WebSecurityConfig {

    @Value("${cors.origins}")
    private String corsOrigins;

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }


    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http, JwtAuthenticationConverter converter) throws Exception {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of(corsOrigins));
        configuration.setAllowedMethods(List.of("*"));
        configuration.setAllowedHeaders(List.of("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        http.cors((CorsConfigurer<HttpSecurity> c) -> c.configurationSource(source));

        http.csrf(AbstractHttpConfigurer::disable);

        http.authorizeHttpRequests((auth) -> {
            //AuthenticationController
            auth.requestMatchers(HttpMethod.POST, "/register").permitAll();
            auth.requestMatchers(HttpMethod.POST, "/login").permitAll();
            auth.requestMatchers(HttpMethod.POST, "/enable").permitAll();
            auth.requestMatchers(HttpMethod.POST, "/regenerate-token").permitAll();
            auth.requestMatchers(HttpMethod.POST, "/reset").permitAll();
            //EmailController
            auth.requestMatchers(HttpMethod.POST, "/email").permitAll();
            //UserController
            auth.requestMatchers(HttpMethod.GET, "/user/general-details/*").permitAll();
            auth.requestMatchers(HttpMethod.GET, "/user/private-short-details").authenticated();
            auth.requestMatchers(HttpMethod.GET, "/user/details").authenticated();
            auth.requestMatchers(HttpMethod.POST, "/user").authenticated();
            auth.requestMatchers(HttpMethod.POST, "/user/change-password").authenticated();
            auth.requestMatchers(HttpMethod.DELETE, "/user").authenticated();
            //ModuleController
            auth.requestMatchers(HttpMethod.GET, "/module/general-details/*").permitAll();
            auth.requestMatchers(HttpMethod.GET, "/module/details/*").permitAll();
            auth.requestMatchers(HttpMethod.GET, "/module/collection").authenticated();
            auth.requestMatchers(HttpMethod.GET, "/module/short-details").authenticated();
            auth.requestMatchers(HttpMethod.POST, "/module").authenticated();
            auth.requestMatchers(HttpMethod.DELETE, "/module/*").authenticated();
            //CollectionController
            auth.requestMatchers(HttpMethod.GET, "/collection/details/*").permitAll();
            auth.requestMatchers(HttpMethod.POST, "/collection/*").authenticated();
            auth.requestMatchers(HttpMethod.DELETE, "/collection/*").authenticated();
            //FlashcardController
            auth.requestMatchers(HttpMethod.GET, "/flashcard/details/*").permitAll();
            auth.requestMatchers(HttpMethod.GET, "/flashcard/learn-context/*/*").authenticated();
            auth.requestMatchers(HttpMethod.POST, "/flashcard/*").authenticated();
            auth.requestMatchers(HttpMethod.DELETE, "/flashcard/*").authenticated();
            //MediaController
            auth.requestMatchers(HttpMethod.GET, "/media/image/*").permitAll();
            auth.requestMatchers(HttpMethod.GET, "/media/audio/*").permitAll();
            //WebSearchController
            auth.requestMatchers(HttpMethod.GET, "/web-search/images/*").authenticated();
            auth.requestMatchers(HttpMethod.GET, "/web-search/audios/*").authenticated();
            auth.requestMatchers(HttpMethod.GET, "/web-search/english-word-senses/*").authenticated();
            auth.requestMatchers(HttpMethod.GET, "/web-search/english-word/*").authenticated();
            //LearningController
            auth.requestMatchers(HttpMethod.GET, "/learn/*").authenticated();
            auth.requestMatchers(HttpMethod.POST, "/learn/*/*/*").authenticated();
            //SearchController
            auth.requestMatchers(HttpMethod.GET, "/search/user/*/*").permitAll();
            auth.requestMatchers(HttpMethod.GET, "/search/module/*/*").permitAll();
            //ImportController
            auth.requestMatchers(HttpMethod.POST, "/import/flashcard/*/*").authenticated();
            auth.requestMatchers(HttpMethod.POST, "/import/collection/*/*").authenticated();
            auth.requestMatchers(HttpMethod.POST, "/import/module/*").authenticated();

        });

        http.oauth2ResourceServer((oauth) -> oauth.jwt((jwt) -> jwt.jwtAuthenticationConverter(converter)));
        http.sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(11);
    }

}
