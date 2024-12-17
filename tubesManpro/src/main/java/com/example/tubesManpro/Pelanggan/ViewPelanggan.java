package com.example.tubesManpro.Pelanggan;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ViewPelanggan {
    private int id;
    private String merek;
    private int kapasitas;
    private int tarif;
    private String status;

}
