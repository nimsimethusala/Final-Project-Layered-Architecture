package lk.Ijse.bo.impl;

import lk.Ijse.bo.PaymentBO;
import lk.Ijse.dao.DAOFactory;
import lk.Ijse.dao.PaymentDAO;
import lk.Ijse.dao.impl.PaymentDaoImpl;
import lk.Ijse.dto.PaymentDTO;
import lk.Ijse.entity.Payment;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentBoImpl implements PaymentBO {
    PaymentDAO paymentDAO = (PaymentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PAYMENT);

    @Override
    public List<String> getPaymentId() throws SQLException, ClassNotFoundException {
        return paymentDAO.getId();
    }

    @Override
    public double getTotalDefectCost(String jobId) throws SQLException, ClassNotFoundException {
        return paymentDAO.getTotalDefectCost(jobId);
    }

    @Override
    public double getTotalEmployeeCost(String jobId) throws SQLException, ClassNotFoundException {
        return paymentDAO.getTotalEmployeeCost(jobId);
    }

    @Override
    public double getTotalSpareCost(String jobId) throws SQLException, ClassNotFoundException {
        return paymentDAO.getTotalSpareCost(jobId);
    }

    @Override
    public boolean savePayment(PaymentDTO paymentDTO) throws SQLException, ClassNotFoundException {
        return paymentDAO.save(new Payment(
                paymentDTO.getPaymentId(),
                paymentDTO.getJobId(),
                paymentDTO.getDefectTotal(),
                paymentDTO.getEmpTotal(),
                paymentDTO.getSpareTotal(),
                paymentDTO.getTotalCost()
        ));
    }

    @Override
    public String generateNextPaymentId() throws SQLException, ClassNotFoundException {
        return paymentDAO.generateNextId();
    }

    @Override
    public ArrayList<PaymentDTO> getAllPayments() throws SQLException, ClassNotFoundException {
        ArrayList<PaymentDTO> paymentDTOS = new ArrayList<>();
        ArrayList<Payment> payments = paymentDAO.getAll();

        for (Payment payment : payments) {
            PaymentDTO paymentDTO = new PaymentDTO(payment.getPaymentId(), payment.getJobId(), payment.getDefectTotal(), payment.getEmpTotal(), payment.getSpareTotal(), payment.getTotalCost());
            paymentDTOS.add(paymentDTO);
        }
        return paymentDTOS;
    }
}
