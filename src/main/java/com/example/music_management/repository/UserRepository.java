package com.example.music_management.repository;

import org.springframework.stereotype.Repository;

import com.example.music_management.entity.User;
import com.example.music_management.mapper.UserMapper;

// このクラスはユーザーに関するデータベース操作を行うリポジトリです
@Repository
public class UserRepository {
    // UserMapperを使ってデータベース操作を行います
    private final UserMapper userMapper;

    // コンストラクタでUserMapperを受け取って初期化します
    public UserRepository(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

     // ユーザー名でユーザーを検索し、ユーザー情報を返します
    public User selectUserByUsername(String username) {
        return userMapper.selectUserByUsername(username);
    }

    // 新しいユーザーのエントリをデータベースに追加します
    public void insertUser(User user) {
        userMapper.insertUser(user);
    }
}
