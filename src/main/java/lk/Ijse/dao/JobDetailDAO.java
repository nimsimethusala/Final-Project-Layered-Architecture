package lk.Ijse.dao;

import lk.Ijse.entity.JobDetail;

import java.sql.SQLException;
import java.util.List;

public interface JobDetailDAO extends CrudDAO<JobDetail>{
    public boolean save(List<JobDetail> jobList) throws SQLException, ClassNotFoundException;
}
