package com.example.music_management.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.music_management.entity.Music;
import com.example.music_management.mapper.MusicMapper;
import com.example.music_management.viewmodel.MusicViewModel;

@Repository
public class MusicRepository{
    private final MusicMapper musicMapper;

    public MusicRepository(MusicMapper musicMapper){
        this.musicMapper = musicMapper;
    }

    public List<Music> getMusicsByAlbumId(long albumId){
        return musicMapper.selectMusicsById(albumId);
    }

    public void insertMusic(Music music){
        musicMapper.insertMusic(music);
    }

    public void deleteMusicById(long musicId){
        musicMapper.deleteMusicById(musicId);
    }

    public void updateMusic(Music music){
        musicMapper.updateMusic(music);
    }

    public Music selectMusicById(long musicId){
        return musicMapper.selectMusicById(musicId);
    }

    public List<MusicViewModel> selectMusicsWithFavorite(long albumId, long userId) {
        return musicMapper.selectMusicsWithFavorite(albumId, userId);
    }
}