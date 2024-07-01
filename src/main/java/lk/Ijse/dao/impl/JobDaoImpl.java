package lk.Ijse.dao.impl;

import lk.Ijse.dao.JobDAO;
import lk.Ijse.dao.SQLUtil;
import lk.Ijse.entity.Job;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JobDaoImpl implements JobDAO {
    @Override
    public boolean save(Job job) throws SQLException, ClassNotFoundException {
        /*String sql = "INSERT INTO job VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, job.getJobId());
        pstm.setString(2, job.getModel());
        pstm.setDate(3, job.getDate());
        pstm.setString(4, job.getCustomerId());
        pstm.setInt(5, job.getItemCount());
        pstm.setString(6, job.getDefectId());
        pstm.setString(7, job.getDefectName());
        pstm.setString(8, job.getSpareId());
        pstm.setString(9, job.getSpareName());
        pstm.setInt(10, job.getSpareCount());
        pstm.setString(11, job.getEmpId());
        pstm.setObject(12, job.getEmpName());

        return pstm.executeUpdate() > 0;*/

        return SQLUtil.execute("INSERT INTO job VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", job);
    }

    @Override
    public String generateNextId() throws SQLException, ClassNotFoundException {
        /*String sql = "SELECT job_No FROM job ORDER BY job_No DESC LIMIT 1" ;

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);*/

        ResultSet resultSet = SQLUtil.execute("SELECT job_No FROM job ORDER BY job_No DESC LIMIT 1");

        if(resultSet.next()) {
            return splitOrderId(resultSet.getString(1));
        }
        return splitOrderId(null);
    }

    public String splitOrderId(String string) {
        if(string != null) {
            String[] strings = string.split("J0");
            int id = Integer.parseInt(strings[1]);
            id++;
            String ID = String.valueOf(id);
            int length = ID.length();
            if (length < 2){
                return "J00"+id;
            }else {
                if (length < 3){
                    return "J0"+id;
                }else {
                    return "J"+id;
                }
            }
        }
        return "J001";
    }

    @Override
    public List<String> getId() throws SQLException, ClassNotFoundException {
        /*String sql = "SELECT job_No FROM job";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);*/

        ResultSet resultSet = SQLUtil.execute("SELECT job_No FROM job");

        List<String> idList = new ArrayList<>();
        while (resultSet.next()) {
            idList.add(resultSet.getString(1));
        }
        return idList;
    }

    @Override
    public String getEmployeeId(String jobId) throws SQLException, ClassNotFoundException {
        /*String sql = "SELECT Emp_id FROM job WHERE job_No = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, jobId);*/

        ResultSet resultSet = SQLUtil.execute("SELECT Emp_id FROM job WHERE job_No = ?", jobId);

        if (resultSet.next()){
            String empId = resultSet.getString(1);
            return empId;
        }
        return null;
    }

    @Override
    public String getSpareId(String jobId) throws SQLException, ClassNotFoundException {
        /*String sql = "SELECT Spare_id FROM job WHERE job_No = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, jobId);*/

        ResultSet resultSet = SQLUtil.execute("SELECT Spare_id FROM job WHERE job_No = ?", jobId);

        while (resultSet.next()){
            String spareId = resultSet.getString(1);

            return spareId;
        }
        return null;
    }

    @Override
    public String getDefectId(String jobId) throws SQLException, ClassNotFoundException {
        /*String sql = "SELECT defect_id FROM job WHERE job_No = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, jobId);*/

        ResultSet resultSet = SQLUtil.execute("SELECT defect_id FROM job WHERE job_No = ?", jobId);

        while (resultSet.next()){
            String defectId = resultSet.getString(1);

            return defectId;
        }
        return null;
    }

    @Override
    public boolean update(Job customer) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String cusId) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Job searchById(String tel) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public List<Job> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public String getName(String cusId) throws SQLException, ClassNotFoundException {
        return null;
    }
}
