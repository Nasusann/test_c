package com.example.music_management.viewmodel;

import java.time.LocalDate;

import lombok.Data;

// アルバムの情報を保持するためのクラスです
// Lombokのアノテーションで、ゲッター、セッター、toStringなどを自動生成
@Data
public class AlbumViewModel {
    // アルバムのIDを保持するフィールドです
    private long albumId;
    // アルバムのタイトルを保持するフィールドです
    private String title;
    // アルバムのアーティスト名を保持するフィールドです
    private String artist;
    // アルバムのリリース日を保持するフィールドです
    private LocalDate releaseDate;
    // アルバムに含まれる音楽の数を保持するフィールドです
    private int musicCount;

}
