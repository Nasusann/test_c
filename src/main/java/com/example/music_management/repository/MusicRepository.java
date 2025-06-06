package com.example.music_management.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.music_management.entity.Music;
import com.example.music_management.mapper.MusicMapper;
import com.example.music_management.viewmodel.MusicViewModel;

// このクラスは音楽に関するデータベース操作を行うリポジトリ
@Repository
public class MusicRepository{
    // MusicMapperを使ってデータベース操作を行う
    private final MusicMapper musicMapper;

    // コンストラクタでMusicMapperを受け取って初期化
    public MusicRepository(MusicMapper musicMapper){
        this.musicMapper = musicMapper;
    }

    // 指定されたアルバムIDで音楽を検索し、リストとして返す
    public List<Music> getMusicsByAlbumId(long albumId){
        return musicMapper.selectMusicsById(albumId);
    }

    // 新しい音楽のエントリをデータベースに追加
    public void insertMusic(Music music){
        musicMapper.insertMusic(music);
    }

    // 指定された音楽IDの音楽をデータベースから削除
    public void deleteMusicById(long musicId){
        musicMapper.deleteMusicById(musicId);
    }

    // 音楽の情報を更新
    public void updateMusic(Music music){
        musicMapper.updateMusic(music);
    }

    // 指定された音楽IDの音楽を検索して返す
    public Music selectMusicById(long musicId){
        return musicMapper.selectMusicById(musicId);
    }

    // ユーザーのお気に入り情報を含めて、指定されたアルバム内の音楽をリストとして返す
    public List<MusicViewModel> selectMusicsWithFavorite(long albumId, long userId) {
        return musicMapper.selectMusicsWithFavorite(albumId, userId);
    }
}