package dao;

import db.DBConnection;
import model.CustomerDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public interface CustomerDAO {

    public List<CustomerDTO> getAllCustomer() throws SQLException, ClassNotFoundException;

    public boolean updateCustomer(CustomerDTO customerDTO ) throws SQLException, ClassNotFoundException ;

    public boolean existCustomer(String id) throws SQLException, ClassNotFoundException ;

    public boolean customerDelete(String id) throws SQLException, ClassNotFoundException;

    public String generateNewCustomerId() throws SQLException, ClassNotFoundException ;


    public CustomerDTO searchCustomer(String newValue) throws SQLException, ClassNotFoundException ;

    boolean saveAllCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException;
}
