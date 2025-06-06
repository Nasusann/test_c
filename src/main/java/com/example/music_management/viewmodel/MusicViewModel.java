package com.example.music_management.viewmodel;

import java.time.LocalTime;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

// 音楽の情報を保持するためのクラスです
// Lombokのアノテーションで、ゲッター、セッター、toStringなどを自動生成
@Data
public class MusicViewModel {
    // 音楽のIDを保持するフィールドです
    private long musicId;
     // 音楽のタイトルを保持するフィールドです
    private String title;
     // 音楽の再生時間を保持するフィールドです
    // @DateTimeFormatアノテーションで、時間のフォーマットを指定しています
    @DateTimeFormat(pattern = "HH:mm:ss")
    private LocalTime duration;
    // お気に入りかどうかを示すフラグを保持するフィールドです
    private boolean isFavorite;
}
