package lk.Ijse.dao;

import lk.Ijse.entity.Job;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface JobDAO extends CrudDAO<Job>{
    public String getEmployeeId(String jobId) throws SQLException, ClassNotFoundException;

    public String getSpareId(String jobId) throws SQLException, ClassNotFoundException;

    public String getDefectId(String jobId) throws SQLException, ClassNotFoundException;
}
