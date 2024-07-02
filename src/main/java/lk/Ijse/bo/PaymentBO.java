package lk.Ijse.bo;

import lk.Ijse.dto.PaymentDTO;
import lk.Ijse.entity.Payment;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface PaymentBO extends SuperBO{
    public List<String> getPaymentId() throws SQLException, ClassNotFoundException;

    public double getTotalDefectCost(String jobId) throws SQLException, ClassNotFoundException;

    public double getTotalEmployeeCost(String jobId) throws SQLException, ClassNotFoundException;

    public double getTotalSpareCost(String jobId) throws SQLException, ClassNotFoundException;

    public boolean savePayment(PaymentDTO paymentDTO) throws SQLException, ClassNotFoundException;

    public String generateNextPaymentId() throws SQLException, ClassNotFoundException;

    public ArrayList<PaymentDTO> getAllPayments() throws SQLException, ClassNotFoundException;
}
