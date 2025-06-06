package com.example.music_management.repository;

import com.example.music_management.mapper.FavoriteMapper;
 import org.springframework.stereotype.Repository;

// このクラスがリポジトリであることを示し、Spring Boot によって管理されるようにします
 @Repository
public class FavoriteRepository {
    // FavoriteMapper を使うための準備
    private final FavoriteMapper favoriteMapper;
    public FavoriteRepository(FavoriteMapper favoriteMapper) {
        this.favoriteMapper = favoriteMapper;
    }
    // FavoriteMapper をコンストラクタで受け取って、DI（依存性注入）を行います
    public void insertFavorite(long userId, long musicId) {
        favoriteMapper.insertFavorite(userId, musicId);
    }

    public void deleteFavorite(long userId, long musicId) {
        favoriteMapper.deleteFavorite(userId, musicId);
    }
}