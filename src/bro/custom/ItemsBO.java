package bro.custom;

import bro.SuperBO;
import dao.custom.ItemsDAO;
import dao.custom.impl.ItemsDAOimpl;
import model.ItemDTO;

import java.sql.SQLException;
import java.util.List;

public interface ItemsBO extends SuperBO {

    ItemsDAO itemsDAO = new ItemsDAOimpl();

    public List<ItemDTO> getAllItem() throws SQLException, ClassNotFoundException ;

    public boolean saveItem(ItemDTO dto) throws SQLException, ClassNotFoundException ;

    public boolean updateItem(ItemDTO dto) throws SQLException, ClassNotFoundException ;

    public boolean existItem(String id) throws SQLException, ClassNotFoundException ;

    public String generateNewItemId() throws SQLException, ClassNotFoundException ;

    public boolean deleteItem(String code) throws SQLException, ClassNotFoundException ;
}
