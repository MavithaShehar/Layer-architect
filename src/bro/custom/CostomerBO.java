package bro.custom;

import bro.SuperBO;
import dao.custom.CustomerDAO;
import dao.custom.impl.CustomersDAOimpl;
import model.CustomerDTO;

import java.sql.SQLException;
import java.util.List;

public interface CostomerBO extends SuperBO {

    CustomerDAO customerDAO = new CustomersDAOimpl();

    public List<CustomerDTO> getAllCustomer() throws SQLException, ClassNotFoundException ;

    public boolean saveCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException ;

    public boolean updateCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException ;

    public boolean existCustomer(String id) throws SQLException, ClassNotFoundException ;

    public String generateNewCustomerId() throws SQLException, ClassNotFoundException ;

    public boolean deleteCustomer(String code) throws SQLException, ClassNotFoundException ;
}
