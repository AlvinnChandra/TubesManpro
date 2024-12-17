package com.example.tubesManpro.Pelanggan;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/view")
public class ViewPelangganController {
    @Autowired
    private ViewPelangganRepository viewPelangganRepository;

    // Endpoint untuk mendapatkan semua data mesin
    @GetMapping("/all")
    public List<ViewPelanggan> getAllMesin() {
        return viewPelangganRepository.findAll();
    }
}
