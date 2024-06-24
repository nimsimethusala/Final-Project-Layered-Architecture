package lk.Ijse.dao;

import lk.Ijse.entity.Customer;

import java.sql.SQLException;
import java.util.List;

public interface CrudDAO<T> extends SuperDAO{
    public boolean save(T customer) throws SQLException, ClassNotFoundException;

    public boolean update(T customer) throws SQLException, ClassNotFoundException;

    public boolean delete(String cusId) throws SQLException, ClassNotFoundException;

    public T searchById(String tel) throws SQLException, ClassNotFoundException;

    public List<T> getAll() throws SQLException, ClassNotFoundException;

    public List<String> getId() throws SQLException, ClassNotFoundException;

    public String getName(String cusId) throws SQLException, ClassNotFoundException;

    public String generateNextId() throws SQLException, ClassNotFoundException;
}
