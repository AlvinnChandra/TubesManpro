package com.example.tubesManpro.Admin.Pegawai;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/pegawai")
public class PegawaiController {

    @Autowired
    private jdbcPegawaiRepository pegawaiRepository;

    @PostMapping("/add")
    public Map<String, String> addPegawai(@RequestParam String nama, @RequestParam String posisi) {
        PegawaiData pegawai = new PegawaiData();
        pegawai.setNama(nama);
        pegawai.setPosisi(posisi);
        pegawaiRepository.save(pegawai);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Pegawai berhasil ditambahkan");
        return response;
    }

    @GetMapping("/all")
    public List<PegawaiData> getAllPegawai() {
        return pegawaiRepository.findAll();
    }
}
