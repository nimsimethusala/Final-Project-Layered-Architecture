package lk.Ijse.dao;

import lk.Ijse.entity.Payment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface PaymentDAO extends CrudDAO<Payment>{
    public double getTotalDefectCost(String jobId) throws SQLException, ClassNotFoundException;

    public double getTotalEmployeeCost(String jobId) throws SQLException, ClassNotFoundException;

    public double getTotalSpareCost(String jobId) throws SQLException, ClassNotFoundException;
}
