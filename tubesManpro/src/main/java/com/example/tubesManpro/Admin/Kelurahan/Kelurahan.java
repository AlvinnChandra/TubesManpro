package com.example.tubesManpro.Admin.Kelurahan;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Kelurahan {
    private int id;
    private String namaKelurahan;
    private int kecamatanId;
}