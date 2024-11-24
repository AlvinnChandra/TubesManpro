package com.example.tubesManpro.Admin.Pegawai;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pegawai")
public class PegawaiController {

    @Autowired
    private jdbcPegawaiRepository pegawaiRepository;

    @PostMapping("/add")
    public String addPegawai(@RequestParam String nama, @RequestParam String posisi) {
        PegawaiData pegawai = new PegawaiData();
        pegawai.setNama(nama);
        pegawai.setPosisi(posisi);
        pegawaiRepository.save(pegawai);
        return "/UI-UX Admin/Admin";
    }

    @GetMapping("/all")
    public List<PegawaiData> getAllPegawai() {
        return pegawaiRepository.findAll();
    }
}
