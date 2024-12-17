package com.example.tubesManpro.Login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class LoginController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, Model model) {
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        try {
            Map<String, Object> user = jdbcTemplate.queryForMap(sql, username, password);

            String role = (String) user.get("role");

            if ("admin".equals(role)) {
                return "UI-UX Admin/Admin";
            } else if ("pemilik".equals(role)) {
                return "UI-UX Pemilik/LaporanPemilik";
            }
        } catch (Exception e) {
            model.addAttribute("error", "Nama pengguna atau kata sandi tidak valid");
        }
        return "login";
    }

    @GetMapping("/admin")
    public String adminPage() {
        return "UI-UX Admin/Admin";
    }

    @GetMapping("/UI-UX Admin/dataKecamatan")
    public String dataKecamatanPage() {
        return "UI-UX Admin/dataKecamatan";
    }

    @GetMapping("/UI-UX Admin/dataKelurahan")
    public String dataKelurahanPage() {
        return "UI-UX Admin/dataKelurahan";
    }

    @GetMapping("/UI-UX Admin/dataPelanggan")
    public String dataPelangganPage() {
        return "UI-UX Admin/dataPelanggan";
    }

    @GetMapping("/UI-UX Admin/dataMesin")
    public String dataMesinPage() {
        return "UI-UX Admin/dataMesin";
    }

    @GetMapping("/UI-UX Admin/dataTransaksi")
    public String dataTransaksiPage() {
        return "UI-UX Admin/dataTransaksi";
    }

    @GetMapping("/pelanggan")
    public String pelangganPage() {
        return "UI-UX Pelanggan/PageDaftarMesin";
    }

}
