package lk.Ijse.dao.impl;

import lk.Ijse.dao.JobDetailDAO;
import lk.Ijse.dao.SQLUtil;
import lk.Ijse.db.DbConnection;
import lk.Ijse.entity.JobDetail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class JobDetailDaoImpl implements JobDetailDAO {
    public boolean save(List<JobDetail> jobList) throws SQLException, ClassNotFoundException {
        for (JobDetail list : jobList) {
            boolean isSaved = save(list);
            if(!isSaved) {
                return false;
            }
        }
        return true;
    }

    public boolean save(JobDetail list) throws SQLException, ClassNotFoundException {
        /*String sql = "INSERT INTO job_details VALUES(?, ?, ?)";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, list.getItemId());
        pstm.setString(2, list.getModel());
        pstm.setString(3, list.getJobId());

        return pstm.executeUpdate() > 0;*/

        return SQLUtil.execute("INSERT INTO job_details VALUES(?, ?, ?)", list);
    }

    @Override
    public boolean update(JobDetail customer) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String cusId) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public JobDetail searchById(String tel) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public List<JobDetail> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public List<String> getId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public String getName(String cusId) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public String generateNextId() throws SQLException, ClassNotFoundException {
        return null;
    }
}
