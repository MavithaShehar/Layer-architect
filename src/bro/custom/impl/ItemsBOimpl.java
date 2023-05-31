package bro.custom.impl;

import bro.custom.ItemsBO;
import dao.DAOFactory;
import dao.custom.ItemsDAO;
import dao.custom.impl.ItemsDAOimpl;
import model.ItemDTO;

import java.sql.SQLException;
import java.util.List;

public class ItemsBOimpl implements ItemsBO {

   // ItemsDAO itemsDAO = new ItemsDAOimpl();

    ItemsDAO itemsDAO = (ItemsDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ITEM);

    @Override
    public List<ItemDTO> getAllItem() throws SQLException, ClassNotFoundException {

        return itemsDAO.getAll();
    }

    @Override
    public boolean saveItem(ItemDTO dto) throws SQLException, ClassNotFoundException {
        return itemsDAO.saveAll(dto);
    }

    @Override
    public boolean updateItem(ItemDTO dto) throws SQLException, ClassNotFoundException {
        return itemsDAO.update(dto);

    }

    @Override
    public boolean existItem(String id) throws SQLException, ClassNotFoundException {
        return itemsDAO.exist(id);
    }

    @Override
    public String generateNewItemId() throws SQLException, ClassNotFoundException {
        return itemsDAO.generateNewId();
    }

    @Override
    public boolean deleteItem(String code) throws SQLException, ClassNotFoundException {
        return itemsDAO. delete(code);
    }

  /*  ItemsDAO itemsDAO = new ItemsDAOimpl();

    public List<ItemDTO> getAllItem() throws SQLException, ClassNotFoundException {

        CustomerDAO customerDAO = new CustomersDAOimpl();
        return itemsDAO.getAll();
    }
    public boolean saveItem(ItemDTO dto) throws SQLException, ClassNotFoundException {

        return itemsDAO.saveAll(dto);
    }

    public boolean updateItem(ItemDTO dto) throws SQLException, ClassNotFoundException {
        CustomerDAO customerDAO = new CustomersDAOimpl();
        return itemsDAO.update(dto);

    }

    public boolean existItem(String id) throws SQLException, ClassNotFoundException {

        return itemsDAO.exist(id);
    }

    public String generateNewItemId() throws SQLException, ClassNotFoundException {

        return itemsDAO.generateNewId();
    }

    public boolean deleteItem(String code) throws SQLException, ClassNotFoundException {

        return itemsDAO. delete(code);
    }*/
}
