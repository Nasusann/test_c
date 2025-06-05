package com.example.music_management.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    
    // HTTP GETリクエストが"/login"に来たときにこのメソッドを実行
    @GetMapping("/login")
    public String login() {
        // "login"という名前のビューを返す
        // 実際のログインページを表示するためのテンプレートを指す
        return "login";
    }
}
