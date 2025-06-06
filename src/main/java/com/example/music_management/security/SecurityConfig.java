package com.example.music_management.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

// このクラスはSpring Securityの設定をカスタマイズするためのクラス
@Configuration
public class SecurityConfig {
    // SecurityFilterChainは、HTTPリクエストに適用するセキュリティフィルターのチェーンを設定
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // HTTPリクエストの認可設定
            .authorizeHttpRequests(request -> request
                // "/register", "/login", "/error"へのアクセスを許可します        
                .requestMatchers("/register", "/login", "/error").permitAll()
                    // 他の全てのリクエストは認証を要求します    
                    // .anyRequest().permitAll())
                    .anyRequest().authenticated())
                // フォームログインの設定を行います
                .formLogin(login -> login
                    // ログイン処理のためのURLを指定します
                    .loginProcessingUrl("/login")
                    // ログインページのURLを指定
                    .loginPage("/login")
                    // ログイン成功時のリダイレクト先URLを指定
                    .defaultSuccessUrl("/albums")
                    // ログイン失敗時のリダイレクト先URLを指定します
                    .failureUrl("/login?error")
                    // ログインページへのアクセスを許可
                    .permitAll()
            );
        // 設定を反映したSecurityFilterChainオブジェクトを返します
        return http.build();
    }

    // PasswordEncoderのBeanを定義します
    @Bean
    public PasswordEncoder passwordEncoder() {
        // BCryptPasswordEncoderを使ってパスワードのハッシュ化と検証を行います
        return new BCryptPasswordEncoder();
    }
}