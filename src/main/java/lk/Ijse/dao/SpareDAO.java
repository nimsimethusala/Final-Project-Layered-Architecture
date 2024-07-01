package lk.Ijse.dao;

import lk.Ijse.entity.Job;
import lk.Ijse.entity.Spares;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface SpareDAO extends CrudDAO<Spares>{
    public boolean update(Job job) throws SQLException, ClassNotFoundException;

    public boolean updateQty(String spareId, int spareCount) throws SQLException, ClassNotFoundException;
}
