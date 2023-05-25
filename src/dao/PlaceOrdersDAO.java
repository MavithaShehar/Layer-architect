package dao;

import db.DBConnection;
import model.OrderDTO;

import java.sql.*;

public interface PlaceOrdersDAO {


    public String generateNewOrderId() throws SQLException, ClassNotFoundException ;

    public boolean existOrder(String orderId) throws SQLException, ClassNotFoundException ;

    public boolean saveOrder(OrderDTO dto) throws SQLException, ClassNotFoundException ;

}
