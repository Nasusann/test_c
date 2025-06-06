package com.example.music_management.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.music_management.entity.User;
import com.example.music_management.form.UserForm;
import com.example.music_management.repository.UserRepository;

// このクラスはユーザーの管理を行うサービスクラス
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // コンストラクタでリポジトリとパスワードエンコーダを注入しています
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    // 新しいユーザーを作成するメソッドです
    public void createUser(UserForm userForm) {
        // 新しいユーザーオブジェクトを作成
        User user = new User();
        user.setUsername(userForm.getUsername());

        // パスワードをハッシュ化（暗号化）
        String hashedPassword = passwordEncoder.encode(userForm.getPassword());
        user.setPassword(hashedPassword);

        // ユーザーをデータベースに挿入
        userRepository.insertUser(user);
    }

}
