package com.example.tubesManpro.Admin.Kelurahan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class KelurahanRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Kelurahan> findByKecamatanId(int kecamatanId, int limit, int offset) {
        String sql = "SELECT * FROM kelurahan WHERE kecamatan_id = ? LIMIT ? OFFSET ?";
        return jdbcTemplate.query(sql, this::mapRowToKelurahan, kecamatanId, limit, offset);
    }

    public List<Kelurahan> findById(int id) {
        String sql = "SELECT * FROM kelurahan WHERE id = ?";
        return jdbcTemplate.query(sql, this::mapRowToKelurahan, id);
    }

    public int save(Kelurahan kelurahan) {
        String sql = "INSERT INTO kelurahan (nama_kelurahan, kecamatan_id) VALUES (?, ?)";
        return jdbcTemplate.update(sql, kelurahan.getNamaKelurahan(), kelurahan.getKecamatanId());
    }

    public int update(Kelurahan kelurahan) {
        String sql = "UPDATE kelurahan SET nama_kelurahan = ?, kecamatan_id = ? WHERE id = ?";
        return jdbcTemplate.update(sql, kelurahan.getNamaKelurahan(), kelurahan.getKecamatanId(), kelurahan.getId());
    }

    public int deleteById(int id) {
        String sql = "DELETE FROM kelurahan WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    private Kelurahan mapRowToKelurahan(ResultSet resultSet, int rowNum) throws SQLException {
        return new Kelurahan(
                resultSet.getInt("id"),
                resultSet.getString("namaKelurahan"),
                resultSet.getInt("kecamatanId"));
    }
}