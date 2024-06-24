package lk.Ijse.dao;

import lk.Ijse.entity.Defect;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface DefectDAO extends CrudDAO<Defect>{
    public String getDescription(String defectId) throws SQLException, ClassNotFoundException;
}
