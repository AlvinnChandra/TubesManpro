package com.example.tubesManpro.Admin.Mesin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MesinData {
    private int id;
    private String merek;
    private String kapasitas;
    private int tarif;
}
