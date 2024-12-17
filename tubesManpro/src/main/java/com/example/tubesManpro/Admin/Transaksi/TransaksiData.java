package com.example.tubesManpro.Admin.Transaksi;

import java.sql.Date;
import java.time.LocalTime;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TransaksiData {
    private int id;
    private String nama;
    private String merek;
    private Date tanggal;
    private int waktuPakai;
    private LocalTime jamMulai;
    private LocalTime jamSelesai;
    private int tarif;

    public TransaksiData(int id, String nama, String merek, Date tanggal, int waktuPakai, LocalTime jamMulai, LocalTime jamSelesai, int tarif){
        this.id = id;
        this.nama = nama;
        this.merek = merek;
        this.tanggal = tanggal;
        this.waktuPakai = waktuPakai;
        this.jamMulai = jamMulai;
        this.jamSelesai = jamSelesai;
        this.tarif = tarif;
    }
}
