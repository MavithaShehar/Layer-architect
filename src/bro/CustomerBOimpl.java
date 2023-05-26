package bro;

import bro.custom.CostomerBO;
import dao.custom.CustomerDAO;
import dao.custom.impl.CustomersDAOimpl;
import model.CustomerDTO;

import java.sql.SQLException;
import java.util.List;


public class CustomerBOimpl implements CostomerBO {

    CustomerDAO customerDAO = new CustomersDAOimpl();
    @Override
    public List<CustomerDTO> getAllCustomer() throws SQLException, ClassNotFoundException {


        return customerDAO.getAll();
    }

    @Override
    public boolean saveCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException {

        return customerDAO.saveAll(dto);
    }

    @Override
    public boolean updateCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException {

        return customerDAO.update(dto);
    }

    @Override
    public boolean existCustomer(String id) throws SQLException, ClassNotFoundException {

        return customerDAO.exist(id);
    }

    @Override
    public String generateNewCustomerId() throws SQLException, ClassNotFoundException {
        return customerDAO.generateNewId();

    }

    @Override
    public boolean deleteCustomer(String code) throws SQLException, ClassNotFoundException {
        return customerDAO. delete(code);
    }

   /* CustomerDAO customerDAO = new CustomersDAOimpl();

    public List<CustomerDTO> getAllCustomer() throws SQLException, ClassNotFoundException {

        CustomerDAO customerDAO = new CustomersDAOimpl();
        return customerDAO.getAll();
    }
    public boolean saveCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException {

        return customerDAO.saveAll(dto);
    }

    public boolean updateCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        CustomerDAO customerDAO = new CustomersDAOimpl();
        return customerDAO.update(dto);
    }
    public boolean existCustomer(String id) throws SQLException, ClassNotFoundException {

        return customerDAO.exist(id);
    }
    public String generateNewCustomerId() throws SQLException, ClassNotFoundException {

        return customerDAO.generateNewId();
    }
    public boolean deleteCustomer(String code) throws SQLException, ClassNotFoundException {

        return customerDAO. delete(code);
    }*/
}
