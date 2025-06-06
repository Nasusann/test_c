package com.example.music_management.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.music_management.entity.Album;
import com.example.music_management.exception.AlbumNotFoundException;
import com.example.music_management.form.AlbumForm;
import com.example.music_management.repository.AlbumRepository;
import com.example.music_management.viewmodel.AlbumViewModel;

@Service
public class AlbumService {
    // アルバムのデータ操作を行うためのリポジトリ
    // AlbumRepository を DI するための記述
    private final AlbumRepository albumRepository;

    // コンストラクタでAlbumRepositoryを注入
    public AlbumService(AlbumRepository albumRepository){
        this.albumRepository = albumRepository;
    }

    // 全てのアルバムを取得するメソッド
    // AlbumRepository に定義されているアルバム一覧取得処理を呼び出す
    public List<Album> getAllAlbums(){
        return albumRepository.getAllAlbums();
    }

    // 新しいアルバムを作成するメソッド
    //  AlbumForm クラスのインスタンスを受け取り、Album クラスのインスタンスに変換して、Repository クラスに渡す
    public void createAlbum(AlbumForm albumForm) {
        Album album = new Album();
        album.setTitle(albumForm.getTitle());
        album.setArtist(albumForm.getArtist());
        album.setReleaseDate(albumForm.getReleaseDate());
        albumRepository.insertAlbum(album);
    }

    // IDで特定のアルバムを取得するメソッド
    public Album getAlbumById(long albumId) {
        return albumRepository.getAlbumById(albumId);
    }

    // アルバムを削除するメソッド
    public void deleteAlbum(long albumId){
        albumRepository.deleteAlbum(albumId);
    }

    // アルバム情報を更新するメソッド
    // Repository の呼び出しを追記
    @Transactional
    public void updateAlbum(long albumId, Album album) {
        // 存在するアルバムを確認
        Album  existingAlbum = getAlbumById(albumId);
        if (existingAlbum == null){
            throw new AlbumNotFoundException("Album not found");
        }
        // アルバムIDが一致するか確認
        if (albumId != album.getAlbumId()) {
            throw new IllegalArgumentException("Album ID does not match");
        }
        albumRepository.updateAlbum(album);
    }
    
    // 音楽カウント情報を含む全てのアルバムを取得するメソッド
    public List<AlbumViewModel> getAllAlbumsWithMusicCount() {
        return albumRepository.getAllAlbumsWithMusicCount();
    }
}
