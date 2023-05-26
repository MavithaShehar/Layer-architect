package dao.custom.impl;

import bro.ItemsBOimpl;
import dao.SQLUtil;
import dao.custom.ItemsDAO;
import model.ItemDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemsDAOimpl implements ItemsDAO {


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

}
