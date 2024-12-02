package com.example.tubesManpro.Admin.Kelurahan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/lokasi")
public class LokasiController {

    @Autowired
    private KecamatanRepository kecamatanRepository;

    @Autowired
    private KelurahanRepository kelurahanRepository;

    // Endpoint untuk mendapatkan semua data kecamatan
    @GetMapping("/kecamatan")
    public List<KecamatanData> getAllKecamatan(@RequestParam(defaultValue = "10") int limit,
            @RequestParam(defaultValue = "0") int offset) {
        return kecamatanRepository.findAll(limit, offset);
    }

    // Endpoint untuk mendapatkan kecamatan berdasarkan ID
    @GetMapping("/kecamatan/{id}")
    public List<KecamatanData> getKecamatanById(@PathVariable int id) {
        return kecamatanRepository.findById(id);
    }

    // Endpoint untuk menambahkan kecamatan baru
    @PostMapping("/kecamatan")
    public Map<String, String> addKecamatan(@RequestParam String namaKecamatan) {
        KecamatanData kecamatan = new KecamatanData();
        kecamatan.setNamaKecamatan(namaKecamatan);
        kecamatanRepository.save(kecamatan);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Kecamatan berhasil ditambahkan");
        return response;
    }

    // Endpoint untuk memperbarui kecamatan berdasarkan ID
    @PutMapping("/kecamatan/{id}")
    public Map<String, String> updateKecamatan(@PathVariable int id, @RequestParam String namaKecamatan) {
        KecamatanData kecamatan = new KecamatanData();
        kecamatan.setId(id);
        kecamatan.setNamaKecamatan(namaKecamatan);
        kecamatanRepository.update(kecamatan);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Kecamatan berhasil diperbarui");
        return response;
    }

    // Endpoint untuk menghapus kecamatan berdasarkan ID
    @DeleteMapping("/kecamatan/{id}")
    public Map<String, String> deleteKecamatan(@PathVariable int id) {
        kecamatanRepository.deleteById(id);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Kecamatan berhasil dihapus");
        return response;
    }

    // Endpoint untuk mendapatkan semua kelurahan berdasarkan kecamatan_id
    @GetMapping("/kelurahan")
    public List<Kelurahan> getKelurahanByKecamatanId(@RequestParam int kecamatanId,
            @RequestParam(defaultValue = "10") int limit,
            @RequestParam(defaultValue = "0") int offset) {
        return kelurahanRepository.findByKecamatanId(kecamatanId, limit, offset);
    }

    // Endpoint untuk mendapatkan kelurahan berdasarkan ID
    @GetMapping("/kelurahan/{id}")
    public List<Kelurahan> getKelurahanById(@PathVariable int id) {
        return kelurahanRepository.findById(id);
    }

    // Endpoint untuk menambahkan kelurahan baru
    @PostMapping("/kelurahan")
    public Map<String, String> addKelurahan(@RequestParam String namaKelurahan,
            @RequestParam int kecamatanId) {
        Kelurahan kelurahan = new Kelurahan();
        kelurahan.setNamaKelurahan(namaKelurahan);
        kelurahan.setKecamatanId(kecamatanId);
        kelurahanRepository.save(kelurahan);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Kelurahan berhasil ditambahkan");
        return response;
    }

    // Endpoint untuk memperbarui kelurahan berdasarkan ID
    @PutMapping("/kelurahan/{id}")
    public Map<String, String> updateKelurahan(@PathVariable int id,
            @RequestParam String namaKelurahan,
            @RequestParam int kecamatanId) {
        Kelurahan kelurahan = new Kelurahan();
        kelurahan.setId(id);
        kelurahan.setNamaKelurahan(namaKelurahan);
        kelurahan.setKecamatanId(kecamatanId);
        kelurahanRepository.update(kelurahan);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Kelurahan berhasil diperbarui");
        return response;
    }
}