package com.codegym.cms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestSecurityController {

    @GetMapping("/admin")
    public String getAdminPage() {
        return "admin";
    }

    @GetMapping("/admin/monitor")
    public String getAdminMonitorPage() {
        return "admin";
    }

    @GetMapping("/user")
    public String getUserPage() {
        return "user";
    }

    @GetMapping("/articles")
    public String getArticlePage() {
        return "articles";
    }

    @GetMapping("/accessDenied")
    public String getAccessDeniedPage() {
        return "accessDenied";
    }

}
