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

    // Endpoint untuk total pendapatan keseluruhan
    @GetMapping("/total-pendapatan")
    public int getTotalPendapatan() {
        return laporanTransaksiRepository.findTotalPendapatan();
    }

    // Endpoint untuk total pendapatan berdasarkan filter
    @GetMapping("/total-pendapatan-filter")
    public int getTotalPendapatanByFilter(
            @RequestParam(required = false) Date dariTanggal,
            @RequestParam(required = false) Date sampaiTanggal) {
        if (dariTanggal != null && sampaiTanggal != null) {
            return laporanTransaksiRepository.findTotalPendapatanByFilter(dariTanggal, sampaiTanggal);
        } else {
            return laporanTransaksiRepository.findTotalPendapatan();
        }
    }

    // Endpoint untuk total pendapatan untuk merek terbanyak dipesan
    @GetMapping("/total-pendapatan-most-ordered-brand")
    public int getTotalPendapatanForMostOrderedBrand() {
        return laporanTransaksiRepository.findTotalPendapatanForMostOrderedBrand();
    }

    // Endpoint untuk total pendapatan untuk merek paling sedikit dipesan
    @GetMapping("/total-pendapatan-least-ordered-brand")
    public int getTotalPendapatanForLeastOrderedBrand() {
        return laporanTransaksiRepository.findTotalPendapatanForLeastOrderedBrand();
    }

    }
