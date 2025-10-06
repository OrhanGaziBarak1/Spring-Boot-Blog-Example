package com.blog.blog_project.configs;

import com.blog.blog_project.services.CustomUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.blog.blog_project.services.JwtService;

import java.io.IOException;

// 1. Burası bir middleware
// 2. Gelen her istek buraya düşüyor (OncePerRequestFilter sayesinde)
// 3. İstekleri burada kontrol ediyoruz

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final CustomUserDetailsService customUserDetailsService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request, // Gelen istek
            @NonNull HttpServletResponse response, // Gönderilecek cevap
            @NonNull FilterChain filterChain // Diğer filtrelere devam etmek için
    ) throws ServletException, IOException {
        // 1. ADIM: Authorization header'ını al
        // Örnek: "Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String username;

        // Token yoksa veya "Bearer " ile başlamıyorsa
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            // Diğer filtrelere devam et ve metodu bitir
            filterChain.doFilter(request, response);
            return;
        }

        // 2. ADIM: Token'ı çıkar
        // "Bearer " kısmını at, sadece token'ı al
        // "Bearer eyJhbGci..." -> "eyJhbGci..."
        jwt = authHeader.substring(7);

        // 3. ADIM: Token'dan kullanıcı adını (email) çıkar
        username = jwtService.extractUsername(jwt);

        // 4. ADIM: Kullanıcı email'i var mı ve henüz doğrulanmamış mı kontrol et
        // SecurityContextHolder.getContext().getAuthentication() == null
        // -> Kullanıcı henüz sisteme giriş yapmamış demek
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // Veritabanından kullanıcı bilgilerini al
            UserDetails userDetails = this.customUserDetailsService.loadUserByUsername(username);

            // 5. ADIM: Token geçerli mi kontrol et
            if (jwtService.isTokenValid(jwt, userDetails)) {
                // Token geçerli! Kullanıcıyı sisteme giriş yapmış olarak işaretle
                // Authentication objesi oluştur

                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
                // Request detaylarını ekle
                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                // Kullanıcıyı Spring Security Context'ine ekle
                // Artık Spring Security bu kullanıcının giriş yaptığını biliyor
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        // Diğer filtrelere devam et
        filterChain.doFilter(request, response);
    }
}