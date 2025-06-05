package com.example.music_management.exception.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.music_management.exception.AlbumNotFoundException;

// コントローラ全体で例外を処理するためのアノテーション
@ControllerAdvice
public class AlbumExceptionHandler {
    // AlbumNotFoundExceptionが発生したときにこのメソッドが呼ばれる
    @ExceptionHandler(AlbumNotFoundException.class)
    public String handleAlbumNotFoundException(AlbumNotFoundException e, RedirectAttributes redirectAttributes) {
        // エラーメッセージをフラッシュ属性として追加し、リダイレクト時に表示する
        redirectAttributes.addFlashAttribute("error", "対象のアルバムが見つかりませんでした");
        // "/albums"にリダイレクト
        return "redirect:/albums";
    }
}
