package com.example.practice2.model.service.province;

import com.example.practice2.model.Province;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.example.practice2.config.ConnectionJDBC.getConnection;

public class ProvinceJDBC implements IProvince {
    @Override
    public List<Province> findAll() {
        List<Province> provinces = new ArrayList<>();
        Connection connection = getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from province;");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Integer id = resultSet.getInt("idProvince");
                String name = resultSet.getString("nameProvince");
                Province province = new Province(id,name);
                provinces.add(province);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return provinces;
    }
}
