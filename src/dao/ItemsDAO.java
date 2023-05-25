package dao;

import db.DBConnection;
import model.ItemDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public interface ItemsDAO {

    public  List<ItemDTO> getAllItems() throws SQLException, ClassNotFoundException ;

    public boolean saveItems(ItemDTO itemDTO) throws SQLException, ClassNotFoundException ;

    public boolean updateItem(ItemDTO itemDTO) throws SQLException, ClassNotFoundException ;

    public boolean existItems(String code) throws SQLException, ClassNotFoundException ;

    public boolean ItemDelete(String code) throws SQLException, ClassNotFoundException ;

    public String genereateNewItemId() throws SQLException, ClassNotFoundException ;

    public ItemDTO searchItem(String newItemCode) throws SQLException, ClassNotFoundException ;
}
