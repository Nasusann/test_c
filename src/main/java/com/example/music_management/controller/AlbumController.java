package com.example.music_management.controller;
// インポート
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

import com.example.music_management.entity.Album;
import com.example.music_management.entity.Music;
import com.example.music_management.form.AlbumForm;
import com.example.music_management.form.MusicForm;
import com.example.music_management.service.AlbumService;
import com.example.music_management.service.MusicService;
import com.example.music_management.viewmodel.AlbumViewModel;

import com.example.music_management.security.CustomUserDetails;
import com.example.music_management.viewmodel.MusicViewModel;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

//  Controller クラス全体のルーティングを設定
@Controller

// 各メソッドの @GetMapping や @PostMapping の値の前に設定　
// URL
@RequestMapping("/albums") 
public class AlbumController {
    private final AlbumService albumService;
    private final MusicService musicService;

    // コンストラクタでサービスを受け取る
    public AlbumController(AlbumService albumService, MusicService musicService){
        this.albumService = albumService;
        this.musicService = musicService;
    }

    // 全てのアルバムを表示する画面を呼び出す
    @GetMapping
    public String albums(Model model) {
        List<AlbumViewModel> albums = albumService.getAllAlbumsWithMusicCount();
        model.addAttribute("albums", albums);
        // templates フォルダの下の album フォルダの中の album-list.htmlを表示する
        return "album/album-list";
    }

    // アルバム登録フォームの画面を表示するためのルーティングを追加
    @GetMapping("/new")
    public String albumForm(Model model){
        AlbumForm albumForm = new AlbumForm();
        model.addAttribute("albumForm", albumForm);
        return "album/album-form";
    }

     // アルバムを新規作成します
    @PostMapping("/new")
    public String createAlbum(AlbumForm albumForm){
        albumService.createAlbum(albumForm);
        // アルバム一覧画面へのリダイレクト要求を返します。
        return "redirect:/albums";
    }

    // 詳細画面へのルーティングを追記
    @GetMapping("/{albumId}")
    public String album(@PathVariable long albumId,
                        Model model,
                        @AuthenticationPrincipal CustomUserDetails userDetails) {
        // データベースからアルバムの詳細を取得するビジネスロジックを呼び出し、結果を album-detail.html に渡す
        Album album = albumService.getAlbumById(albumId);
        // List<Music> musics = musicService.getMusicsByAlbumId(albumId);
        List<MusicViewModel> musics = musicService.selectMusicsWithFavorite(albumId, userDetails.getUserId());

        model.addAttribute("album", album);
        model.addAttribute("musics", musics);
        return "album/album-detail";
    }

    // 特定のアルバムを削除
    @PostMapping("/{albumId}/delete")
    public String deleteAlbum(@PathVariable long albumId) {
        albumService.deleteAlbum(albumId);
        return "redirect:/albums";
    }

    // 編集画面を表示するルーティングを追記
    @GetMapping("/{albumId}/edit")
    public String editAlbum(@PathVariable long albumId, Model model) {
        Album album = albumService.getAlbumById(albumId);
        model.addAttribute("album", album);
        return "album/album-edit";
    }

    // 更新する処理のルーティングを追記
    @PostMapping("/{albumId}/edit")
    public String updateAlbum(@PathVariable long albumId, Album album) {
        albumService.updateAlbum(albumId, album);
        return "redirect:/albums";
    }

     // 新しい音楽を登録するためのフォームを表示
    @GetMapping("/{albumId}/musics/new")
    public String createMusicForm(@PathVariable long albumId, Model model) {
        MusicForm musicForm = new MusicForm();
        musicForm.setAlbumId(albumId);
        model.addAttribute("musicForm", musicForm);

        return "music/music-form";
    }

    // 音楽を新規作成
    @PostMapping("/{albumId}/musics/new")
    public String createMusic(@PathVariable long albumId, MusicForm musicForm) {
        musicService.createMusic(musicForm);
            return "redirect:/albums/" + albumId;
    }

     // 特定の音楽を削除します
    @PostMapping("/{albumId}/musics/{musicId}/delete")
    public String deleteMusic(@PathVariable long albumId, @PathVariable long musicId) {
        musicService.deleteMusic(musicId);
        return "redirect:/albums/" + albumId;
    }

    // 音楽を編集するためのフォームを表示
    @GetMapping("/{albumId}/musics/{musicId}/edit")
    public String editMusic(@PathVariable long albumId, @PathVariable long musicId, Model model) {
        Music music = musicService.getMusicById(musicId);
        model.addAttribute("music", music);
        return "music/music-edit";
    }

    // // 音楽を更新
    @PostMapping("/{albumId}/musics/{musicId}/edit")
    public String updateMusic(@PathVariable long albumId, @PathVariable long musicId, Music music) {
        musicService.updateMusic(musicId, music);
        return "redirect:/albums/" + albumId;
    }

}