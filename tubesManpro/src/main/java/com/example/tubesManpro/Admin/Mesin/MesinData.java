package com.example.tubesManpro.Admin.Mesin;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MesinData {
    private int id;
    private String merek;
    private int kapasitas;
    private int tarif;
    private String status;

    public MesinData(int id, String merek, int kapasitas, int tarif, String status){
        this.id = id;
        this.merek = merek;
        this.kapasitas = kapasitas;
        this.tarif = tarif;
        this.status = status;
    }
}
