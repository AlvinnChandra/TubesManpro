package com.example.tubesManpro.Admin.Pelanggan;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class PelangganRepository {

    private final JdbcTemplate jdbcTemplate;

    public PelangganRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Pelanggan> findAll() {
        String sql = "SELECT * FROM pelanggan";
        return jdbcTemplate.query(sql, new PelangganRowMapper());
    }

    public Pelanggan findById(int id) {
        String sql = "SELECT * FROM pelanggan WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new PelangganRowMapper(), id);
    }

    public void save(Pelanggan pelanggan) {
        // Ambil nama kecamatan berdasarkan ID
        String sqlKecamatan = "SELECT nama_kecamatan FROM kecamatan WHERE id = ?";
        String sqlKelurahan = "SELECT nama_kelurahan FROM kelurahan WHERE id = ?";
    
        String namaKecamatan = jdbcTemplate.queryForObject(sqlKecamatan, String.class, pelanggan.getIdKecamatan());
        String namaKelurahan = jdbcTemplate.queryForObject(sqlKelurahan, String.class, pelanggan.getIdKelurahan());
    
        // Simpan data pelanggan
        String sql = "INSERT INTO pelanggan (nama, telepon, email, id_kecamatan, id_kelurahan, kecamatan, kelurahan) VALUES (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, pelanggan.getNama(), pelanggan.getTelepon(), pelanggan.getEmail(),
                pelanggan.getIdKecamatan(), pelanggan.getIdKelurahan(), namaKecamatan, namaKelurahan);
    }

    public void update(Pelanggan pelanggan) {
        // Ambil nama kecamatan dan kelurahan berdasarkan ID
        String sqlKecamatan = "SELECT nama_kecamatan FROM kecamatan WHERE id = ?";
        String sqlKelurahan = "SELECT nama_kelurahan FROM kelurahan WHERE id = ?";
    
        String namaKecamatan = jdbcTemplate.queryForObject(sqlKecamatan, String.class, pelanggan.getIdKecamatan());
        String namaKelurahan = jdbcTemplate.queryForObject(sqlKelurahan, String.class, pelanggan.getIdKelurahan());
    
        // Update data pelanggan
        String sql = "UPDATE pelanggan SET nama = ?, telepon = ?, email = ?, id_kecamatan = ?, id_kelurahan = ?, kecamatan = ?, kelurahan = ? WHERE id = ?";
        jdbcTemplate.update(sql, pelanggan.getNama(), pelanggan.getTelepon(), pelanggan.getEmail(),
                pelanggan.getIdKecamatan(), pelanggan.getIdKelurahan(), namaKecamatan, namaKelurahan, pelanggan.getId());
    }
    

    public void deleteById(int id) {
        String sql = "DELETE FROM pelanggan WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    private static class PelangganRowMapper implements RowMapper<Pelanggan> {
        @Override
        public Pelanggan mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Pelanggan(
                    rs.getInt("id"),
                    rs.getString("nama"),
                    rs.getString("telepon"),
                    rs.getString("email"),
                    rs.getString("kecamatan"),
                    rs.getString("kelurahan"),
                    rs.getInt("id_kecamatan"),
                    rs.getInt("id_kelurahan")
            );
        }
    }
}
