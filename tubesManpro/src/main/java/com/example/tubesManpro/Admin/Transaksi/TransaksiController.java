package com.example.tubesManpro.Admin.Transaksi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.tubesManpro.Admin.Mesin.MesinData;
import com.example.tubesManpro.Admin.Mesin.jdbcMesinRepository;

import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api/transaksi")
public class TransaksiController {

    @Autowired
    private TransaksiRepository transaksiRepository;

    @Autowired
    private jdbcMesinRepository jdbcMesinRepository;

    // Mendapatkan semua data transaksi
    @GetMapping("/all")
    public ResponseEntity<List<TransaksiData>> getAllTransaksi() {
        List<TransaksiData> transaksiList = transaksiRepository.findAll();
        return ResponseEntity.ok(transaksiList);
    }

    // Menambahkan data transaksi baru
    @PostMapping("/add")
    public ResponseEntity<String> addTransaksi(@RequestBody TransaksiData transaksi) {
        try {
            // Tambahkan transaksi ke database
            transaksiRepository.addTransaksi(transaksi);

            // Ubah status mesin menjadi unavailable
            transaksiRepository.updateStatusMesinToUnavailable(transaksi.getMerek());

            // Jadwalkan update status mesin menjadi available setelah transaksi selesai
            LocalTime jamSelesai = transaksi.getJamSelesai();
            long delayInMillis = calculateDelayInMillis(jamSelesai);

            // Misalnya menggunakan ScheduledExecutorService untuk menjadwalkan perubahan status setelah transaksi selesai
            ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
            scheduler.schedule(() -> {
                transaksiRepository.updateStatusMesinToAvailable(transaksi.getMerek());
            }, delayInMillis, TimeUnit.MILLISECONDS);

            return ResponseEntity.ok("Transaksi berhasil ditambahkan.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Gagal menambahkan transaksi: " + e.getMessage());
        }
    }

    // Mengisi dropdown merek mesin cuci
    @GetMapping("/list")
    public ResponseEntity<List<String>> getAllMerek() {
        try {
            List<String> merekList = transaksiRepository.findAllMerek();
            return ResponseEntity.ok(merekList);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/pelanggan")
    public ResponseEntity<List<String>> getAllPelanggan() {
        try {
            List<String> pelangganList = transaksiRepository.findAllPelanggan();
            return ResponseEntity.ok(pelangganList);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/tarif")
    public ResponseEntity<?> getTarifMesin(@RequestParam String merek) {
        MesinData mesin = jdbcMesinRepository.findByMerek(merek);
        if (mesin != null) {
            return ResponseEntity.ok(Map.of("tarif", mesin.getTarif()));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tarif tidak ditemukan");
    }

    private long calculateDelayInMillis(LocalTime jamSelesai) {
        // Mengonversi jamSelesai menjadi waktu dalam milidetik dari sekarang
        LocalTime now = LocalTime.now();
        long delayInMillis = java.time.Duration.between(now, jamSelesai).toMillis();
        return delayInMillis;
    }

    // Update Transaksi
    @PostMapping("/update/{id}")
    public ResponseEntity<String> updateTransaksi(@PathVariable int id, @RequestBody TransaksiData transaksi) {
        transaksi.setId(id);
        transaksiRepository.updateTransaksi(transaksi);
        return ResponseEntity.ok("Transaksi berhasil diperbarui");
    }

    // Delete Transaksi
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTransaksi(@PathVariable int id) {
        transaksiRepository.deleteTransaksi(id);
        return ResponseEntity.ok("Transaksi berhasil dihapus");
    }
}
