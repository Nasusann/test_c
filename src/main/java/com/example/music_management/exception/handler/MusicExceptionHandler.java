package com.example.music_management.exception.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.music_management.exception.MusicNotFoundException;

// コントローラ全体で例外を処理するためのアノテーション
@ControllerAdvice
// MusicNotFoundExceptionが発生したときにこのメソッドが呼ばれる
public class MusicExceptionHandler {
    @ExceptionHandler(MusicNotFoundException.class)
public String handleMusicNotFoundException(MusicNotFoundException e, RedirectAttributes redirectAttributes){
     // エラーメッセージをフラッシュ属性として追加し、リダイレクト時に表示する
    redirectAttributes.addFlashAttribute("error", "対象の楽曲が見つかりませんでした");
    // 楽曲が見つからなかったアルバムのページにリダイレクト    
    return "redirect:/albums/" + e.getAlbumId();
    }
}
