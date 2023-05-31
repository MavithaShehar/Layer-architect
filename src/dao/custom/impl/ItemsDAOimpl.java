package dao.custom.impl;

import dao.SQLUtil;
import dao.custom.ItemsDAO;
import entity.Items;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ItemsDAOimpl implements ItemsDAO {


    @Override
    public List<Items> getAll() throws SQLException, ClassNotFoundException {

        List<Items> allItem = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM Item");

        while (rst.next()){
            allItem.add(new Items(rst.getString("code"), rst.getString("description"), rst.getInt("qtyOnHand"),rst.getBigDecimal("unitPrice")));
        }
        return allItem;

    }

    @Override
    public boolean saveAll(Items dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO Item (code, description, unitPrice, qtyOnHand) VALUES (?,?,?,?)",dto.getCode(),dto.getDescription(),dto.getUnitPrice(),dto.getQtyOnHand());
    }

    @Override
    public boolean update(Items dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE Item SET description=?, unitPrice=?, qtyOnHand=? WHERE code=?",dto.getDescription(),dto.getUnitPrice(),dto.getQtyOnHand(),dto.getCode());
    }


    @Override
    public boolean exist(String code) throws SQLException, ClassNotFoundException {

        ResultSet rst = SQLUtil.execute("SELECT code FROM Item WHERE code=?",code);
        return rst.next();
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT code FROM Item ORDER BY code DESC LIMIT 1;");
        if (rst.next()) {
            String id = rst.getString("code");
            int newItemId = Integer.parseInt(id.replace("I00-", "")) + 1;
            return String.format("I00-%03d", newItemId);
        } else {
            return "I00-001";
        }
    }

    @Override
    public boolean delete(String code) throws SQLException, ClassNotFoundException {

        ResultSet rst = SQLUtil.execute("DELETE FROM Item WHERE code=?",code);
        return rst.next();


    }

    @Override
    public Items search(String code) throws SQLException, ClassNotFoundException {


        ResultSet rst =  SQLUtil.execute("SELECT * FROM Item WHERE code=?",code + "");
        rst.next();
        return new Items(code + "",  rst.getString("description"), rst.getInt("qtyOnHand"), rst.getBigDecimal("unitPrice"));


    }

}
