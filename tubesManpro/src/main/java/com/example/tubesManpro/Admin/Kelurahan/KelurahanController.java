package com.example.tubesManpro.Admin.Kelurahan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/kelurahan")
public class KelurahanController {

    @Autowired
    private KelurahanRepository kelurahanRepository;

    // Endpoint untuk menambahkan data Kelurahan
    @PostMapping("/add")
    public Map<String, String> addKelurahan(
            @RequestParam String namaKelurahan,
            @RequestParam int kecamatanId) {
        KelurahanData kelurahan = new KelurahanData();
        kelurahan.setNamaKelurahan(namaKelurahan);
        kelurahan.setId(kecamatanId); // Asosiasi dengan Kecamatan
        kelurahanRepository.save(kelurahan);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Kelurahan berhasil ditambahkan");
        return response;
    }

    // Endpoint untuk mendapatkan semua data Kelurahan dengan pagination
    @GetMapping("/all")
    public Map<String, Object> getAllKelurahanWithPagination(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "16") int size) {
        List<KelurahanData> kelurahanList = kelurahanRepository.findAllWithPagination(page, size);
        int totalData = kelurahanRepository.count();
        int totalPages = (int) Math.ceil((double) totalData / size);

        Map<String, Object> response = new HashMap<>();
        response.put("data", kelurahanList);
        response.put("totalPages", totalPages);
        response.put("currentPage", page);
        return response;
    }

    // Endpoint untuk mendapatkan data Kelurahan berdasarkan ID
    @GetMapping("/{id}")
    public KelurahanData getKelurahanById(@PathVariable int id) {
        return kelurahanRepository.findById(id);
    }

    // Endpoint untuk memperbarui data Kelurahan
    @PutMapping("/{id}")
    public Map<String, String> updateKelurahan(
            @PathVariable int id,
            @RequestParam String namaKelurahan) {
        KelurahanData kelurahan = new KelurahanData();
        kelurahan.setId(id);
        kelurahan.setNamaKelurahan(namaKelurahan);
        kelurahanRepository.update(kelurahan);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Kelurahan berhasil diperbarui");
        return response;
    }

    // Endpoint untuk menghapus data Kelurahan
    @DeleteMapping("/{id}")
    public Map<String, String> deleteKelurahan(@PathVariable int id) {
        kelurahanRepository.delete(id);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Kelurahan berhasil dihapus");
        return response;
    }
}
