package com.example.tubesManpro.Pemilik.LaporanTransaksi;

import java.sql.Date;
import java.sql.Time;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LaporanTransaksi {
    private int id;
    private String nama;
    private String merek;
    private Date tanggal;
    private int waktuPakai;
    private Time jamMulai;
    private Time jamSelesai;
    private int tarif;
}
