package com.example.tubesManpro.Admin.Mesin;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class jdbcMesinRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Mendapatkan semua data mesin
    public List<MesinData> findAll() {
        String sql = "SELECT id, merek, kapasitas, tarif, status FROM mesin";
        return jdbcTemplate.query(sql, this::mapRowToMesin);
    }

    // Mendapatkan data mesin berdasarkan ID
    public MesinData findById(int id) {
        String sql = "SELECT id, merek, kapasitas, tarif, status FROM mesin WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, this::mapRowToMesin, id);
    }

    // Menambahkan mesin baru
    public void addMesin(MesinData mesin) {
        String sql = "INSERT INTO mesin (merek, kapasitas, tarif, status) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, mesin.getMerek(), mesin.getKapasitas(), mesin.getTarif(), mesin.getStatus());
    }

    // Memperbarui data mesin
    public void updateMesin(MesinData mesin) {
        String sql = "UPDATE mesin SET merek = ?, kapasitas = ?, tarif = ?, status = ? WHERE id = ?";
        jdbcTemplate.update(sql, mesin.getMerek(), mesin.getKapasitas(), mesin.getTarif(), mesin.getStatus(), mesin.getId());

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
                        resultSet.getInt("kapasitas"),
                        resultSet.getInt("tarif"),
                        resultSet.getString("status"));
    }

    public List<MesinData> findAllMerek() {
        String sql = "SELECT merek FROM mesin";
        return jdbcTemplate.query(sql, this::mapRowToMesin);
    }

    public MesinData findByMerek(String merek) {
        String sql = "SELECT id, merek, kapasitas, tarif, status FROM mesin WHERE merek = ?";
        try {
            return jdbcTemplate.queryForObject(sql, this::mapRowToMesin, merek);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
