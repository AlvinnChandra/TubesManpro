package com.example.tubesManpro.Admin.Pelanggan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/pelanggan")
public class PelangganController {

    @Autowired
    private PelangganRepository pelangganRepository;

    @PostMapping("/add")
    public Map<String, String> addPelanggan(@RequestBody Pelanggan pelanggan) {
        pelangganRepository.save(pelanggan);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Pelanggan berhasil ditambahkan");
        return response;
    }

    @GetMapping("/all")
    public List<Pelanggan> getAllPelanggan() {
        return pelangganRepository.findAll();
    }

    @GetMapping("/{id}")
    public Pelanggan getPelangganById(@PathVariable int id) {
        return pelangganRepository.findById(id);
    }

    @PutMapping("/{id}")
    public Map<String, String> updatePelanggan(@PathVariable int id, @RequestParam String nama,
            @RequestParam String telepon, @RequestParam String email,
            @RequestParam String kecamatan, @RequestParam String kelurahan) {
        Pelanggan pelanggan = new Pelanggan();
        pelanggan.setId(id);
        pelanggan.setNama(nama);
        pelanggan.setTelepon(telepon);
        pelanggan.setEmail(email);
        pelanggan.setKecamatan(kecamatan);
        pelanggan.setKelurahan(kelurahan);
        pelangganRepository.update(pelanggan);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Pelanggan berhasil diperbarui");
        return response;
    }

    @DeleteMapping("/{id}")
    public Map<String, String> deletePelanggan(@PathVariable int id) {
        pelangganRepository.deleteById(id);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Pelanggan berhasil dihapus");
        return response;
    }
}
