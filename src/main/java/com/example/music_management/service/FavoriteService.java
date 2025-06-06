package com.example.music_management.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.music_management.entity.Music;
import com.example.music_management.exception.MusicNotFoundException;
import com.example.music_management.repository.FavoriteRepository;
import com.example.music_management.repository.MusicRepository;

@Service
public class FavoriteService {
    // FavoriteRepository と MusicRepository を DI するための記述
    private final FavoriteRepository favoriteRepository;
    private final MusicRepository musicRepository;

    // コンストラクタでリポジトリを初期化
    public FavoriteService(FavoriteRepository favoriteRepository, MusicRepository musicRepository) {
        this.favoriteRepository = favoriteRepository;
        this.musicRepository = musicRepository;
    }
    
    // お気に入りの音楽を挿入するメソッド
    @Transactional
    public void insertFavorite(long userId, long albumId , long musicId) {
        // 音楽が存在するか確認
        Music existingMusic = musicRepository.selectMusicById(musicId);
        // 音楽が見つからなかった場合、例外を投げる
        if (existingMusic == null) {
            throw new MusicNotFoundException("Music not found", albumId);
        }
        
        // お気に入りの音楽をリポジトリに挿入
        favoriteRepository.insertFavorite(userId, musicId);
    }

    // お気に入りの音楽を削除するメソッド
    public void deleteFavorite(long userId, long musicId) {
        // 指定されたユーザーのお気に入り音楽を削除
        favoriteRepository.deleteFavorite(userId, musicId);
    }
}
