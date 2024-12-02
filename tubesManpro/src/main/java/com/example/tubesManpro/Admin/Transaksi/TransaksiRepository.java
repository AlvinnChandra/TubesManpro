package com.example.tubesManpro.Admin.Transaksi;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.tubesManpro.Admin.Mesin.MesinData;

@Repository
public class TransaksiRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    //mendapatkan semua data transaksi
    public List<TransaksiData> findAll(){
        String sql = "SELECT * FROM transaksi";
        return jdbcTemplate.query(sql, this::mapRowToTransaksi);
    }

    //menambahkan data transaksi
    public void addTransaksi(TransaksiData transaksi){
        String sql = "INSERT INTO transaksi (nama, merek, tanggal, waktuPakai, jamMulai, jamSelesai, tarif) VALUES (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, transaksi.getNama(), transaksi.getMerek(), transaksi.getTanggal(), transaksi.getWaktuPakai(), transaksi.getJamMulai(), transaksi.getJamSelesai(), transaksi.getTarif());
    }

    // Memperbarui data transaksi
    public void updateTransaksi(TransaksiData transaksi) {
        String sql = "UPDATE transaksi SET nama = ?, merek = ?, tanggal = ?, waktuPakai = ?, jamMulai = ?, jamSelesai = ?, tarif = ? WHERE id = ?";
        jdbcTemplate.update(sql, transaksi.getNama(), transaksi.getMerek(), transaksi.getTanggal(), transaksi.getWaktuPakai(), transaksi.getJamMulai(), transaksi.getJamSelesai(), transaksi.getTarif());

    }

    // Menghapus data transaksi berdasarkan ID
    public void deleteTransaksi(int id) {
        String sql = "DELETE FROM transaksi WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    // Mapping ResultSet ke TransaksiData
    private TransaksiData mapRowTransaksi(ResultSet resultSet, int rowNum) throws SQLException {
        return new TransaksiData(
                resultSet.getString("nama"),
                resultSet.getString("merek"),
                resultSet.getLocalDate("tanggal"),
                resultSet.getInt("waktuPakai"),
                resultSet.getLocalTime("jamMulai"),
                resultSet.getLocalTime("jamSelesai"),
                resultSet.getInt("tarif"));
    }


}
