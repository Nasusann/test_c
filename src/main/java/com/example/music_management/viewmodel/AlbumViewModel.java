package com.example.music_management.viewmodel;

import java.time.LocalDate;

import lombok.Data;

@Data
public class AlbumViewModel {
    private long albumId;
    private String title;
    private String artist;
    private LocalDate releaseDate;
    private int musicCount;

}
