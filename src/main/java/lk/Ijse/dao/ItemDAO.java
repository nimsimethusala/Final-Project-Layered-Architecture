package lk.Ijse.dao;

import lk.Ijse.entity.Item;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface ItemDAO extends CrudDAO<Item>{
    public boolean update(List<JobDetail> jobList) throws SQLException;

    public boolean updateQty(String itemId, int itemCount) throws SQLException, ClassNotFoundException;
}
