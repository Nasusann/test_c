package com.example.music_management.controller;

// インポート
import com.example.music_management.security.CustomUserDetails;
import com.example.music_management.service.FavoriteService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
// "/favorites"というURLに対するリクエストを処理するコントローラーを定義
@RequestMapping("/favorites")
public class FavoriteController {
    private final FavoriteService favoriteService;
    
    // コンストラクタ
    public FavoriteController(FavoriteService favoriteService) {
        this.favoriteService = favoriteService;
    }

    // HTTP POSTリクエストを処理するメソッドを定義
    @PostMapping
    public String addFavorite(@RequestParam long musicId,
                                @RequestParam long albumId,
                                @AuthenticationPrincipal CustomUserDetails userDetails,
                                RedirectAttributes redirectAttributes) {
        // お気に入りを追加するためのサービスメソッドを呼び出し
        favoriteService.insertFavorite(userDetails.getUserId(), albumId ,musicId);
        // ユーザーにフィードバックメッセージを設定
        redirectAttributes.addFlashAttribute("message", "お気に入りに追加しました");
        // 処理完了後にアルバムページにリダイレクト
        return "redirect:/albums/" + albumId;
    }

    // "/delete"のURLに対するPOSTリクエストを処理するメソッドを定義
    @PostMapping("/delete")
    public String deleteFavorite(@RequestParam long musicId, // リクエストからmusicIdを取得
                                    @RequestParam long albumId, // リクエストからalbumIdを取得
                                    @AuthenticationPrincipal CustomUserDetails userDetails,
                                    // リダイレクト時に属性を渡すためのオブジェクト
                                    RedirectAttributes redirectAttributes) {
        // お気に入りを削除するためのサービスメソッドを呼び出し
        favoriteService.deleteFavorite(userDetails.getUserId(), musicId);
        // ユーザーにフィードバックメッセージを設定
        redirectAttributes.addFlashAttribute("message", "お気に入りを解除しました");
        // 処理完了後にアルバムページにリダイレクト
        return "redirect:/albums/" + albumId;
    }
    
}
