package com.example.music_management.entity;

import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class Music {
    // 音楽の一意な識別子
    private long musicId;
    // 音楽のタイトル
    private String title;
    @DateTimeFormat(pattern = "HH:mm:ss")
    // 　 LocalTime　時間を保持するクラス
    private LocalTime duration;
    // この音楽が属するアルバムのID
    private long albumId;
    // 音楽が作成された日時
    private LocalDateTime createdAt;
}
