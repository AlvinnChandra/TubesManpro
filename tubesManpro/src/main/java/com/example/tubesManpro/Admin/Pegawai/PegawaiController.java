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

    @GetMapping("/{id}")
    public PegawaiData getPegawaiById(@PathVariable int id) {
        return pegawaiRepository.findById(id);
    }

    @PutMapping("/{id}")
    public Map<String, String> updatePegawai(@PathVariable int id, @RequestParam String nama,
            @RequestParam String posisi) {
        PegawaiData pegawai = new PegawaiData();
        pegawai.setId(id);
        pegawai.setNama(nama);
        pegawai.setPosisi(posisi);
        pegawaiRepository.update(pegawai);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Pegawai berhasil diperbarui");
        return response;
    }

    @DeleteMapping("/{id}")
    public Map<String, String> deletePegawai(@PathVariable int id) {
        pegawaiRepository.delete(id);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Pegawai berhasil dihapus");
        return response;
    }
}
