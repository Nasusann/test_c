package com.example.music_management.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Delete;
import com.example.music_management.entity.Album;
import com.example.music_management.viewmodel.AlbumViewModel;

// MyBatis のマッパーインタフェースであることを宣言
@Mapper
// MyBatis はインタフェース
public interface AlbumMapper {
    // @Select アノテーションを使って実行したい SQL を記述
    // albums テーブルからすべての情報を取得
    @Select("SELECT * FROM albums")
    List<Album> selectAllAlbums();

    // @INSERT アノテーションを使って、INSERT 文の SQL を定義
    @Insert("INSERT INTO albums (title, artist, release_date) VALUES (#{title}, #{artist}, #{releaseDate})")
    // INSERT した際に自動採番される ID を自動で引数のインスタンスにセットする
    @Options(useGeneratedKeys = true, keyProperty = "albumId")
    void insertAlbum(Album album);

    // album_id を指定して 1 件だけ取得するメソッド
    @Select("SELECT * FROM albums WHERE album_id = #{albumId}")
    Album selectAlbumById(long albumId);

    // album_id を指定して DELETE 文を実行するメソッド
    @Delete("DELETE FROM albums WHERE album_id = #{albumId}")
    void deleteAlbumById(long albumId);

    // album_id を指定して UPDATE 文を実行するメソッド
    @Update("UPDATE albums SET title = #{title}, artist = #{artist}, release_date = #{releaseDate} WHERE album_id = #{albumId}")
    void updateAlbum(Album album);

     // 各アルバムとその楽曲数を取得するメソッド
    // albumsテーブルとmusicsテーブルを結合し、楽曲の数をカウントする
    @Select("""
            SELECT albums.album_id, albums.title, artist, release_date, count(musics.music_id) AS music_count
            FROM albums
            LEFT OUTER JOIN musics ON albums.album_id = musics.album_id
            GROUP BY albums.album_id, albums.title, artist, release_date
            """)

    public List<AlbumViewModel>  selectAllAlbumsWithMusicCount();
}
