package com.example.music_management.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.music_management.entity.Album;
import com.example.music_management.mapper.AlbumMapper;
import com.example.music_management.viewmodel.AlbumViewModel;

// リポジトリ―クラスであることを宣言　Spring Boot の DI の対象になる
// このクラスがリポジトリであることを示し、Spring Boot によって管理されるようにする
@Repository
public class AlbumRepository {
    // AlbumMapper を DI するための記述
    // AlbumMapper を使うための準備
    private final AlbumMapper albumMapper;

     // AlbumMapper をコンストラクタで受け取って、DI（依存性注入）を行います
    public AlbumRepository(AlbumMapper albumMapper){
        this.albumMapper = albumMapper;
    }

    // AlbumMapper に定義されているデータベース処理を呼び出す
    // データベースからすべてのアルバムを取得するためのメソッド
    public List<Album> getAllAlbums(){
        return albumMapper.selectAllAlbums();
    }

    // Mapper の処理を呼び出すメソッドを追加
    // 新しいアルバムをデータベースに追加するためのメソッド
    public void insertAlbum(Album album){
        albumMapper.insertAlbum(album);
    }

    // 指定されたIDのアルバムをデータベースから取得するためのメソッド
    public Album  getAlbumById(long albumId){
        return albumMapper.selectAlbumById(albumId);
    }

    // 指定されたIDのアルバムをデータベースから削除するためのメソッド
    public void deleteAlbum(long albumId){
        albumMapper.deleteAlbumById(albumId);
    }

    // アルバム情報を更新するためのメソッド
    public void updateAlbum(Album album) {
        albumMapper.updateAlbum(album);
    }

    // すべてのアルバムと、それに含まれる音楽の数を取得するためのメソッド
    public List<AlbumViewModel> getAllAlbumsWithMusicCount() {
        return albumMapper.selectAllAlbumsWithMusicCount();
    }
}
