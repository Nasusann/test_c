package com.example.music_management.exception;

// RuntimeExceptionを継承してカスタム例外を作成
public class AlbumNotFoundException  extends RuntimeException  {
     // コンストラクタでエラーメッセージを受け取る
    public AlbumNotFoundException(String message) {
        // 親クラスのコンストラクタにエラーメッセージを渡す
        super(message);
    }
}
