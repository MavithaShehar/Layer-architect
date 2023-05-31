package dao;

import entity.Items;

import java.sql.SQLException;
import java.util.List;

public interface CrudDAO<T> extends SuperDAO {

    public List<T> getAll() throws SQLException, ClassNotFoundException;

    boolean saveAll(T dto) throws SQLException, ClassNotFoundException;

    public boolean update (T dto ) throws SQLException, ClassNotFoundException ;

    public boolean exist(String id) throws SQLException, ClassNotFoundException ;

    public String generateNewId() throws SQLException, ClassNotFoundException ;

    public boolean delete(String id) throws SQLException, ClassNotFoundException;

    public T search(String id) throws SQLException, ClassNotFoundException ;

}
