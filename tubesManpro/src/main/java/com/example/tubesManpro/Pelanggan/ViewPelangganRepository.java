package com.example.tubesManpro.Pelanggan;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class ViewPelangganRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<ViewPelanggan> findAll() {
        String sql = "SELECT id, merek, kapasitas, tarif, status FROM mesin";
        return jdbcTemplate.query(sql, this::mapRowToViewPelanggan);
    }

     // Mapping ResultSet ke MesinData
    private ViewPelanggan mapRowToViewPelanggan(ResultSet resultSet, int rowNum) throws SQLException {
        return new ViewPelanggan(
                        resultSet.getInt("id"),
                        resultSet.getString("merek"),
                        resultSet.getInt("kapasitas"),
                        resultSet.getInt("tarif"),
                        resultSet.getString("status"));
    }

}
