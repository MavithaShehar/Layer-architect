package dao;

import db.DBConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public interface PlaceOrdersDAO {

    public String generateNewOrderId() throws SQLException, ClassNotFoundException ;

}
