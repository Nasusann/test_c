package com.example.music_management.viewmodel;

import java.time.LocalTime;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class MusicViewModel {
    private long musicId;
    private String title;
    @DateTimeFormat(pattern = "HH:mm:ss")
    private LocalTime duration;
    private boolean isFavorite;
}
