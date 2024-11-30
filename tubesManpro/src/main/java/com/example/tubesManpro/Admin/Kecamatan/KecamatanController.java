package com.example.tubesManpro.Admin.Kecamatan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/kecamatan")
public class KecamatanController {

    @Autowired
    private KecamatanRepository kecamatanRepository;

    // Endpoint untuk menambahkan data Kecamatan
    @PostMapping("/add")
    public Map<String, String> addKecamatan(@RequestParam String namaKecamatan) {
        KecamatanData kecamatan = new KecamatanData();
        kecamatan.setNamaKecamatan(namaKecamatan);
        kecamatanRepository.save(kecamatan);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Kecamatan berhasil ditambahkan");
        return response;
    }

    // Endpoint untuk mendapatkan semua data Kecamatan dengan pagination
    @GetMapping("/all")
    public Map<String, Object> getAllKecamatanWithPagination(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        List<KecamatanData> kecamatanList = kecamatanRepository.findAllWithPagination(page, size);
        int totalData = kecamatanRepository.count(); // Tambahkan metode count() di repository
        int totalPages = (int) Math.ceil((double) totalData / size);

        Map<String, Object> response = new HashMap<>();
        response.put("data", kecamatanList);
        response.put("totalPages", totalPages);
        response.put("currentPage", page);
        return response;
    }

    // Endpoint untuk mendapatkan data Kecamatan berdasarkan ID
    @GetMapping("/{id}")
    public KecamatanData getKecamatanById(@PathVariable int id) {
        return kecamatanRepository.findById(id);
    }

    // Endpoint untuk memperbarui data Kecamatan
    @PutMapping("/{id}")
    public Map<String, String> updateKecamatan(@PathVariable int id, @RequestParam String namaKecamatan) {
        KecamatanData kecamatan = new KecamatanData();
        kecamatan.setId(id);
        kecamatan.setNamaKecamatan(namaKecamatan);
        kecamatanRepository.update(kecamatan);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Kecamatan berhasil diperbarui");
        return response;
    }

    // Endpoint untuk menghapus data Kecamatan
    @DeleteMapping("/{id}")
    public Map<String, String> deleteKecamatan(@PathVariable int id) {
        kecamatanRepository.delete(id);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Kecamatan berhasil dihapus");
        return response;
    }
}
