package dao;

import db.DBConnection;
import model.ItemDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemsDAOimpl implements CrudDAO<ItemDTO> {
    @Override
    public List<ItemDTO> getAll() throws SQLException, ClassNotFoundException {

        ArrayList<ItemDTO>itemDTO = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM Item");

        while (rst.next()){

            ItemDTO items = new ItemDTO(
                    rst.getString( 1),
                    rst.getString( 2),
                    rst.getBigDecimal( 3),
                    rst.getInt(4)

            );

            itemDTO.add(items);
        }
        return itemDTO;

    }

    @Override
    public boolean saveAll(ItemDTO dto) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute("INSERT INTO Item (code, description, unitPrice, qtyOnHand) VALUES (?,?,?,?)",dto.getCode(),dto.getDescription(),dto.getUnitPrice(),dto.getQtyOnHand());

    }

    @Override
    public boolean update(ItemDTO dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE Item SET description=?, unitPrice=?, qtyOnHand=? WHERE code=?",dto.getUnitPrice(),dto.getUnitPrice(),dto.getQtyOnHand(),dto.getQtyOnHand());
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
    public ItemDTO search(String code) throws SQLException, ClassNotFoundException {


        ResultSet rst =  SQLUtil.execute("SELECT * FROM Item WHERE code=?",code + "");
        rst.next();
        return new ItemDTO(code + "",  rst.getString("description"), rst.getBigDecimal("unitPrice"), rst.getInt("qtyOnHand"));


    }


   /* @Override
    public  List<ItemDTO> getAllItems() throws SQLException, ClassNotFoundException {

       *//* Connection connection = DBConnection.getDbConnection().getConnection();
        Statement stm = connection.createStatement();
        ResultSet rst = stm.executeQuery("SELECT * FROM Item");
*//*
        ArrayList<ItemDTO>itemDTO = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM Item");

        while (rst.next()){

            ItemDTO items = new ItemDTO(
                    rst.getString( 1),
                    rst.getString( 2),
                    rst.getBigDecimal( 3),
                    rst.getInt(4)

            );

             itemDTO.add(items);
        }
        return itemDTO;
    }

    @Override
    public boolean saveItems(ItemDTO itemDTO) throws SQLException, ClassNotFoundException {

        *//*Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("INSERT INTO Item (code, description, unitPrice, qtyOnHand) VALUES (?,?,?,?)");
        pstm.setString(1, itemDTO.getCode());
        pstm.setString(2, itemDTO.getDescription());
        pstm.setBigDecimal(3, itemDTO.getUnitPrice());
        pstm.setInt(4, itemDTO.getQtyOnHand());
        pstm.executeUpdate();
        return  0 < pstm.executeUpdate();*//*

        return SQLUtil.execute("INSERT INTO Item (code, description, unitPrice, qtyOnHand) VALUES (?,?,?,?)",itemDTO.getCode(),itemDTO.getDescription(),itemDTO.getUnitPrice(),itemDTO.getQtyOnHand());

    }

    @Override
    public boolean updateItem(ItemDTO itemDTO) throws SQLException, ClassNotFoundException {

        *//*Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("UPDATE Item SET description=?, unitPrice=?, qtyOnHand=? WHERE code=?");
        pstm.setString(1, itemDTO.getDescription());
        pstm.setBigDecimal(2, itemDTO.getUnitPrice());
        pstm.setInt(3, itemDTO.getQtyOnHand());
        pstm.setString(4, itemDTO.getCode());
        pstm.executeUpdate();
        return  0 < pstm.executeUpdate();*//*

        return SQLUtil.execute("UPDATE Item SET description=?, unitPrice=?, qtyOnHand=? WHERE code=?",itemDTO.getUnitPrice(),itemDTO.getUnitPrice(),itemDTO.getQtyOnHand(),itemDTO.getQtyOnHand());
    }

    @Override
    public boolean existItems(String code) throws SQLException, ClassNotFoundException {
     *//*Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT code FROM Item WHERE code=?");
        pstm.setString(1, code);
        return pstm.executeQuery().next();*//*

        ResultSet rst = SQLUtil.execute("SELECT code FROM Item WHERE code=?",code);
        return rst.next();
    }

    @Override
    public boolean ItemDelete(String code) throws SQLException, ClassNotFoundException {

      *//*  Connection connection = DBConnection.getDbConnection().getConnection();
            PreparedStatement pstm = connection.prepareStatement("DELETE FROM Item WHERE code=?");
            pstm.setString(1, code);
            pstm.executeUpdate();
            return pstm.executeQuery().next();*//*
        ResultSet rst = SQLUtil.execute("DELETE FROM Item WHERE code=?",code);
        return rst.next();

    }

    @Override
    public String genereateNewItemId() throws SQLException, ClassNotFoundException {

        *//* Connection connection = DBConnection.getDbConnection().getConnection();
            ResultSet rst = connection.createStatement().executeQuery("SELECT code FROM Item ORDER BY code DESC LIMIT 1;");*//*

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
    public ItemDTO searchItem(String id) throws SQLException, ClassNotFoundException {

       *//* Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM Item WHERE code=?");
        pstm.setString(1, newItemCode + "");
        ResultSet rst = pstm.executeQuery();
        rst.next();
        ItemDTO item = new ItemDTO(newItemCode + "", rst.getString("description"), rst.getBigDecimal("unitPrice"), rst.getInt("qtyOnHand"));
        return item;*//*

        ResultSet rst =  SQLUtil.execute("SELECT * FROM Item WHERE code=?",id + "");
        rst.next();
        return new ItemDTO(id + "",  rst.getString("description"), rst.getBigDecimal("unitPrice"), rst.getInt("qtyOnHand"));

    }*/


}
