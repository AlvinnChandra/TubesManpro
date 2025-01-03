package com.example.tubesManpro.Admin.Mesin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/mesin")
public class MesinController {

    @Autowired
    private jdbcMesinRepository mesinRepository;

    // Endpoint untuk mendapatkan semua data mesin
    @GetMapping("/all")
    public List<MesinData> getAllMesin() {
        return mesinRepository.findAll();
    }

    @GetMapping("/{id}")
    public MesinData getMesinById(@PathVariable int id) {
        return mesinRepository.findById(id);
    }

    // Endpoint untuk menambahkan data mesin baru
    @PostMapping("/add")
    public Map<String, String> addMesin(@RequestParam String merek,
                                        @RequestParam int kapasitas,
                                        @RequestParam int tarif,
                                        @RequestParam(defaultValue = "available") String status) {
        MesinData mesin = new MesinData();
        mesin.setMerek(merek);
        mesin.setKapasitas(kapasitas);
        mesin.setTarif(tarif);
        mesin.setStatus(status);
        mesinRepository.addMesin(mesin);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Mesin berhasil ditambahkan");
        return response;
    }

    // Endpoint untuk memperbarui data mesin berdasarkan ID
    @PutMapping("/{id}")
    public Map<String, String> updateMesin(@PathVariable int id,
                                        @RequestParam String merek,
                                        @RequestParam int kapasitas,
                                        @RequestParam int tarif,
                                        @RequestParam String status) {
        MesinData mesin = new MesinData();
        mesin.setId(id);
        mesin.setMerek(merek);
        mesin.setKapasitas(kapasitas);
        mesin.setTarif(tarif);
        mesin.setStatus(status);
        mesinRepository.updateMesin(mesin);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Mesin berhasil diperbarui");
        return response;
    }

    // Endpoint untuk menghapus data mesin berdasarkan ID
    @DeleteMapping("/{id}")
    public Map<String, String> deleteMesin(@PathVariable int id) {
        mesinRepository.deleteMesin(id);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Mesin berhasil dihapus");
        return response;
    }

    @GetMapping("/list")
    public List<MesinData> getAllMerek() {
        return mesinRepository.findAllMerek();
    }
}
