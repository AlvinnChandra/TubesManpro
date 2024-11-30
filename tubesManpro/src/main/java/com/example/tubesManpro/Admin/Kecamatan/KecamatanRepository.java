package com.example.tubesManpro.Admin.Kecamatan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class KecamatanRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Menambahkan data Kecamatan
    public void save(KecamatanData kecamatan) {
        String sql = "INSERT INTO kecamatan (nama_kecamatan) VALUES (?)";
        jdbcTemplate.update(sql, kecamatan.getNamaKecamatan());
    }

    // Mengambil semua data Kecamatan dengan pagination
    public List<KecamatanData> findAllWithPagination(int page, int pageSize) {
        int offset = (page - 1) * pageSize;
        String sql = "SELECT * FROM kecamatan LIMIT ? OFFSET ?";
        return jdbcTemplate.query(sql, new RowMapper<KecamatanData>() {
            @Override
            public KecamatanData mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new KecamatanData(rs.getInt("id"), rs.getString("nama_kecamatan"));
            }
        }, pageSize, offset);
    }

    // Mengambil semua data Kecamatan dengan pagination filter
    public List<KecamatanData> findAllWithPagination(int page, int pageSize, String search) {
        int offset = (page - 1) * pageSize;
        String sql = "SELECT * FROM kecamatan WHERE LOWER(nama_kecamatan) LIKE LOWER(?) LIMIT ? OFFSET ?";
        return jdbcTemplate.query(sql, new RowMapper<KecamatanData>() {
            @Override
            public KecamatanData mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new KecamatanData(rs.getInt("id"), rs.getString("nama_kecamatan"));
            }
        }, "%" + search.toLowerCase() + "%", pageSize, offset);  // Menggunakan LOWER() untuk pencarian case-insensitive
    }

    // Mengambil data Kecamatan berdasarkan ID
    public KecamatanData findById(int id) {
        String sql = "SELECT * FROM kecamatan WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new RowMapper<KecamatanData>() {
            @Override
            public KecamatanData mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new KecamatanData(rs.getInt("id"), rs.getString("nama_kecamatan"));
            }
        }, id);
    }

    // Mengupdate data Kecamatan
    public void update(KecamatanData kecamatan) {
        String sql = "UPDATE kecamatan SET nama_kecamatan = ? WHERE id = ?";
        jdbcTemplate.update(sql, kecamatan.getNamaKecamatan(), kecamatan.getId());
    }

    // Menghapus data Kecamatan
    public void delete(int id) {
        String sql = "DELETE FROM kecamatan WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    public int count() {
        String sql = "SELECT COUNT(*) FROM kecamatan";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    public int count(String search) {
        String sql = "SELECT COUNT(*) FROM kecamatan WHERE LOWER(nama_kecamatan) LIKE LOWER(?)";
        return jdbcTemplate.queryForObject(sql, Integer.class, "%" + search.toLowerCase() + "%");  // Menggunakan LOWER() untuk menghitung case-insensitive
    }
}