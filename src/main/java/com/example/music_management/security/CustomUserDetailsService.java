package com.example.music_management.security;

import com.example.music_management.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.music_management.entity.User;

// このクラスはUserDetailsServiceを実装して、ユーザー情報をデータベースから取得するサービスとして動作します
@Service
public class CustomUserDetailsService implements UserDetailsService {

    // ユーザー情報を取得するためのリポジトリ
    private final UserRepository userRepository;

    // コンストラクタでUserRepositoryを受け取って初期化します
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // ユーザー名からユーザー情報をロードするメソッド
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // ユーザー名でデータベースからユーザーを探します
        User user = userRepository.selectUserByUsername(username);
        // ユーザーが見つからない場合は、例外を投げます
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        // 見つかったユーザー情報をCustomUserDetailsに変換して返します
        return new CustomUserDetails(user);
    }
}
