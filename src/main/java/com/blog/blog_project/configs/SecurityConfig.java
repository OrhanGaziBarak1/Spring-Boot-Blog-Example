package com.blog.blog_project.configs;

import com.blog.blog_project.services.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;

/**
 * SecurityConfig - Spring Security yapılandırması
 * 
 * AÇIKLAMA: Bu class uygulamanın güvenlik ayarlarını yapıyor.
 * Hangi sayfalar açık? Hangi sayfalar korumalı? 
 * Şifreler nasıl şifreleniyor? vs.
 */

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final CustomUserDetailsService CustomUserDetailsService;


    /**
     * Güvenlik filtreleme zinciri
     * 
     * AÇIKLAMA: Bu metod hangi endpoint'lerin açık, hangilerinin 
     * korumalı olduğunu belirliyor.
     */

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // CSRF korumasını kapat
            .csrf(csrf -> csrf.disable())

            .authorizeHttpRequests(auth -> auth
                // Bu endpoint'ler herkese açık (giriş yapmadan erişilebilir)
                // "/users/" altında yer alan her bir endpoint açık şu an
                .requestMatchers("/users/*").permitAll()
                // Diğer tüm endpoint'ler korumalı (giriş gerekli)
                .anyRequest().authenticated()
            )
            // Session kullanmıyoruz
            // Her istekte token gönderilecek, session'a gerek yok
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
            // Authentication provider'ı ekle
            .authenticationProvider(authenticationProvider())
            // JWT filter'ını ekle
            // Bu filter UsernamePasswordAuthenticationFilter'dan ÖNCE çalışacak
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    //Kullanıcı şifrelerini bu method ile şifreliyoruz
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //Kullanıcı adı ve şifre kontrolünü yapan method
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider(CustomUserDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    //Authentication işlemlerini yönetir. Login olurken bu kullanılacak
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}