package com.example.tubesManpro.Pemilik.LaporanTransaksi;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class LaporanTransaksiRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Mendapatkan semua data transaksi
    public List<LaporanTransaksi> findAll() {
        String sql = "SELECT * FROM transaksi";
        return jdbcTemplate.query(sql, this::mapRowLaporanTransaksi);
    }

    // Mendapatkan data berdasarkan filter (rentang tanggal)
    public List<LaporanTransaksi> findByFilter(Date dariTanggal, Date sampaiTanggal) {
        String sql = "SELECT * FROM transaksi WHERE tanggal BETWEEN ? AND ?";
        return jdbcTemplate.query(sql, this::mapRowLaporanTransaksi, dariTanggal, sampaiTanggal);
    }

    // Mendapatkan merek terbanyak dipesan
    public List<LaporanTransaksi> findMostOrderedBrand() {
        String sql = "SELECT * FROM transaksi WHERE merek = (SELECT merek FROM transaksi GROUP BY merek ORDER BY COUNT(*) DESC LIMIT 1)";
        return jdbcTemplate.query(sql, this::mapRowLaporanTransaksi);
    }

    // Mendapatkan merek paling sedikit dipesan
    public List<LaporanTransaksi> findLeastOrderedBrand() {
        String sql = "SELECT * FROM transaksi WHERE merek = (SELECT merek FROM transaksi GROUP BY merek ORDER BY COUNT(*) ASC LIMIT 1)";
        return jdbcTemplate.query(sql, this::mapRowLaporanTransaksi);
    }

    private LaporanTransaksi mapRowLaporanTransaksi(ResultSet resultSet, int rowNum) throws SQLException {
        return new LaporanTransaksi(
                resultSet.getInt("id"),
                resultSet.getString("nama"),
                resultSet.getString("merek"),
                resultSet.getDate("tanggal"),
                resultSet.getInt("waktuPakai"),
                resultSet.getTime("jamMulai"),
                resultSet.getTime("jamSelesai"),
                resultSet.getInt("tarif"));
    }
}


