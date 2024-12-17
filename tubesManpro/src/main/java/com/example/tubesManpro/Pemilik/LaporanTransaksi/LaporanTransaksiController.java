package com.example.tubesManpro.Pemilik.LaporanTransaksi;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/laporan")
public class LaporanTransaksiController {
    @Autowired
    private LaporanTransaksiRepository laporanTransaksiRepository;

    // Mengambil semua data transaksi
    @GetMapping("/all")
    public List<LaporanTransaksi> getAllLaporanTransaksi() {
        return laporanTransaksiRepository.findAll();
    }

    // Filter berdasarkan rentang tanggal
    @GetMapping("/filter")
    public List<LaporanTransaksi> getFilteredLaporanTransaksi(
            @RequestParam Date dariTanggal,
            @RequestParam Date sampaiTanggal) {
        return laporanTransaksiRepository.findByFilter(dariTanggal, sampaiTanggal);
    }

    // Mendapatkan data merek terbanyak dipesan
    @GetMapping("/most-ordered-brand")
    public List<LaporanTransaksi> getMostOrderedBrand() {
        return laporanTransaksiRepository.findMostOrderedBrand();
    }

    // Mendapatkan data merek paling sedikit dipesan
    @GetMapping("/least-ordered-brand")
    public List<LaporanTransaksi> getLeastOrderedBrand() {
        return laporanTransaksiRepository.findLeastOrderedBrand();
    }
}
