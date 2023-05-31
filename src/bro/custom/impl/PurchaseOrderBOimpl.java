package bro.custom.impl;

import bro.custom.PurchaseOrderBO;
import dao.custom.PlaceOrdersDAO;
import dao.custom.impl.ItemsDAOimpl;
import dao.custom.impl.OrderDetailDAOimpl;
import dao.custom.impl.PlaceOrdersDAOimpl;
import db.DBConnection;
import model.ItemDTO;
import model.OrderDTO;
import model.OrderDetailDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class PurchaseOrderBOimpl implements PurchaseOrderBO {

    
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


