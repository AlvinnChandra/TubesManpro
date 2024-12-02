package com.example.tubesManpro.Admin.Transaksi;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TransaksiData {
    private int id;
    private String nama;
    private String merek;
    private LocalDate tanggal;
    private int waktuPakai;
    private LocalTime jamMulai;
    private LocalTime jamSelesai;
    private int tarif;
}
