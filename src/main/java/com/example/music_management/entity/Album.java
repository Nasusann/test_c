package com.example.music_management.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

// Lombokを使ったgettreやsetterを自動生成するためのアノテーション
@Data
public class Album {
    // アルバムの一意な識別子
    private long albumId;
    // アルバムのタイトル
    private String title;
    // アーティスト名
    private String artist;
    // 日付型のフォーマットを指定　fromタグのtype属性date都フォーマットを統一
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate releaseDate;
    // Mybatis の自動変換で利用するため
    // アルバムが作成された日時、MyBatisの自動変換で使用
    private LocalDateTime createdAt;
}
