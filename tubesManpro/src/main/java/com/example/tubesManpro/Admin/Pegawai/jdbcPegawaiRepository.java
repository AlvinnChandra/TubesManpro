package com.example.tubesManpro.Admin.Pegawai;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class jdbcPegawaiRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void save(PegawaiData pegawai) {
        String sql = "INSERT INTO pegawai (nama, posisi) VALUES (?, ?)";
        jdbcTemplate.update(sql, pegawai.getNama(), pegawai.getPosisi());
    }

    public List<PegawaiData> findAll() {
        String sql = "SELECT id, nama, posisi FROM pegawai";
        return jdbcTemplate.query(sql, this::mapRowToUser);
    }

    public PegawaiData findById(int id) {
        String sql = "SELECT id, nama, posisi FROM pegawai WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, this::mapRowToUser, id);
    }

    public void update(PegawaiData pegawai) {
        String sql = "UPDATE pegawai SET nama = ?, posisi = ? WHERE id = ?";
        jdbcTemplate.update(sql, pegawai.getNama(), pegawai.getPosisi(), pegawai.getId());
    }

    public void delete(int id) {
        String sql = "DELETE FROM pegawai WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    private PegawaiData mapRowToUser(ResultSet resultSet, int rowNum) throws SQLException {
        return new PegawaiData(
                resultSet.getInt("id"),
                resultSet.getString("nama"),
                resultSet.getString("posisi"));
    }
}