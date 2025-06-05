package com.example.music_management.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.music_management.entity.Music;
import com.example.music_management.exception.MusicNotFoundException;
import com.example.music_management.repository.FavoriteRepository;
import com.example.music_management.repository.MusicRepository;

@Service
public class FavoriteService {
    private final FavoriteRepository favoriteRepository;
    private final MusicRepository musicRepository;

    public FavoriteService(FavoriteRepository favoriteRepository, MusicRepository musicRepository) {
        this.favoriteRepository = favoriteRepository;
        this.musicRepository = musicRepository;
    }
    
    @Transactional
    public void insertFavorite(long userId, long albumId , long musicId) {
        Music existingMusic = musicRepository.selectMusicById(musicId);
        if (existingMusic == null) {
            throw new MusicNotFoundException("Music not found", albumId);
        }
        
        favoriteRepository.insertFavorite(userId, musicId);
    }

    public void deleteFavorite(long userId, long musicId) {
        favoriteRepository.deleteFavorite(userId, musicId);
    }
}
