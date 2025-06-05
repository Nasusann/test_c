package com.example.music_management.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.music_management.entity.Music;
import com.example.music_management.viewmodel.MusicViewModel;

// MyBatis のマッパーインタフェースであることを示す
@Mapper
public interface MusicMapper {  
    // 外部キーであるアルバムIDを指定して楽曲の一覧を取得
    @Select("SELECT * FROM musics WHERE album_id = #{albumId}")
    List<Music> selectMusicsById(long albumId); 
    
    // 新しい楽曲をデータベースに挿入
    // 自動生成されたmusic_idをMusicオブジェクトに設定
    @Insert("INSERT INTO musics (title, duration, album_id) VALUES (#{title}, #{duration}, #{albumId})")
    @Options(useGeneratedKeys=true, keyProperty="musicId")
    void insertMusic(Music music);

    // 指定されたmusic_idに基づいて楽曲を削除
    @Delete("DELETE FROM musics WHERE music_id = #{musicId}")
    void deleteMusicById(long musicId);

    // 指定された楽曲の情報を更新
    @Update("UPDATE musics SET title = #{title}, duration = #{duration} WHERE music_id = #{musicId}")
    void updateMusic(Music music);

    // 指定されたmusic_idに基づいて楽曲を取得
    @Select("SELECT * FROM musics WHERE music_id = #{musicId}")
    Music  selectMusicById(long musicId);

    // アルバムIDおよびユーザーIDを基に、お気に入り情報を含めた楽曲の一覧を取得
    // お気に入りの場合はis_favoriteがtrueになる
    @Select("""
            SELECT
                musics.music_id,
                title,
                duration,
                favorites.user_id IS NOT NULL AS is_favorite
            FROM musics
            LEFT JOIN favorites ON musics.music_id = favorites.music_id AND favorites.user_id = #{userId}
            WHERE album_id = #{albumId}
            """)
    List<MusicViewModel> selectMusicsWithFavorite(long albumId, long userId);
}