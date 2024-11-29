package com.example.tubesManpro.Admin.Kelurahan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class KecamatanRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<KecamatanData> findAll(int limit, int offset) {
        String sql = "SELECT * FROM kecamatan LIMIT ? OFFSET ?";
        return jdbcTemplate.query(sql, this::mapRowToKecamatan, limit, offset);
    }

    public List<KecamatanData> findById(int id) {
        String sql = "SELECT * FROM kecamatan WHERE id = ?";
        return jdbcTemplate.query(sql, this::mapRowToKecamatan, id);
    }

    public int save(KecamatanData kecamatan) {
        String sql = "INSERT INTO kecamatan (nama_kecamatan) VALUES (?)";
        return jdbcTemplate.update(sql, kecamatan.getNamaKecamatan());
    }

    public int update(KecamatanData kecamatan) {
        String sql = "UPDATE kecamatan SET nama_kecamatan = ? WHERE id = ?";
        return jdbcTemplate.update(sql, kecamatan.getNamaKecamatan(), kecamatan.getId());
    }

    public int deleteById(int id) {
        String sql = "DELETE FROM kecamatan WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    private KecamatanData mapRowToKecamatan(ResultSet resultSet, int rowNum) throws SQLException {
        return new KecamatanData(
                resultSet.getInt("id"),
                resultSet.getString("namaKecamatan"));
    }
}