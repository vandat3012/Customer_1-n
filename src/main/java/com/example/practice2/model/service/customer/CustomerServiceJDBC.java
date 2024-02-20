package com.example.practice2.model.service.customer;

import com.example.practice2.model.Customer;
import com.example.practice2.model.Province;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.example.practice2.config.ConnectionJDBC.getConnection;
public class CustomerServiceJDBC implements ICustomerService {

    public static final String SELECT_FROM_CUSTOMER = "select customer.* , province.nameProvince as nameProvince \n" +
            "from customer \n" +
            "join province\n" +
            "on customer.idProvince = province.idProvince;";
    public static final String INSERT_INTO_CUSTOMER_VALUES = "insert into customer (name,email,address,idProvince) values (?,?,?,?);";

    @Override
    public List<Customer> findAll() {
        List<Customer> customerList = new ArrayList<>();
        Connection c = getConnection();
        try {
            PreparedStatement getAllQuery = c.prepareStatement(SELECT_FROM_CUSTOMER);
            ResultSet resultSet = getAllQuery.executeQuery();
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String address = resultSet.getString("address");
                Integer idProvince = resultSet.getInt("idProvince");
                String nameProvince = resultSet.getString("nameProvince");
                Province province = new Province(idProvince,nameProvince);
                Customer customer = new Customer(id,name,email,address,province);
                customerList.add(customer);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return customerList;
    }

    @Override
    public void save(Customer c) {
        Connection connection = getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO_CUSTOMER_VALUES);
            // Thêm tham số
            preparedStatement.setString(1,c.getName());
            preparedStatement.setString(2,c.getEmail());
            preparedStatement.setString(3,c.getAddress());
            preparedStatement.setInt(4,c.getoProvince().getIdProvince());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Customer> findByName(String name) {
        List<Customer> customerList = new ArrayList<>();
      Connection connection = getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select customer.* , province.nameProvince as nameProvince \n" +
                    "from customer \n" +
                    "join province\n" +
                    "on customer.idProvince = province.idProvince\n" +
                    "where name = ?;");
            preparedStatement.setString(1,name);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                String name0fResult = resultSet.getString("name");
                String email = resultSet.getString("email");
                String address = resultSet.getString("address");
                Integer idProvince = resultSet.getInt("idProvince");
                String nameProvince = resultSet.getString("nameProvince");
                Province province = new Province(idProvince,nameProvince);
                Customer customer = new Customer(name0fResult,email,address,province);
                customerList.add(customer);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return customerList;
    }

    @Override
    public void update(Customer customer) {
        Connection connection = getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("update customer set name = ?,email = ?,address = ?,idProvince=? where id = ?;");
            preparedStatement.setString(1,customer.getName());
            preparedStatement.setString(2,customer.getEmail());
            preparedStatement.setString(3,customer.getAddress());
            preparedStatement.setInt(4,customer.getoProvince().getIdProvince());
            preparedStatement.setInt(5,customer.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Customer findById(int id) {
        Customer customer = null;
        Connection connection = getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select id, name, email,address,province.idProvince,province.nameProvince \n" +
                    "from customer \n" +
                    "join province\n" +
                    "on customer.idProvince = province.idProvince\n" +
                    "where id = ?;\n");
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Integer id1 = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String address = resultSet.getString("address");
                Integer idProvince = resultSet.getInt("idProvince");
                String nameProvince = resultSet.getString("nameProvince");
                Province province = new Province(idProvince,nameProvince);
                customer = new Customer(id1,name,email,address,province);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return customer;
    }
}
