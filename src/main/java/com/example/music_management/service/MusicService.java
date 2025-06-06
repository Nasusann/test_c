package com.example.music_management.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.music_management.entity.Album;
import com.example.music_management.entity.Music;
import com.example.music_management.exception.AlbumNotFoundException;
import com.example.music_management.exception.MusicNotFoundException;
import com.example.music_management.form.MusicForm;
import com.example.music_management.repository.AlbumRepository;
import com.example.music_management.repository.MusicRepository;
import com.example.music_management.viewmodel.MusicViewModel;

// MusicServiceクラスは音楽データの管理を行うサービスクラス
@Service
public class MusicService {
    private final MusicRepository musicRepository;
    private final AlbumRepository albumRepository;

    // コンストラクタでリポジトリを注入しています
    public MusicService(MusicRepository musicRepository, AlbumRepository albumRepository){
        this.musicRepository = musicRepository;
        this.albumRepository = albumRepository;
    }

    // 特定のアルバムIDに関連する音楽を取得します
    public List<Music>  getMusicsByAlbumId(long albumId){
        return musicRepository.getMusicsByAlbumId(albumId);
    }

    // 新しい音楽をデータベースに追加します
    @Transactional
    public void createMusic(MusicForm musicForm) {
        // アルバムが存在するか確認します
        Album existingAlbum = albumRepository.getAlbumById(musicForm.getAlbumId());
        if (existingAlbum == null) {
            throw new AlbumNotFoundException("Album not found");
        }
        
        // 新しい音楽オブジェクトを作成します
        Music music = new Music();
        music.setTitle(musicForm.getTitle());
        music.setDuration(musicForm.getDuration());
        music.setAlbumId(musicForm.getAlbumId());

        // 音楽をデータベースに挿入します
        musicRepository.insertMusic(music);
    }

    // 特定の音楽IDの音楽を削除します
    public void deleteMusic(long musicId){
        musicRepository.deleteMusicById(musicId);
    }

    // 特定の音楽IDに対応する音楽を取得します
    public Music  getMusicById(long musicId){
        return musicRepository.selectMusicById(musicId);
    }

    // 音楽情報を更新します
    @Transactional
    public void updateMusic(long musicId, Music music){
        // 既存の音楽を取得して存在を確認します
        Music existingMusic = getMusicById(musicId);
        if (existingMusic == null) {
            throw new MusicNotFoundException("Music not found", music.getAlbumId());
        }
        
        // IDが一致するか確認します
        if(musicId != music.getMusicId()){
            throw new MusicNotFoundException ("Music ID does not match",  music.getAlbumId());
        }
        // 音楽情報を更新します
        musicRepository.updateMusic(music);
    }

    // お気に入り情報を含む音楽リストを取得
    public List<MusicViewModel> selectMusicsWithFavorite(long albumId, long userId) {
        return musicRepository.selectMusicsWithFavorite(albumId, userId);
    }


}
