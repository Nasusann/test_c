package com.example.music_management.controller;

import com.example.music_management.form.UserForm;
import com.example.music_management.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
@Controller
public class RegistrationController {
    private final UserService userService;

    // UserServiceをコンストラクタ経由で
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

     // HTTP GETリクエストが"/register"に来たときにこのメソッドを実行
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
         // 新しいユーザーフォームオブジェクトをモデルに追加して、テンプレートで使用できるようする
        model.addAttribute("userForm", new UserForm());
        // "register"という名前のビューを返します
        return "register";
    }
    
    // HTTP POSTリクエストが"/register"に来たときにこのメソッドを実行
    @PostMapping("/register")
    public String registerUser(UserForm userForm) {
        // フォームから送信されたユーザー情報を使って、新しいユーザーを作成
        userService.createUser(userForm);
        // 登録が成功したら、ログインページにリダイレクトし、"register"というパラメータを追加
        return "redirect:/login?register";
    }
}
