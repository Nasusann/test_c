package com.example.music_management.exception;

import lombok.Getter;

// Lombokの@Getterアノテーションを使って、getterメソッドを自動生成
@Getter
public class MusicNotFoundException extends RuntimeException{
    // 楽曲が見つからなかったアルバムのIDを保持するフィールド
    private final long albumId;

    // コンストラクタでエラーメッセージとアルバムIDを受け取る
    public MusicNotFoundException(String message, long albumId){
        // 親クラスのコンストラクタにエラーメッセージを渡す
        super(message);
        // アルバムIDをフィールドにセット
        this.albumId = albumId;
    }
}