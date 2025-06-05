package com.example.music_management.form;

import java.time.LocalTime;

import lombok.Data;

@Data
public class MusicForm {
    // 楽曲のタイトル
    private String title;
    // 楽曲の再生時間
    private LocalTime duration;
    // 楽曲が属するアルバムのID
    private long albumId;
}
