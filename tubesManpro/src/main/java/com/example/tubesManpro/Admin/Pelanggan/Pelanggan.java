package com.example.tubesManpro.Admin.Pelanggan;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pelanggan {
    private int id;
    private String nama;
    private String telepon;
    private String email;
    private String kecamatan;
    private String kelurahan;
}