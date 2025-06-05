package com.example.music_management.entity;


import java.time.LocalDateTime;
import lombok.Data;

@Data
public class User {
    // ユーザーの一意な識別子
    private long userId;
    // ユーザー名
    private String username;
    // ユーザーのパスワード
    private String password;
    // ユーザーが作成された日時
    private LocalDateTime createdAt;
}
