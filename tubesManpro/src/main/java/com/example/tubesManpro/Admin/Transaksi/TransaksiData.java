package com.example.tubesManpro.Admin.Transaksi;

import java.sql.Date;
import java.sql.Time;

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
    private Time jamMulai;
    private Time jamSelesai;
    private int tarif;

    public TransaksiData(int id, String nama, String merek, Date tanggal, int waktuPakai, Time jamMulai, Time jamSelesai, int tarif){
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
