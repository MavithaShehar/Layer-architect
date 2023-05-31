package bro.custom.impl;

import bro.custom.PurchaseOrderBO;
import dao.DAOFactory;
import dao.custom.CustomerDAO;
import dao.custom.PlaceOrdersDAO;
import dao.custom.impl.ItemsDAOimpl;
import dao.custom.impl.OrderDetailDAOimpl;
import dao.custom.impl.PlaceOrdersDAOimpl;
import db.DBConnection;
import model.CustomerDTO;
import model.ItemDTO;
import model.OrderDTO;
import model.OrderDetailDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class PurchaseOrderBOimpl<ItemDAO, OrderDAO, OrderDetailsDAO> implements PurchaseOrderBO {

    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);
    ItemDAO itemDAO = (ItemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ITEM);
    OrderDAO orderDAO = (OrderDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER);
    OrderDetailsDAO orderDetailsDAO = (OrderDetailsDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.DETAILS);

    @Override
    public CustomerDTO searchCustomer(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.search(id);
    }

    @Override
    public ItemDTO searchItem(String code) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean existItem(String code) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean existCustomer(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateOrderID() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public List<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public List<ItemDTO> getAllItems() throws SQLException, ClassNotFoundException {
        return null;
    }

    public boolean purchaseOrder(String orderId, LocalDate orderDate, String customerId, List<OrderDetailDTO> orderDetails){

        /*Transaction*/
        Connection connection = null;
        try {

            connection = DBConnection.getDbConnection().getConnection();

            PlaceOrdersDAO orderDAO = new PlaceOrdersDAOimpl();

            boolean b1 = orderDAO.exist(orderId);

            if (b1) {
                return false;
            }

            connection.setAutoCommit(false);
            PlaceOrdersDAOimpl orderDAO1 = new PlaceOrdersDAOimpl();
            boolean b2 = orderDAO1.saveAll(new OrderDTO(orderId, orderDate, customerId));
            if (!b2) {
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }

            OrderDetailDAOimpl orderDetailsDAO = new OrderDetailDAOimpl();

            for (OrderDetailDTO detail : orderDetails) {
                boolean b3 = orderDetailsDAO.saveAll(detail);

                if (!b3) {
                    connection.rollback();
                    connection.setAutoCommit(true);
                    return false;
                }

//                //Search & Update Item
                ItemDTO item = findItem(detail.getItemCode());
                item.setQtyOnHand(item.getQtyOnHand() - detail.getQty());


                ItemsDAOimpl itemDAO = new ItemsDAOimpl();
                boolean b = itemDAO.update(new ItemDTO(item.getCode(), item.getDescription(), item.getUnitPrice(), item.getQtyOnHand()));

                if (!b) {
                    connection.rollback();
                    connection.setAutoCommit(true);
                    return false;
                }
            }

            connection.commit();
            connection.setAutoCommit(true);
            return true;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }


    public ItemDTO findItem(String code) {
        try {

            ItemsDAOimpl itemsDAO = new ItemsDAOimpl();
            ItemDTO item =itemsDAO.search(code);

            return new ItemDTO(code, item.getDescription(), item.getUnitPrice(), item.getQtyOnHand());

        } catch (SQLException e) {
            throw new RuntimeException("Failed to find the Item " + code, e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    }


