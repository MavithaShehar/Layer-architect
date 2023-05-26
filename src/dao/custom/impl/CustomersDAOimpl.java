package dao.custom.impl;

import dao.SQLUtil;
import dao.custom.CustomerDAO;
import model.CustomerDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomersDAOimpl implements CustomerDAO {

    @Override
    public List<CustomerDTO> getAll() throws SQLException, ClassNotFoundException {

        ArrayList<CustomerDTO> customerDTO = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM Customer");

        while (rst.next()){
            CustomerDTO customer = new CustomerDTO(
                    rst.getString("id"),
                    rst.getString("name"),
                    rst.getString("address")
            );
            customerDTO.add(customer);
        }

        return customerDTO ;
    }

    @Override
    public boolean saveAll(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO Customer (id,name, address) VALUES (?,?,?)",dto.getId(),dto.getName(),dto.getAddress());
    }

    @Override
    public boolean update(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE Customer SET name=?, address=? WHERE id=?",dto.getId(),dto.getName(),dto.getAddress());
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT id FROM Customer WHERE id=?",id);
        return rst.next();
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT id FROM Customer ORDER BY id DESC LIMIT 1;");

        if (rst.next()) {
            String id = rst.getString("id");
            int newCustomerId = Integer.parseInt(id.replace("C00-", "")) + 1;
            return String.format("C00-%03d", newCustomerId);
        } else {
            return "C00-001";
        }
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("DELETE FROM Customer WHERE id=?",id);
        return rst.next();
    }

    @Override
    public CustomerDTO search(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM Customer WHERE id=?",id + "");
        rst.next();
        return new CustomerDTO(id + "", rst.getString("name"), rst.getString("address"));
    }


}
