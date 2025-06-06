package com.example.music_management.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.music_management.entity.User;

// このクラスはSpring SecurityのUserDetailsを実装して、カスタムのユーザー情報を提供
public class CustomUserDetails implements UserDetails {
    // ユーザー情報を保持するためのフィールド
    private final User user;

    // コンストラクタでUserオブジェクトを受け取って初期化します
    public CustomUserDetails(User user) {
        this.user = user;
    }

    // ユーザーの権限情報を返します（ここでは空のリストを返しています）
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }
    
    // ユーザーのパスワードを返します
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    // ユーザー名を返します
    @Override
    public String getUsername() {
        return user.getUsername();
    }

    // ユーザーIDを取得するためのメソッドを追加しています
    public long getUserId() {
        return user.getUserId();
    }
}
