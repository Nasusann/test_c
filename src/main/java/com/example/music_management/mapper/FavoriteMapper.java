package com.example.music_management.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

// MyBatis のマッパーインタフェースであることを示す
@Mapper
public interface FavoriteMapper {
    // favoritesテーブルに新しいお気に入りのレコードを挿入するメソッド
    // user_idとmusic_idを指定して、お気に入りの楽曲を追加
    @Insert("INSERT INTO favorites (user_id, music_id) VALUES (#{userId}, #{musicId})")
    void insertFavorite(long userId, long musicId);

    // 指定されたuser_idとmusic_idに基づいて、お気に入りの楽曲を削除するメソッド
    // これにより、ユーザーがお気に入りから楽曲を削除できる
    @Delete("DELETE FROM favorites WHERE user_id = #{userId} AND music_id = #{musicId}")
    void deleteFavorite(long userId, long musicId);
    
}

