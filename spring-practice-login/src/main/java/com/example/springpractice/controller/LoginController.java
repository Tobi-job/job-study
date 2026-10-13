package com.example.springpractice.controller;

import com.example.springpractice.entity.User;
import com.example.springpractice.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/")
public class LoginController {

    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@RequestParam String username,
                           @RequestParam String password,
                           Model model) {
        boolean success = userService.registerUser(username, password);
        if (!success) {
            model.addAttribute("error", "ユーザー名は既に存在するか、入力内容が不正です");
            return "register";
        }

        model.addAttribute("message", "アカウント登録が完了しました。ログインしてください。");
        return "login";
    }

    @PostMapping("/login")
    public String handleLogin(@RequestParam String username,
                             @RequestParam String password,
                             HttpSession session,
                             Model model) {
        if (userService.authenticate(username, password)) {
            User user = userService.findByUsername(username).orElse(null);
            if (user != null) {
                session.setAttribute("username", username);
                session.setAttribute("role", user.getRole());
                return "redirect:/dashboard";
            }
        }

        model.addAttribute("error", "ユーザー名またはパスワードが間違っています");
        return "login";
    }

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            return "redirect:/login";
        }

        String role = (String) session.getAttribute("role");
        model.addAttribute("message", "ログイン成功！");
        model.addAttribute("username", username);
        model.addAttribute("role", role);

        if ("ADMIN".equalsIgnoreCase(role)) {
            List<User> users = userService.findAllUsers();
            model.addAttribute("users", users);
            return "admin-dashboard";
        }

        User user = userService.findByUsername(username).orElse(null);
        model.addAttribute("user", user);
        return "user-dashboard";
    }

    @PostMapping("/admin/users/update")
    public String updateUser(@RequestParam Long userId,
                            @RequestParam String username,
                            @RequestParam(required = false) String password,
                            @RequestParam String role,
                            HttpSession session) {
        String currentUser = (String) session.getAttribute("username");
        String currentRole = (String) session.getAttribute("role");

        if (currentUser == null || !"ADMIN".equalsIgnoreCase(currentRole)) {
            return "redirect:/login";
        }

        userService.updateUser(userId, username, password, role);
        return "redirect:/dashboard";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

    @GetMapping("/")
    public String index() {
        return "redirect:/login";
    }
}
