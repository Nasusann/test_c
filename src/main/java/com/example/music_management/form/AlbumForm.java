package com.example.music_management.form;
// インポート
import lombok.Data;
import java.time.LocalDate;

// アルバム登録フォーム
@Data
public class AlbumForm {
    // アルバムのタイトル
    private String title;
    // アルバムのアーティスト
    private String artist;
    // アルバムのリリース日
    private LocalDate releaseDate;
}