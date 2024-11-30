package com.example.tubesManpro.Admin.Kelurahan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class KelurahanRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Menambahkan data Kelurahan
    public void save(KelurahanData kelurahan) {
        String sql = "INSERT INTO kelurahan (nama_kelurahan, kecamatan_id) VALUES (?, ?)";
        jdbcTemplate.update(sql, kelurahan.getNamaKelurahan(), kelurahan.getId());
    }

    // Mengupdate data Kelurahan
    public void update(KelurahanData kelurahan) {
        String sql = "UPDATE kelurahan SET nama_kelurahan = ? WHERE id = ?";
        jdbcTemplate.update(sql, kelurahan.getNamaKelurahan(), kelurahan.getId());
    }

    // Menghapus data Kelurahan
    public void delete(int id) {
        String sql = "DELETE FROM kelurahan WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    // Mengambil semua data Kelurahan dengan pagination
    public List<KelurahanData> findAllWithPagination(int page, int pageSize) {
        int offset = (page - 1) * pageSize;
        String sql = "SELECT id, nama_kelurahan FROM kelurahan LIMIT ? OFFSET ?";
        return jdbcTemplate.query(sql, new RowMapper<KelurahanData>() {
            @Override
            public KelurahanData mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new KelurahanData(rs.getInt("id"), rs.getString("nama_kelurahan"));
            }
        }, pageSize, offset);
    }

    // Mengambil semua data Kelurahan dengan pagination filter
    @SuppressWarnings("deprecation")
    public List<KelurahanData> findAllWithPaginationAndSearch(int page, int size, String search) {
        int offset = (page - 1) * size;
        String sql = "SELECT id, nama_kelurahan FROM kelurahan WHERE nama_kelurahan LIKE ? LIMIT ? OFFSET ?";
        return jdbcTemplate.query(sql, new Object[]{"%" + search + "%", size, offset}, 
                (rs, rowNum) -> new KelurahanData(rs.getInt("id"), rs.getString("nama_kelurahan")));
    }

    // Mengambil data Kelurahan berdasarkan ID
    public KelurahanData findById(int id) {
        String sql = "SELECT id, nama_kelurahan FROM kelurahan WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new RowMapper<KelurahanData>() {
            @Override
            public KelurahanData mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new KelurahanData(rs.getInt("id"), rs.getString("nama_kelurahan"));
            }
        }, id);
    }

    public List<KelurahanData> findByKecamatanId(int kecamatanId) {
        String sql = "SELECT id, nama_kelurahan FROM kelurahan WHERE kecamatan_id = ?";
        return jdbcTemplate.query(sql, new RowMapper<KelurahanData>() {
            @Override
            public KelurahanData mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new KelurahanData(rs.getInt("id"), rs.getString("nama_kelurahan"));
            }
        }, kecamatanId);
    }

    @SuppressWarnings("deprecation")
    public int countBySearch(String search) {
        String sql = "SELECT COUNT(*) FROM kelurahan WHERE nama_kelurahan LIKE ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{"%" + search + "%"}, Integer.class);
    }
}
