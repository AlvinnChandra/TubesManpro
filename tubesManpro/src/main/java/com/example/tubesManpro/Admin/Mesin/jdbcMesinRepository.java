package com.example.tubesManpro.Admin.Mesin;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class jdbcMesinRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Mendapatkan semua data mesin
    public List<MesinData> findAll() {
        String sql = "SELECT id, merek, kapasitas, tarif FROM mesin";
        return jdbcTemplate.query(sql, this::mapRowToMesin);
    }

    // Menambahkan mesin baru
    public void addMesin(MesinData mesin) {
        String sql = "INSERT INTO mesin (merek, kapasitas, tarif) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, mesin.getMerek(), mesin.getKapasitas(), mesin.getTarif());
    }

    // Memperbarui data mesin
    public void updateMesin(MesinData mesin) {
        String sql = "UPDATE mesin SET merek = ?, kapasitas = ?, tarif = ? WHERE id = ?";
        jdbcTemplate.update(sql, mesin.getMerek(), mesin.getKapasitas(), mesin.getTarif(), mesin.getId());

    }

    // Menghapus data mesin berdasarkan ID
    public void deleteMesin(int id) {
        String sql = "DELETE FROM mesin WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    // Mapping ResultSet ke MesinData
    private MesinData mapRowToMesin(ResultSet resultSet, int rowNum) throws SQLException {
        return new MesinData(
                resultSet.getInt("id"),
                resultSet.getString("merek"),
                resultSet.getString("kapasitas"),
                resultSet.getInt("tarif"));
    }
}
