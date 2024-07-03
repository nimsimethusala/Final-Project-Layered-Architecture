
package lk.Ijse.dao.impl;

import lk.Ijse.dao.JobDAO;
import lk.Ijse.dao.PaymentDAO;
import lk.Ijse.dao.SQLUtil;
import lk.Ijse.entity.Payment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentDaoImpl implements PaymentDAO {
    JobDAO jobDAO = new JobDaoImpl();

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
    public double getTotalDefectCost(String jobId) throws SQLException, ClassNotFoundException {
        String defectId = jobDAO.getDefectId(jobId);

        /*String sql = "SELECT price FROM defect WHERE defect_id = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, defectId);*/

        ResultSet resultSet = SQLUtil.execute("SELECT price FROM defect WHERE defect_id = ?", defectId);

        while (resultSet.next()){
            double defectCost = resultSet.getDouble(1);

            return defectCost;
        }
        return 0;
    }

    @Override
    public double getTotalEmployeeCost(String jobId) throws SQLException, ClassNotFoundException {
        String empId = jobDAO.getEmployeeId(jobId);

        /*String sql = "SELECT cost FROM employee WHERE Emp_id = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, empId);*/

        ResultSet resultSet = SQLUtil.execute("SELECT cost FROM employee WHERE Emp_id = ?", empId);

        while (resultSet.next()){
            double empCost = resultSet.getDouble(1);
            return empCost;
        }

        return 0;
    }

    @Override
    public double getTotalSpareCost(String jobId) throws SQLException, ClassNotFoundException {
        String SpareId = jobDAO.getSpareId(jobId);

        /*String sql = "SELECT price FROM spare WHERE Spare_id = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, SpareId);*/

        ResultSet resultSet = SQLUtil.execute("SELECT price FROM spare WHERE Spare_id = ?",SpareId);

        while (resultSet.next()){
            double SpareCost = resultSet.getDouble(1);

            return SpareCost;
        }
        return 0;
    }

    @Override
    public boolean save(Payment payment) throws SQLException, ClassNotFoundException {
        /*String sql = "INSERT INTO payment VALUES(?, ?, ?, ?, ?, ?)";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1, payment.getPaymentId());
        pstm.setObject(2, payment.getJobId());
        pstm.setObject(3, payment.getDefectTotal());
        pstm.setObject(4, payment.getEmpTotal());
        pstm.setObject(5, payment.getSpareTotal());
        pstm.setObject(6, payment.getTotalCost());

        return pstm.executeUpdate() > 0;*/

        return SQLUtil.execute("INSERT INTO payment VALUES(?, ?, ?, ?, ?, ?)",
                payment.getPaymentId(),
                payment.getJobId(),
                payment.getDefectTotal(),
                payment.getEmpTotal(),
                payment.getSpareTotal(),
                payment.getTotalCost());
    }

    @Override
    public String generateNextId() throws SQLException, ClassNotFoundException {
        /*String sql = "SELECT payment_id FROM payment ORDER BY payment_id DESC LIMIT 1";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);*/

        ResultSet resultSet = SQLUtil.execute("SELECT payment_id FROM payment ORDER BY payment_id DESC LIMIT 1");

        if(resultSet.next()) {
            return splitOrderId(resultSet.getString(1));
        }
        return splitOrderId(null);
    }

    private String splitOrderId(String string) {
        if(string != null) {
            String[] strings = string.split("P0");
            int id = Integer.parseInt(strings[1]);
            id++;
            String ID = String.valueOf(id);
            int length = ID.length();
            if (length < 2){
                return "P00"+id;
            }else {
                if (length < 3){
                    return "P0"+id;
                }else {
                    return "P"+id;
                }
            }
        }
        return "P001";
    }

    public ArrayList<Payment> getAll() throws SQLException, ClassNotFoundException {
        /*String sql = "SELECT * FROM payment";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);*/

        ResultSet resultSet = SQLUtil.execute("SELECT * FROM payment");

        ArrayList<Payment> paymentList = new ArrayList<>();

        while (resultSet.next()){
            String paymentId = resultSet.getString(1);
            String jobId = resultSet.getString(2);
            double defectTotal = resultSet.getDouble(3);
            double employeeTotal = resultSet.getDouble(4);
            double spareTotal = resultSet.getDouble(5);
            double total = resultSet.getDouble(6);

            Payment payment = new Payment(paymentId, jobId, defectTotal, employeeTotal, spareTotal, total);
            paymentList.add(payment);
        }

        return paymentList;
    }

    @Override
    public boolean update(Payment customer) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String cusId) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Payment searchById(String tel) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public String getName(String cusId) throws SQLException, ClassNotFoundException {
        return null;
    }
}