package com.example.music_management.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import com.example.music_management.entity.User;
// MyBatis のマッパーインタフェースであることを示す
@Mapper
public interface UserMapper {
    // ユーザー名を使って、ユーザー情報をデータベースから取得
    // 取得した情報にはユーザーID、ユーザー名、パスワード、作成日時が含まれる
    @Select("SELECT user_id, username, password, created_at FROM users WHERE username = #{username}")
    User selectUserByUsername(String username);

    // ユーザー名とパスワードをデータベースに新しく追加
    // 自動で生成されたユーザーIDをUserオブジェクトに設定
    @Insert("INSERT INTO users (username, password) VALUES (#{username}, #{password})")
    @Options(useGeneratedKeys = true, keyProperty = "userId")
    void insertUser(User user);
}

